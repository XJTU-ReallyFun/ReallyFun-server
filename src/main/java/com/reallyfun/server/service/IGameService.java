package com.reallyfun.server.service;

import com.reallyfun.server.entity.Game;

import java.util.List;

public interface IGameService {
    /**
     * 检查游戏基本信息是否符合要求
     *
     * @param title    休息名
     * @param intro    游戏简介
     * @param tutorial 游戏玩法
     * @param entry    入口文件
     * @param nullable 以上参数是否可空
     */
    void checkInfo(String title, String intro, String tutorial, String entry, Boolean nullable);

    /**
     * 根据游戏ID检查游戏是否存在
     *
     * @param id 游戏ID
     */
    void checkExistById(Integer id);

    /**
     * 检查用户是否有某游戏的管理权限
     *
     * @param id     游戏ID
     * @param userId 用户ID
     * @param auth   用户权限值
     */
    void checkAccess(Integer id, Integer userId, Integer auth);

    /**
     * 创建游戏
     *
     * @param title    游戏名
     * @param intro    游戏简介
     * @param tutorial 游戏玩法
     * @param entry    入口文件
     * @param bundle   源文件UUID（目录名）
     * @param thumb    缩略图UUID+后缀（文件名）
     * @param userId   创建人ID
     */
    void create(
            String title,
            String intro,
            String tutorial,
            String entry,
            String bundle,
            String thumb,
            Integer userId
    );

    /**
     * 删除游戏
     *
     * @param id     游戏ID
     * @param userId 用户ID
     * @param auth   用户权限
     */
    void delete(Integer id, Integer userId, Integer auth);

    /**
     * 更新游戏信息
     *
     * @param id       游戏ID
     * @param title    游戏名
     * @param intro    游戏简介
     * @param tutorial 游戏玩法
     * @param isHidden 游戏是否被隐藏
     * @param userId   用户ID
     * @param auth     用户权限
     */
    void updateInfoById(
            Integer id,
            String title,
            String intro,
            String tutorial,
            Boolean isHidden,
            Integer userId,
            Integer auth
    );

    /**
     * 更新游戏入口文件及源文件UUID
     *
     * @param id     游戏ID
     * @param entry  入口文件
     * @param bundle 源文件UUID
     * @param userId 用户ID
     */
    void updateBundleById(Integer id, String entry, String bundle, Integer userId);

    /**
     * 更新游戏缩略图文件名
     *
     * @param id     游戏ID
     * @param thumb  缩略图文件名
     * @param userId 用户ID
     */
    void updateThumbById(Integer id, String thumb, Integer userId);

    /**
     * 根据ID获取游戏对象
     *
     * @param id 游戏ID
     * @return 游戏对象
     */
    Game findGameById(Integer id);

    /**
     * 获取符合条件的游戏列表
     *
     * @param key      排序关键字，可选"rating", "moment"
     * @param order    排序方式，升序false, 降序true
     * @param search   搜索关键字
     * @param tagId    标签ID
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 游戏列表
     */
    List<Game> findAllGameOfPage(
            String key,
            Boolean order,
            String search,
            Integer tagId,
            Integer pageSize,
            Integer pageNum
    );
}
