package com.reallyfun.server.controller;

import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IUserService;
import com.reallyfun.server.service.ex.UserException;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 处理⽤户相关请求的控制器类
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     *
     * @param user 需包含name, password, email
     * @return 响应结果
     */
    @PostMapping("register")
    public ResponseResult<Void> register(@RequestBody User user) {
        // 用户注册
        userService.register(
                user.getName(),
                user.getPassword(),
                user.getEmail()
        );

        // 返回响应结果
        return ResponseResult.getResponseResult("注册成功！");
    }

    /**
     * 用户登录
     *
     * @param user    需包含name, password
     * @param session Http会话对象
     * @return 若登录成功，返回用户信息，否则返回相应错误信息
     */
    @PostMapping("login")
    public ResponseResult<User> login(@RequestBody User user, HttpSession session) {
        // 检查重复登录
        if (getUserFromSession(session) != null) {
            throw new UserException("您已登录");
        }

        // 登录并获取当前用户数据
        User userResult = userService.login(user.getName(), user.getPassword());

        // 将用户数据存入HttpSession中
        setUserIntoSession(userResult, session);

        // 构造并返回响应结果
        User userRet = new User();
        userRet.setId(userResult.getId());
        userRet.setName(userResult.getName());
        userRet.setEmail(userResult.getEmail());
        userRet.setAvatar(userResult.getAvatar());
        userRet.setAuth(userResult.getAuth());
        return ResponseResult.getResponseResult("登录成功！", userRet);
    }
}
