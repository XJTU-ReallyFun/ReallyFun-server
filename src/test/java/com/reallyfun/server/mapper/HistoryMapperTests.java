package com.reallyfun.server.mapper;

import com.reallyfun.server.BaseTests;
import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.entity.History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class HistoryMapperTests extends BaseTests {
    @Autowired(required = false)
    private IHistoryMapper historyMapper;

    private List<History> historyList;

    private Boolean generate() {
        if (historyList == null) {
            System.out.println("GENERATE");
            historyList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                History history = new History();
                history.setUserId(randint(1, 100));
                history.setGameId(randint(1, 2));
                history.setTotalTime(1);
                history.setLastMoment(new Date());
                history.createBy(history.getUserId());
                historyList.add(history);
            }
            return true;
        }
        return false;
    }

    @Test
    public void insert() {
        generate();
        System.out.println("INSERT");
        for (History history : historyList) {
            Integer result = historyMapper.insert(history);
            assert result == 1;
        }
    }

    @Test
    public void updateByIds() {
        if (generate()) {
            insert();
        }
        System.out.println("UPDATE");
        for (History history : historyList) {
            Integer t = randint(0, 5);
            System.out.println("t = " + t + " " + history);
            for (int i = 0; i < t; i++) {
                History h = new History();
                h.setUserId(history.getUserId());
                h.setGameId(history.getGameId());
                h.setLastMoment(new Date());
                h.modifiedBy(history.getUserId());
                Integer result = historyMapper.updateByIds(h);
                assert result == 1;
            }
        }
    }

    @Test
    public void findByIds() {
        if (generate()) {
            insert();
        }
        System.out.println("FIND");
        System.out.println(historyList.get(0));
        History history = historyMapper.findByIds(
                historyList.get(0).getUserId(),
                historyList.get(0).getGameId()
        );
        System.out.println(history);
    }

    @Test
    public void findAllByUserIdOfRange() {
        if (generate()) {
            insert();
        }
        System.out.println("FINDALL");
        System.out.println(historyList.get(0));
        List<History> histories = historyMapper.findAllByUserIdOfRange(
                historyList.get(0).getUserId(), 0, 10
        );
        System.out.println(histories);
    }
}
