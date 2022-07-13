package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.History;
import com.reallyfun.server.mapper.IGameMapper;
import com.reallyfun.server.mapper.IHistoryMapper;
import com.reallyfun.server.service.IHistoryService;
import com.reallyfun.server.service.ex.HistoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 处理历史记录数据的业务层实现类
 */
@Service
public class HistoryServiceImpl implements IHistoryService {
    @Autowired(required = false)
    IHistoryMapper historyMapper;

    @Autowired(required = false)
    IGameMapper gameMapper;

    @Override
    public void trigger(Integer userId, Integer gameId) {
        // 判断游戏是否存在
        if (gameMapper.findById(gameId) == null) {
            throw new HistoryException("游戏不存在");
        }

        // 构造历史记录数据
        History history = new History();
        history.setUserId(userId);
        history.setGameId(gameId);
        history.setLastMoment(new Date());
        history.modifiedBy(userId);

        // 更新并判断是否成功，若不成功则新建历史并判断是否成功
        Integer result = historyMapper.updateByIds(history);
        if (result != 1) {
            history.setTotalTime(1);
            history.createBy(userId);
            Integer result2 = historyMapper.insert(history);
            if (result2 != 1) {
                throw new HistoryException("游戏时长记录失败");
            }
        }
    }

    @Override
    public List<History> findAllHistoryOfPage(Integer userId, Integer pageSize, Integer pageNum) {
        // 限定查询范围
        Integer size = Math.min(Math.max(pageSize, 1), 100);
        Integer num = Math.max(pageNum, 1);

        // 查询并返回结果
        List<History> result = historyMapper.findAllByUserIdOfRange(
                userId, size * (num - 1), size
        );
        return result;
    }
}
