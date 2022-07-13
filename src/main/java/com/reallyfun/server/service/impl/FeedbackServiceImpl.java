package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.mapper.IFeedbackMapper;
import com.reallyfun.server.mapper.IGameMapper;
import com.reallyfun.server.service.IFeedbackService;
import com.reallyfun.server.service.ex.FeedbackException;
import com.reallyfun.server.service.ex.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired(required = false)
    private IFeedbackMapper feedbackMapper;

    @Autowired(required = false)
    private IGameMapper gameMapper;

    private static final Integer MAX_CONTENT_LENGTH = 200;
    private static final Integer MAX_HANDLE_COMMENT_LENGTH = 200;
    private static final Integer MAX_CATEGORY_VALUE = 5;

    @Override
    public void insert(Integer userId, Integer gameId, Integer category, String content) {
        // 判断游戏是否存在
        if (gameMapper.findById(gameId) == null) {
            throw new TagException("游戏不存在");
        }

        // 判断是否为合法category
        if (category < 0 || MAX_CATEGORY_VALUE < category) {
            throw new FeedbackException("无效的反馈分类");
        }

        // 判断content字数是否合法
        if (content.isEmpty() || MAX_CONTENT_LENGTH < content.length()) {
            throw new FeedbackException("反馈内容字数不符合要求");
        }

        // 构造反馈数据
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setGameId(gameId);
        feedback.setCategory(category);
        feedback.setContent(content);
        feedback.createBy(userId);

        // 插入并判断是否成功
        Integer result = feedbackMapper.insert(feedback);
        if (result != 1) {
            throw new FeedbackException("反馈失败");
        }
    }

    @Override
    public void delete(Integer id, Integer userId) {
        // 判断反馈是否存在
        Feedback feedback = feedbackMapper.findById(id);
        if (feedback == null) {
            throw new FeedbackException("反馈不存在");
        }

        // 判断是否为当前用户提交的反馈
        if (!feedback.getUserId().equals(userId)) {
            throw new FeedbackException("您无权撤销该反馈");
        }

        // 判断是否已被处理
        if (feedback.getHandlerId() != null) {
            throw new FeedbackException("无法撤销已处理的反馈");
        }

        // 删除并判断是否成功
        Integer result = feedbackMapper.deleteById(id);
        if (result != 1) {
            throw new FeedbackException("反馈撤销失败");
        }
    }

    @Override
    public void handle(Integer id, Integer handlerId, String handleComment) {
        // 判断反馈是否存在
        Feedback feedback = feedbackMapper.findById(id);
        if (feedback == null) {
            throw new FeedbackException("反馈不存在");
        }

        // 判断反馈是否已被处理
        if (feedback.getHandlerId() != null) {
            throw new FeedbackException("反馈已被处理");
        }

        // 判断handleComment字数是否合法
        if (handleComment.isEmpty() || handleComment.length() > MAX_HANDLE_COMMENT_LENGTH) {
            throw new FeedbackException("反馈意见字数不符合要求");
        }

        // 构造反馈数据
        feedback.setHandlerId(handlerId);
        feedback.setHandleComment(handleComment);
        feedback.modifiedBy(handlerId);

        // 处理反馈并判断是否成功
        Integer result = feedbackMapper.updateHandleInfoById(feedback);
        if (result != 1) {
            throw new FeedbackException("反馈处理失败");
        }
    }

    @Override
    public List<Feedback> findAllFeedbackOfPage(
            Integer userId,
            Integer gameId,
            Integer category,
            Boolean isHandled,
            Integer pageSize,
            Integer pageNum
    ) {
        // 限定查询范围
        Integer size = Math.min(Math.max(pageSize, 1), 100);
        Integer num = Math.max(pageNum, 1);

        // 查询并返回数据
        List<Feedback> result = feedbackMapper.findAllFeedbackOfRange(
                userId, gameId, category, isHandled, size * (num - 1), size
        );
        return result;
    }
}
