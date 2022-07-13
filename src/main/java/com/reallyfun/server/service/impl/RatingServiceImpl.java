package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Rating;
import com.reallyfun.server.mapper.IGameMapper;
import com.reallyfun.server.mapper.IRatingMapper;
import com.reallyfun.server.service.IRatingService;
import com.reallyfun.server.service.ex.RatingException;
import com.reallyfun.server.service.ex.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired(required = false)
    private IRatingMapper ratingMapper;

    @Autowired(required = false)
    private IGameMapper gameMapper;

    public void rate(Integer gameId, Integer userId, Integer value) {
        // 判断评分值是否合法
        if (value < 1 || 5 < value) {
            throw new RatingException("无效的评分值");
        }

        // 判断游戏是否存在
        if (gameMapper.findById(gameId) == null) {
            throw new TagException("游戏不存在");
        }

        // 判断用户是否对该游戏评分过
        if (ratingMapper.existByIds(gameId, userId) != null) {
            throw new RatingException("您已评分");
        }

        // 构造评分数据
        Rating rating = new Rating();
        rating.setGameId(gameId);
        rating.setUserId(userId);
        rating.setRating(value);
        rating.createBy(userId);

        // 插入并判断是否成功
        Integer result = ratingMapper.insert(rating);
        if (result != 1) {
            throw new RatingException("评分失败");
        }

        // 更新游戏评分缓存
        gameMapper.updateRatingById(gameId);
    }
}
