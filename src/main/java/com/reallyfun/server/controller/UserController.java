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

/**
 * 处理⽤户相关请求的控制器类
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public ResponseResult<Void> register(@RequestBody User user) throws UserException {
        userService.register(
                user.getName(),
                user.getPassword(),
                user.getEmail()
        );
        return ResponseResult.getResponseResult("注册成功！");
    }
}
