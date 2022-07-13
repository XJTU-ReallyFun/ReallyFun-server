package com.reallyfun.server.service;

import com.reallyfun.server.entity.User;

import javax.servlet.http.HttpSession;

public interface IUserService {
    /**
     * 用户注册
     *
     * @param name     用户姓名
     * @param password 用户密码明文
     * @param email    用户邮箱地址
     */
    void register(String name, String password, String email);

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码明文
     * @return 若登录成功则返回用户信息，否则抛出错误
     */
    User login(String name, String password);

    /**
     * 根据用户ID更改对应用户头像文件名
     *
     * @param id     用户ID
     * @param avatar 头像文件名
     */
    void updateAvatar(Integer id, String avatar);

    /**
     * 根据用户ID获取头像文件名
     *
     * @param id 用户ID
     * @return 头像文件名
     */
    String getAvatarById(Integer id);

    /**
     * 根据用户ID获取用户对象
     *
     * @param id 用户ID
     * @return 用户对象
     */
    User findById(Integer id);

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param oldPassword 原密码明文
     * @param newPassword 新密码明文
     */
    void updatePassword(Integer id, String oldPassword, String newPassword);

    /**
     * 修改用户名
     *
     * @param id   用户ID
     * @param name 修改后的用户名
     */
    void updateName(Integer id, String name);

    /**
     * 修改用户权限
     *
     * @param id      被修改权限的用户ID
     * @param dstAuth 目标权限值
     * @param userId  操作人ID
     * @param auth    操作人权限值
     */
    void updateAuth(Integer id, Integer dstAuth, Integer userId, Integer auth);
}
