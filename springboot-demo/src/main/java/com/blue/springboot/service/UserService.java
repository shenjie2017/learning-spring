package com.blue.springboot.service;

import com.blue.springboot.pojo.User;

public interface UserService {
    public User findUserById(Integer id);

    public User findUserByName(String username);

    public void register(String username, String password,String nickname);

    public void updateInfo(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
