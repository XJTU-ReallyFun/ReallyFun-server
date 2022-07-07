package com.reallyfun.server.service;

import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.service.ex.InsertException;
import com.reallyfun.server.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class FeedbackServiceTests {
    @Autowired
    private IFeedbackService iFeedbackService;
    @Test
    public void submitFeedback() {
        try {
            Integer gameId = 1;
            String category = "好评";
            String content = "nice!!!";
            iFeedbackService.submitFeedback(gameId,category,content);
            System.out.println("提交成功！");
        } catch (ServiceException e) {
            System.out.println("添加失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void handleFeedback() {
        try {
            Integer id = 6;
            String handleComment = "我反对";
            iFeedbackService.handleFeedback(id, handleComment);
            System.out.println("处理成功！");
        } catch (ServiceException e) {
            System.out.println("处理失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
