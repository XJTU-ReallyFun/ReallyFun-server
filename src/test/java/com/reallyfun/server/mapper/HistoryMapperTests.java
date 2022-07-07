package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class HistoryMapperTests {
    @Autowired(required = false)
    private HistoryMapper historyMapper;

    @Test
    public void insert(){
        History history=new History();
        history.setUserId(2);
        history.setGameId(1);
        history.setTotalTime(1);
        history.setLastMonent(new Date());
        history.setCreatedUser(2);
        history.setCreatedTime(new Date());
        history.setModifiedUser(2);
        history.setModifiedTime(new Date());
        Integer rows = historyMapper.insert(history);
        System.out.println("rows=" + rows);
    }
    @Test
    public void findByUserid() {
        Integer userId= 7;
        History history = historyMapper.findByUserid(userId);
        System.out.println(history);

    }
    @Test
    public void update(){
        Integer userId=7;
        Integer gameId=1;
        History history= historyMapper.findByUserid(userId);
        history.setTotalTime(history.getTotalTime());
        history.setLastMonent(new Date());
        history.setModifiedUser(2);
        history.setModifiedTime(new Date());
        Integer rows = historyMapper.update(history);
        System.out.println("rows=" + rows);

    }

}
