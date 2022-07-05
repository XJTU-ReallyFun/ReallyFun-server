package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.User;

/**
 * 处理用户操作的持久层接口
 */
public interface IUserMapper {
    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户ID删除用户数据
     *
     * @param id 用户ID
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

    /**
     * 根据用户ID查找用户
     *
     * @param id 用户ID
     * @return 用户实体对象，若无则返回null
     */
    User findUserById(Integer id);

    /**
     * 根据用户名查找用户
     *
     * @param name 用户名
     * @return 用户实体对象，若无则返回null
     */
    User findUserByName(String name);

    /**
     * 根据用户ID更改对应用户头像文件名
     *
     * @param user 用户对象
     * @return 受影响的行数
     */
    Integer updateAvatarById(User user);
}
