package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Example;
import com.reallyfun.server.service.IExampleService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 处理⽤户相关请求的控制器类
 */
@RestController
@RequestMapping("example")
public class ExampleController extends BaseController {
    @Autowired
    private IExampleService exampleService;

    @RequestMapping("test")
    public ResponseResult<Void> test(Integer eint, String estr) {
        // 调⽤业务对象执⾏注册
        exampleService.add(eint, estr);
        // 返回
        return ResponseResult.getResponseResult("成功");
    }
}