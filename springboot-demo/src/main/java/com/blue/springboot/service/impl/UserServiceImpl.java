package com.blue.springboot.service.impl;

import com.blue.springboot.mapper.UserMapper;
import com.blue.springboot.pojo.User;
import com.blue.springboot.service.UserService;
import com.blue.springboot.utils.MD5Util;
import com.blue.springboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void register(String username, String password, String nickname) {
        String newPwd = MD5Util.getMD5(password);
        userMapper.add(username,newPwd,nickname);
    }

    @Override
    public void updateInfo(User user) {
        Integer id = (Integer) ThreadLocalUtil.get().get("id");
        user.setId(id);
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Integer id = (Integer) ThreadLocalUtil.get().get("id");
        userMapper.updateAvatar(id,avatarUrl);

    }

    @Override
    public void updatePwd(String newPwd) {
        Integer id = (Integer) ThreadLocalUtil.get().get("id");
        String md5Pwd = MD5Util.getMD5(newPwd);
        userMapper.updatePwd(id,md5Pwd);
    }
}
