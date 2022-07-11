package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.mapper.FeedbackMapper;
import com.reallyfun.server.service.IFeedbackService;
import com.reallyfun.server.service.ex.FeedbackNotFoundException;
import com.reallyfun.server.service.ex.InsertException;
import com.reallyfun.server.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void submitFeedback(Integer gameId, String category, String content) {
        // 调⽤持久层Integer insert(User user)⽅法，执⾏注册并获取返回值(受影响的⾏数)
        Integer rows = feedbackMapper.submitFeedback(gameId,category,content);
        // 判断受影响的⾏数是否不为1
        if (rows != 1) {
            // 是：插⼊数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加⽤户数据出现未知错误，请联系系统管理员");
        }
    }
    @Override
    public void deleteFeedback(Integer id) {
        Feedback feedback = feedbackMapper.findById(id);
        // 检查查询结果是否为null
        if (feedback == null) {
            // 是：抛出UserNotFoundException异常
            throw new FeedbackNotFoundException("数据不存在");
        }
        Integer rows = feedbackMapper.deleteFeedback(id);
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("删除反馈时出现未知错误，请联系系统管理员");
        }
    }
    @Override
    public void handleFeedback(Integer id, String handleComment) {
        Feedback feedback = feedbackMapper.findById(id);
        // 检查查询结果是否为null
        if (feedback == null) {
            // 是：抛出UserNotFoundException异常
            throw new FeedbackNotFoundException("数据不存在");
        }
        Integer rows = feedbackMapper.handleFeedback(id,handleComment);
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("处理反馈时出现未知错误，请联系系统管理员");
        }

    }
    @Override
    public List<Feedback> getFeedback(Integer pageSize, Integer pageNum, Integer userId) {
        List<Feedback> list = feedbackMapper.findByUid(userId);
        List<Feedback> list1 = new ArrayList<>();
        if (list.isEmpty()) {
            throw new FeedbackNotFoundException("反馈不存在");
        }
        for (Integer item = pageNum*(pageSize - 1); item < pageNum*pageSize; item++ )
        {
            Feedback f = list.get(item);
            list1.add(f);
        }
        return list1;
    }
}
