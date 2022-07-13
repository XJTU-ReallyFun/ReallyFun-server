package com.reallyfun.server.mapper;

import com.reallyfun.server.BaseTests;
import com.reallyfun.server.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
public class GameMapperTests extends BaseTests {
    @Autowired(required = false)
    IGameMapper gameMapper;

    private List<Game> gameList;

    private Boolean generate() {
        if (gameList == null) {
            System.out.println("GENERATE");
            gameList = new ArrayList<Game>();
            for (int i = 0; i < 20; i++) {
                Game game = new Game();
                game.setUserId(randint(1, 10));
                game.setTitle(randstr());
                game.setIntro(randstr());
                game.setTutorial(randstr());
                game.setEntry(randstr());
                game.setBundle(randstr());
                game.setThumb(randstr());
                game.setIsHidden(randbool());
                if (randbool()) {
                    game.setRating(random(1., 5.));
                }
                game.setMoment(randdate());
                game.createBy(game.getUserId());
                gameList.add(game);
            }
            return true;
        }
        return false;
    }

    @Test
    public void insert() {
        generate();
        System.out.println("INSERT");
        for (Game game : gameList) {
            Integer result = gameMapper.insert(game);
            assert result == 1;
        }
    }

    @Test
    public void deleteById() {
        if (generate()) {
            insert();
        }
        System.out.printf("DELETE");
        for (Game game : gameList) {
            if (randbool()) {
                System.out.println("DEL GAME = " + game);
                Integer result = gameMapper.deleteById(game.getId());
                assert result == 1;
            }
        }
        gameList = null;
    }

    @Test
    public void updateById() {
        if (generate()) {
            insert();
        }
        System.out.println("UPDATE");
        Game game = new Game();
        game.setId(gameList.get(0).getId());
        game.setTitle(randstr());
        game.setIntro(randstr());
        game.setTutorial(randstr());
        game.setEntry(randstr());
        game.setBundle(randstr());
        game.setThumb(randstr());
        game.modifiedBy(gameList.get(0).getUserId());
        System.out.println("OLD " + gameList.get(0));
        System.out.println("NEW " + game);
        assert 1 == gameMapper.updateById(game);
    }

    @Test
    public void existByTitle() {
        if (generate()) {
            insert();
        }
        System.out.printf("EXIST");
        assert null != gameMapper.existByTitle(gameList.get(0).getTitle());
        assert null == gameMapper.existByTitle(randstr());
    }

    @Test
    public void findById() {
        if (generate()) {
            insert();
        }
        System.out.printf("FIND");
        assert null != gameMapper.findById(gameList.get(0).getId());
        assert null == gameMapper.findById(-1);
    }

    @Test
    public void findAllGameOfRange() {
        if (generate()) {
            insert();
        }
        System.out.printf("FINDALL");

        List<Game> result = gameMapper.findAllGameOfRange(null, null, null, null, 5, 5);
        System.out.println("(NO CONDITIONS)");
        for (Game game : result) {
            System.out.println(game);
        }

        result = gameMapper.findAllGameOfRange("rating", null, null, null, 5, 100);
        System.out.println("(KEY = RATING)");
        for (Game game : result) {
            System.out.println(game);
        }

        result = gameMapper.findAllGameOfRange("rating", true, null, null, 5, 100);
        System.out.println("(KEY = RATING, ORDER = TRUE)");
        for (Game game : result) {
            System.out.println(game);
        }

        result = gameMapper.findAllGameOfRange("moment", null, null, null, 5, 5);
        System.out.println("(KEY = MOMENT)");
        for (Game game : result) {
            System.out.println(game);
        }

        result = gameMapper.findAllGameOfRange("moment", true, "0", null, 5, 5);
        System.out.println("(KEY = MOMENT, ORDER = TRUE, SEARCH = '0')");
        for (Game game : result) {
            System.out.println(game);
        }

        result = gameMapper.findAllGameOfRange(null, null, null, 1, 0, 5);
        System.out.println("(TAGID = 1)");
        for (Game game : result) {
            System.out.println(game);
        }
    }
}
