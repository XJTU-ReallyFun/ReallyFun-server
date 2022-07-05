package com.reallyfun.server.util;

import com.reallyfun.server.service.ex.FileToolException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件操作的工具类
 */
public class FileTool {
    /**
     * 上传文件
     *
     * @param file      待传文件
     * @param uploadDir 文件存储目录
     * @param filename  文件名
     * @param maxSize   文件大小上限（字节）
     * @param types     支持的文件类型
     */
    public static void uploadFile(MultipartFile file, String uploadDir, String filename, Integer maxSize, List<String> types) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            throw new FileToolException("上传的文件不允许为空");
        }

        // 判断文件大小是否超过上限
        if (file.getSize() > maxSize) {
            throw new FileToolException("上传的文件大小超过上限");
        }

        // 判断文件类型是否支持
        String fileType = file.getContentType();
        if (!types.contains(fileType)) {
            throw new FileToolException("上传的文件类型不支持");
        }

        // 若目标路径不存在则创建它
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            Boolean result = dir.mkdirs();
            if (!result) {
                throw new FileToolException("文件上传目录创建失败");
            }
        }

        // 保存文件
        try {
            File dstFile = new File(dir, filename);
            file.transferTo(dstFile);
        } catch (IOException e) {
            throw new FileToolException("文件上传失败");
        }
    }

    /**
     * 删除文件
     *
     * @param dir      文件所在目录
     * @param filename 文件名
     */
    public static Boolean deleteFile(String dir, String filename) {
        File file = new File(dir, filename);

        // 若文件不存在则返回
        // 注意：不应抛出错误，否则初始数据库-文件系统不匹配的记录会无法恢复到正常状态
        if (!file.exists()) {
            return false;
        }

        // 删除文件
        Boolean result = file.delete();

        // 若删除失败则抛出错误
        if (!result) {
            throw new FileToolException("文件删除失败");
        }
        return true;
    }
}
