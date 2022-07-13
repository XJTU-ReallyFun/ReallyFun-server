package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Game;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IGameService;
import com.reallyfun.server.service.ex.FileToolException;
import com.reallyfun.server.service.ex.GameException;
import com.reallyfun.server.util.FileTool;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

/**
 * 处理游戏相关请求的控制器类
 */
@RestController
public class GameController extends BaseController {
    @Autowired
    IGameService gameService;

    @Value("${game.thumb.upload-dir}")
    private String thumbUploadDir;

    @Value("${game.bundle.tmp-dir}")
    private String bundleTmpDir;

    @Value("${game.bundle.upload-dir}")
    private String bundleUploadDir;

    /**
     * 缩略图文件大小的上限值（2MB）
     */
    private static final Integer THUMB_MAX_SIZE = 2 * 1024 * 1024;

    /**
     * 允许上传的缩略图的文件类型
     */
    public static final List<String> THUMB_TYPES = new ArrayList<>();

    /** 初始化允许上传的缩略图的文件类型 */
    static {
        THUMB_TYPES.add("image/jpeg");
        THUMB_TYPES.add("image/png");
        THUMB_TYPES.add("image/bmp");
        THUMB_TYPES.add("image/gif");
    }

    /**
     * 源代码文件大小的上限值（100MB）
     */
    private static final Integer BUNDLE_MAX_SIZE = 100 * 1024 * 1024;

    /**
     * 允许上传的源代码文件类型
     */
    public static final List<String> BUNDLE_TYPES = new ArrayList<>();

    /** 初始化允许上传的源代码文件类型 */
    static {
        BUNDLE_TYPES.add("application/zip");
        BUNDLE_TYPES.add("application/octet-stream");
        BUNDLE_TYPES.add("application/x-zip-compressed");
        BUNDLE_TYPES.add("multipart/x-zip");
    }

    private void tryDelete(String dir, String filename) {
        try {
            FileTool.deleteFile(dir, filename);
        } catch (FileToolException e) {
        }
    }

    /**
     * 创建游戏
     *
     * @param title      游戏名
     * @param intro      游戏简介
     * @param tutorial   游戏玩法
     * @param entry      入口文件
     * @param bundleFile 源文件.zip
     * @param thumbFile  缩略图文件
     * @param session    HttpSession对象
     * @return 响应结果
     */
    @PostMapping("/game")
    public ResponseResult<Void> create(
            String title,
            String intro,
            String tutorial,
            String entry,
            @RequestParam("bundle") MultipartFile bundleFile,
            @RequestParam("thumb") MultipartFile thumbFile,
            HttpSession session
    ) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 判断用户是否有游戏上传权限
        if (user.getAuth() < 1) {
            throw new GameException("您无权上传游戏");
        }

        // 检查数据合法性
        gameService.checkInfo(title, intro, tutorial, entry, false);

        // 生成目标文件名
        String thumbName, bundleName;
        try {
            thumbName = FileTool.randomFileName(thumbFile);
            bundleName = FileTool.randomFileName(bundleFile);
        } catch (FileToolException e) {
            throw new GameException("目标文件名生成错误：" + e.getMessage());
        }

        // 检查文件信息
        try {
            FileTool.checkFile(thumbFile, THUMB_MAX_SIZE, THUMB_TYPES);
        } catch (FileToolException e) {
            throw new GameException("游戏缩略图文件有误：" + e.getMessage());
        }
        try {
            FileTool.checkFile(bundleFile, BUNDLE_MAX_SIZE, BUNDLE_TYPES);
        } catch (FileToolException e) {
            throw new GameException("游戏源文件有误：" + e.getMessage());
        }

        // 尝试存储缩略图，若失败则尝试删除
        try {
            FileTool.uploadFile(
                    thumbFile, thumbUploadDir, thumbName, THUMB_MAX_SIZE, THUMB_TYPES
            );
        } catch (FileToolException e) {
            tryDelete(thumbUploadDir, thumbName);
            throw new GameException("缩略图上传失败");
        }

        // 尝试存储.zip文件，若失败则尝试删除缩略图和源文件.zip
        try {
            FileTool.uploadFile(
                    bundleFile, bundleTmpDir, bundleName, BUNDLE_MAX_SIZE, BUNDLE_TYPES
            );
        } catch (FileToolException e) {
            tryDelete(thumbUploadDir, thumbName);
            tryDelete(bundleTmpDir, bundleName);
            throw new GameException("游戏源文件上传失败");
        }

        // 生成.zip输出目录
        String zipUUID = UUID.randomUUID().toString();
        String zipOutputDir = bundleUploadDir + zipUUID;

        // 尝试检查入口文件并解压.zip文件，并尝试删除源文件.zip
        try {
            FileTool.checkEntryFileAndUnzip(
                    bundleTmpDir, bundleName, zipOutputDir, entry
            );
            tryDelete(bundleTmpDir, bundleName);
        } catch (FileToolException e) {
            // 若失败则尝试删除缩略图和源文件.zip
            tryDelete(thumbUploadDir, thumbName);
            tryDelete(bundleTmpDir, bundleName);
            throw new GameException("游戏源文件解压失败：" + e.getMessage());
        }

        // 向数据库插入游戏数据
        try {
            gameService.create(title, intro, tutorial, entry, zipUUID, thumbName, user.getId());
        } catch (GameException e) {
            // 若失败则尝试删除缩略图和源文件.zip
            tryDelete(thumbUploadDir, thumbName);
            tryDelete(bundleTmpDir, bundleName);

            // 重新抛出错误
            throw e;
        }

        // 返回响应结果
        return ResponseResult.getResponseResult("游戏创建成功！");
    }

    /**
     * 上传源文件
     *
     * @param id         游戏ID
     * @param entry      入口文件
     * @param bundleFile 源文件.zip
     * @param session    HttpSession
     * @return 响应结果
     */
    @PostMapping("/game/bundle")
    public ResponseResult<Void> bundle(
            Integer id,
            String entry,
            @RequestParam("bundle") MultipartFile bundleFile,
            HttpSession session
    ) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 检查访问权限
        gameService.checkAccess(id, user.getId(), user.getAuth());

        // 检查数据合法性
        gameService.checkInfo(null, null, null, entry, true);

        // 生成目标文件名
        String bundleName;
        try {
            bundleName = FileTool.randomFileName(bundleFile);
        } catch (FileToolException e) {
            throw new GameException("目标文件名生成错误：" + e.getMessage());
        }

        // 检查文件信息
        try {
            FileTool.checkFile(bundleFile, BUNDLE_MAX_SIZE, BUNDLE_TYPES);
        } catch (FileToolException e) {
            throw new GameException("游戏源文件有误：" + e.getMessage());
        }

        // 尝试存储.zip文件，若失败则尝试删除源文件.zip
        try {
            FileTool.uploadFile(
                    bundleFile, bundleTmpDir, bundleName, BUNDLE_MAX_SIZE, BUNDLE_TYPES
            );
        } catch (FileToolException e) {
            tryDelete(bundleTmpDir, bundleName);
            throw new GameException("游戏源文件上传失败");
        }

        // 生成.zip输出目录
        String zipUUID = UUID.randomUUID().toString();
        String zipOutputDir = bundleUploadDir + zipUUID;

        // 尝试检查入口文件并解压.zip文件，并尝试删除源文件.zip
        try {
            FileTool.checkEntryFileAndUnzip(
                    bundleTmpDir, bundleName, zipOutputDir, entry
            );
            tryDelete(bundleTmpDir, bundleName);
        } catch (FileToolException e) {
            // 若失败则尝试删除源文件.zip
            tryDelete(bundleTmpDir, bundleName);
            throw new GameException("游戏源文件解压失败：" + e.getMessage());
        }

        // 更新游戏数据
        try {
            gameService.updateBundleById(id, entry, zipUUID, user.getId());
        } catch (GameException e) {
            // 若失败则尝试删除源文件.zip
            tryDelete(bundleTmpDir, bundleName);

            // 重新抛出错误
            throw e;
        }

        // TODO: 删除旧的文件目录，或定期清理无用目录

        // 返回响应结果
        return ResponseResult.getResponseResult("源文件及入口文件名更新成功！");
    }

    /**
     * 上传缩略图文件
     *
     * @param id        游戏ID
     * @param thumbFile 缩略图文件
     * @param session   HttpSession对象
     * @return 响应结果
     */
    @PostMapping("/game/thumb")
    public ResponseResult<Void> thumb(
            Integer id,
            @RequestParam("thumb") MultipartFile thumbFile,
            HttpSession session
    ) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 检查访问权限
        gameService.checkAccess(id, user.getId(), user.getAuth());

        // 生成目标文件名
        String thumbName;
        try {
            thumbName = FileTool.randomFileName(thumbFile);
        } catch (FileToolException e) {
            throw new GameException("目标文件名生成错误：" + e.getMessage());
        }

        // 检查文件信息
        try {
            FileTool.checkFile(thumbFile, THUMB_MAX_SIZE, THUMB_TYPES);
        } catch (FileToolException e) {
            throw new GameException("游戏缩略图文件有误：" + e.getMessage());
        }

        // 尝试存储缩略图，若失败则尝试删除
        try {
            FileTool.uploadFile(
                    thumbFile, thumbUploadDir, thumbName, THUMB_MAX_SIZE, THUMB_TYPES
            );
        } catch (FileToolException e) {
            tryDelete(thumbUploadDir, thumbName);
            throw new GameException("缩略图上传失败");
        }

        // 更新游戏数据
        try {
            gameService.updateThumbById(id, thumbName, user.getId());
        } catch (GameException e) {
            // 若失败则尝试删除缩略图
            tryDelete(thumbUploadDir, thumbName);

            // 重新抛出错误
            throw e;
        }

        // TODO: 删除旧的文件，或定期清理

        // 返回响应结果
        return ResponseResult.getResponseResult("游戏缩略图更新成功！");
    }

    /**
     * 删除游戏
     *
     * @param id      游戏ID
     * @param session HttpSession对象
     * @return 响应结果
     */
    @DeleteMapping("/game/{id}")
    public ResponseResult<Void> delete(
            @PathVariable("id") Integer id,
            HttpSession session
    ) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 删除游戏
        gameService.delete(id, user.getId(), user.getAuth());

        // 返回响应结果
        return ResponseResult.getResponseResult("游戏删除成功！");
    }

    /**
     * 更新游戏信息
     *
     * @param game    游戏对象
     * @param session HttpSession
     * @return 响应结果
     */
    @PatchMapping("/game")
    public ResponseResult<Void> update(
            @RequestBody Game game,
            HttpSession session
    ) {
        // 获取当前用户对象
        User user = getUserFromSession(session);

        // 更新游戏数据
        gameService.updateInfoById(
                game.getId(),
                game.getTitle(),
                game.getIntro(),
                game.getTutorial(),
                game.getIsHidden(),
                user.getId(),
                user.getAuth()
        );

        // 返回响应结果
        return ResponseResult.getResponseResult("游戏信息更新成功！");
    }

    /**
     * 根据游戏ID获取某个游戏信息
     *
     * @param id 游戏ID
     * @return 游戏信息
     */
    @GetMapping("/game/{id}")
    public ResponseResult<Game> findById(@PathVariable("id") Integer id) {
        // 获取游戏对象并返回结果
        Game game = gameService.findGameById(id);
        return ResponseResult.getResponseResult(game);
    }

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
    @GetMapping("/games")
    public ResponseResult<List<Game>> findAllOfPage(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Boolean order,
            @RequestParam(required = false) String search,
            @RequestParam(value = "tag_id", required = false) Integer tagId,
            @RequestParam("page_size") Integer pageSize,
            @RequestParam("page_num") Integer pageNum
    ) {
        List<Game> gameList = gameService.findAllGameOfPage(
                key, order, search, tagId, pageSize, pageNum
        );
        return ResponseResult.getResponseResult(gameList);
    }
}
