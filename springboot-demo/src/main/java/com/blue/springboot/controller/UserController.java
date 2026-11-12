package com.blue.springboot.controller;

import com.blue.springboot.pojo.Result;
import com.blue.springboot.pojo.User;
import com.blue.springboot.service.UserService;
import com.blue.springboot.utils.JWTUtil;
import com.blue.springboot.utils.MD5Util;
import com.blue.springboot.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{4,16}$") String username,
                           @Pattern(regexp = "^\\S{6,16}$")String password,
                           @Pattern(regexp = "^\\S{1,20}$") String nickname) {
        User user = userService.findUserByName(username);
        if(user != null){
            return Result.error("用户名已被占用");
        }
        userService.register(username,password,nickname);
        user = userService.findUserByName(username);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());

        String token = JWTUtil.getToken(claims);
        return Result.success(token);
    }

    @RequestMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{4,16}$") String username, @Pattern(regexp = "^\\S{6,16}$")String password) {
        User user = userService.findUserByName(username);
        if (user == null) {
            return Result.error("用户不存在");
        }
        String newPwd = MD5Util.getMD5(password);
        if(!user.getPassword().equals(newPwd)){
            return Result.error("用户名或密码错误");
        }

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());

        String token = JWTUtil.getToken(claims);
        return Result.success(token);
    }

    @RequestMapping("/userInfo")
    public Result findUserInfo() {
        Integer id = (Integer) ThreadLocalUtil.get().get("id");
        User user = userService.findUserById(id);

        return Result.success(user);
    }

    @RequestMapping("/updateInfo")
    public Result updateInfo(@RequestBody @Validated User user) {
        userService.updateInfo(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }

        if(!newPwd.equals(rePwd)){
            return Result.error("确认密码和新密码不一致!");
        }

        Integer id = (Integer) ThreadLocalUtil.get().get("id");
        User user = userService.findUserById(id);
        if(!user.getPassword().equals(MD5Util.getMD5(oldPwd))){
            return Result.error("原密码不正确!");
        }

        userService.updatePwd(newPwd);
        return Result.success();
    }
}
