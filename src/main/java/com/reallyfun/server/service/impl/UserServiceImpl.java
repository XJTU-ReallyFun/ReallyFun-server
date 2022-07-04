package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.User;
import com.reallyfun.server.mapper.IUserMapper;
import com.reallyfun.server.service.IUserService;
import com.reallyfun.server.service.ex.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 处理用户数据的业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private IUserMapper userMapper;

    /**
     * 用户注册
     *
     * @param name     用户姓名
     * @param password 用户密码明文
     * @param email    用户邮箱地址
     */
    @Override
    public void register(String name, String password, String email) {
        // 格式检查
        if (!isValidName(name)) {
            throw new UserException("用户名格式有误");
        }
        if (!isValidPassword(password)) {
            throw new UserException("密码格式有误");
        }
        if (!isValidEmail(email)) {
            throw new UserException("邮箱格式有误");
        }

        // 重复检查
        if (userMapper.findUserByName(name) != null) {
            throw new UserException("用户名已存在");
        }

        // 密码加密
        String salt = UUID.randomUUID().toString().toLowerCase();
        String encryptedPassword = encryptPassword(password, salt);

        // 构造用户数据
        User user = new User();
        user.setName(name);
        user.setPassword(encryptedPassword);
        user.setSalt(salt);
        user.setEmail(email);
        // TODO: 使用配置文件设置默认/随机头像地址
        user.setAvatar("https://avatar.com/default");
        user.setAuth(0);
        user.createBy(-1);

        // 插入并检查是否成功
        Integer result = userMapper.insert(user);
        if (result != 1) {
            throw new UserException("用户注册失败");
        }
    }

    /**
     * 判断用户名格式是否正确
     *
     * @param name 用户名
     * @return 若正确则返回true
     */
    private Boolean isValidName(String name) {
        return name.matches("[a-zA-Z0-9_]{3,32}");
    }

    /**
     * 判断密码格式是否正确
     *
     * @param password 密码
     * @return 若正确则返回true
     */
    private Boolean isValidPassword(String password) {
        return password.matches("[a-zA-Z0-9`~!@#$%^&*()\\-=_+\\[\\]\\\\{}|;':\",./<>?]{8,32}");
    }

    /**
     * 判断邮箱格式是否正确
     *
     * @param email 邮箱地址
     * @return 若正确则返回true
     */
    private Boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+");
    }


    /**
     * 根据密码明文和随机盐值计算MD5值
     *
     * @param password 密码明文
     * @param salt     盐值
     * @return (password + salt)的MD5
     */
    private String encryptPassword(String password, String salt) {
        byte[] bytes = (password + salt).getBytes();
        String encrypted = DigestUtils.md5DigestAsHex(bytes).toLowerCase();
        return encrypted;
    }

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码明文
     * @return 若登录成功则返回用户信息，否则抛出错误
     */
    @Override
    public User login(String name, String password) {
        // 根据用户名查询用户信息
        User user = userMapper.findUserByName(name);
        if (user == null) {
            throw new UserException("用户不存在");
        }

        // 检查密码进行身份验证
        if (!verifyPassword(password, user.getPassword(), user.getSalt())) {
            throw new UserException("密码错误");
        }

        // 返回用户信息
        return user;
    }

    /**
     * 检查密码明文与盐值是否与MD5值匹配
     *
     * @param password 密码明文
     * @param hash     MD5值
     * @param salt     盐值
     * @return 若匹配则返回true
     */
    private Boolean verifyPassword(String password, String hash, String salt) {
        String encrypted = encryptPassword(password, salt);
        return encrypted.equals(hash);
    }
}
