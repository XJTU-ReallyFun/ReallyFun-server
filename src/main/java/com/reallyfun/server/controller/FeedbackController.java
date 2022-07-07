package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.service.IFeedbackService;
import com.reallyfun.server.service.ex.InsertException;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;
    @RequestMapping("submit")
    public ResponseResult<Void> submitFeedback (Integer gameId, String category, String content) {
        // 创建返回值
        ResponseResult<Void> response = new ResponseResult<>();
        try {
            // 调⽤业务对象执⾏注册
            feedbackService.submitFeedback(gameId, category, content);
            // 响应成功
            response.setMessage("提交成功");
        } catch (InsertException e) {
            // 插⼊数据异常
            response.setMessage("提交失败，请联系系统管理员");
        }
        return response;
    }
    @RequestMapping("handle")
    public ResponseResult<Void> handleFeedback (Integer id, String handleComment) {
        ResponseResult<Void> response = new ResponseResult<>();
        try {
            feedbackService.handleFeedback(id, handleComment);
            // 响应成功
            response.setMessage("处理成功");
        } catch (InsertException e) {
            // 处理数据异常
            response.setMessage("处理失败，请联系系统管理员");
        }
        return response;
    }
}
