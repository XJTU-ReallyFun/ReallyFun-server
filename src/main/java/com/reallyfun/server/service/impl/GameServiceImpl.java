package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Game;
import com.reallyfun.server.mapper.IGameMapper;
import com.reallyfun.server.service.IGameService;
import com.reallyfun.server.service.ex.GameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 处理游戏数据的业务层实现类
 */
@Service
public class GameServiceImpl implements IGameService {
    @Autowired(required = false)
    IGameMapper gameMapper;

    private static final Integer MAX_TITLE_LENGTH = 20;
    private static final Integer MAX_INTRO_LENGTH = 400;
    private static final Integer MAX_TUTORIAL_LENGTH = 400;
    private static final Integer MAX_ENTRY_LENGTH = 20;

    @Override
    public void checkInfo(String title, String intro, String tutorial, String entry, Boolean nullable) {
        // 检查是否为空
        if (!nullable) {
            if (title == null || intro == null || tutorial == null || entry == null) {
                throw new GameException("游戏信息项不可为空");
            }
        }

        // 检查游戏标题
        if (title != null && (title.isEmpty() || MAX_TITLE_LENGTH < title.length())) {
            throw new GameException("游戏标题字数不符合要求");
        }
        if (gameMapper.existByTitle(title) != null) {
            throw new GameException("游戏名已存在");
        }

        // 检查游戏简介
        if (intro != null && (intro.isEmpty() || MAX_INTRO_LENGTH < intro.length())) {
            throw new GameException("游戏简介字数不符合要求");
        }

        // 检查游戏玩法
        if (tutorial != null && (tutorial.isEmpty() || MAX_TUTORIAL_LENGTH < tutorial.length())) {
            throw new GameException("游戏玩法字数不符合要求");
        }

        // 检查游戏入口文件名
        if (entry != null && (entry.isEmpty() || MAX_ENTRY_LENGTH < entry.length())) {
            throw new GameException("游戏入口文件名长度不符合要求");
        }
    }

    @Override
    public void checkExistById(Integer id) {
        Game game = gameMapper.findById(id);
        if (game == null) {
            throw new GameException("游戏不存在");
        }
    }

    @Override
    public void checkAccess(Integer id, Integer userId, Integer auth) {
        Game game = gameMapper.findById(id);
        if (game == null) {
            throw new GameException("游戏不存在");
        }
        if (!game.getUserId().equals(userId) && auth < 2) {
            throw new GameException("权限不足");
        }
    }

    @Override
    public void create(
            String title,
            String intro,
            String tutorial,
            String entry,
            String bundle,
            String thumb,
            Integer userId
    ) {
        // 构造游戏数据（此前需检查数据合法性）
        Game game = new Game();
        game.setUserId(userId);
        game.setTitle(title);
        game.setIntro(intro);
        game.setTutorial(tutorial);
        game.setEntry(entry);
        game.setBundle(bundle);
        game.setThumb(thumb);
        game.setIsHidden(false);
        game.setMoment(new Date());
        game.createBy(userId);

        // 插入并检查是否成功
        Integer result = gameMapper.insert(game);
        if (result != 1) {
            throw new GameException("游戏创建失败");
        }
    }

    @Override
    public void delete(Integer id, Integer userId, Integer auth) {
        // 判断游戏是否存在及当前用户是否有权删除该游戏
        checkAccess(id, userId, auth);

        // 删除游戏并判断是否成功
        Integer result = gameMapper.deleteById(id);
        if (result != 1) {
            throw new GameException("游戏删除失败");
        }
    }

    @Override
    public void updateInfoById(
            Integer id,
            String title,
            String intro,
            String tutorial,
            Boolean isHidden,
            Integer userId,
            Integer auth
    ) {
        // 检查数据合法性
        checkInfo(title, intro, tutorial, null, true);

        // 检查游戏是否存在及是否有权限
        checkAccess(id, userId, auth);

        // 构造游戏数据
        Game gameData = new Game();
        gameData.setId(id);
        gameData.setTitle(title);
        gameData.setIntro(intro);
        gameData.setTutorial(tutorial);
        gameData.setIsHidden(isHidden);
        gameData.modifiedBy(userId);

        // 更新并判断是否成功
        Integer result = gameMapper.updateById(gameData);
        if (result != 1) {
            throw new GameException("游戏信息更新失败");
        }
    }

    @Override
    public void updateBundleById(Integer id, String entry, String bundle, Integer userId) {
        // 构造游戏数据（此前需检查游戏是否存在，以及是否有权限）
        Game gameData = new Game();
        gameData.setId(id);
        gameData.setBundle(bundle);
        gameData.modifiedBy(userId);

        // 更新并判断是否成功
        Integer result = gameMapper.updateById(gameData);
        if (result != 1) {
            throw new GameException("游戏源文件上传失败");
        }
    }

    @Override
    public void updateThumbById(Integer id, String thumb, Integer userId) {
        // 构造游戏数据（此前需检查游戏是否存在，以及是否有权限）
        Game gameData = new Game();
        gameData.setId(id);
        gameData.setThumb(thumb);
        gameData.modifiedBy(userId);

        // 更新并判断是否成功
        Integer result = gameMapper.updateById(gameData);
        if (result != 1) {
            throw new GameException("游戏缩略图上传失败");
        }
    }

    @Override
    public Game findGameById(Integer id) {
        // 获取游戏对象，判断是否成功并返回数据
        Game game = gameMapper.findById(id);
        if (game == null) {
            throw new GameException("游戏不存在");
        }
        return game;
    }

    private static final List<String> KEY_OPTIONS = new ArrayList<>();

    static {
        KEY_OPTIONS.add("rating");
        KEY_OPTIONS.add("moment");
    }

    @Override
    public List<Game> findAllGameOfPage(
            String key,
            Boolean order,
            String search,
            Integer tagId,
            Integer pageSize,
            Integer pageNum
    ) {
        // 判断排序关键字是否合法
        if (key != null && !KEY_OPTIONS.contains(key)) {
            throw new GameException("游戏排序关键字无效");
        }

        // 限定查询范围
        Integer size = Math.min(Math.max(pageSize, 1), 100);
        Integer num = Math.max(pageNum, 1);

        // 获取范围内符合筛选条件的游戏并返回
        List<Game> result = gameMapper.findAllGameOfRange(
                key, order, search, tagId, size * (num - 1), size
        );
        return result;
    }
}
