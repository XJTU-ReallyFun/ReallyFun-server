package com.reallyfun.server.controller;

import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.ex.*;
import com.reallyfun.server.util.FileTool;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器类的基类
 */
public class BaseController {
    /**
     * ⽤于统⼀处理⽅法抛出的异常
     *
     * @param e 捕捉到的错误
     * @return 错误信息，若无匹配则返回null
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Void> handleException(Throwable e) {
        ResponseResult responseResult;
        if (e instanceof ExampleException) {
            responseResult = ResponseResult.getResponseResult(4000, "示例错误");
        } else if (e instanceof UserException) {
            responseResult = ResponseResult.getResponseResult(4001, e.getMessage());
        } else if (e instanceof FileToolException) {
            responseResult = ResponseResult.getResponseResult(4002, e.getMessage());
        } else {
            responseResult = ResponseResult.getResponseResult(1000, "未知错误");
        }
        return responseResult;
    }

    /**
     * 在HttpSession对象中设置当前用户对象
     *
     * @param user    用户对象
     * @param session HttpSession对象
     */
    protected final void setUserIntoSession(User user, HttpSession session) {
        session.setAttribute("user", user);
    }

    /**
     * 从HttpSession对象中获取当前用户对象
     *
     * @param session HttpSession对象
     * @return 当前登录用户对象，若未登录则返回null
     */
    protected final User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}