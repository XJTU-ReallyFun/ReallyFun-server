package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Game;

import java.util.Date;
import java.util.List;

/**
 * 处理游戏操作的持久层接口
 */
public interface IGameMapper {
    /**
     * 新增游戏
     *
     * @param game 游戏对象
     * @return 受影响的行数
     */
    Integer insert(Game game);

    /**
     * 根据游戏ID删除游戏
     *
     * @param id 游戏ID
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

    /**
     * 根据游戏ID更新游戏信息
     *
     * @param game 游戏对象
     * @return 受影响的行数
     */
    Integer updateById(Game game);

    /**
     * 根据游戏ID重新计算并更新评分
     *
     * @param id 游戏ID
     * @return 受影响的行数
     */
    Integer updateRatingById(Integer id);

    /**
     * 判断是否存在标题为title的游戏
     *
     * @param title 游戏标题
     * @return 若存在则返回1，否则返回null
     */
    Integer existByTitle(String title);

    /**
     * 根据游戏ID查询游戏信息
     *
     * @param id 游戏ID
     * @return 游戏对象
     */
    Game findById(Integer id);

    /**
     * 获取符合条件的范围内的游戏列表
     *
     * @param key    排序关键字，可选"rating", "moment"
     * @param order  排序方式，升序false, 降序true
     * @param search 搜索关键字
     * @param tagId  标签ID
     * @param offset 偏移量
     * @param limit  记录数
     * @return 游戏列表
     */
    List<Game> findAllGameOfRange(
            String key,
            Boolean order,
            String search,
            Integer tagId,
            Integer offset,
            Integer limit
    );
}
