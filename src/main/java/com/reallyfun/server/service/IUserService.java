package com.reallyfun.server.service;

import com.reallyfun.server.entity.User;

import javax.servlet.http.HttpSession;

public interface IUserService {
    void register(String name, String password, String email);

    User login(String name, String password);

    void updateAvatar(Integer id, String avatar);

    String getAvatarById(Integer id);
}
