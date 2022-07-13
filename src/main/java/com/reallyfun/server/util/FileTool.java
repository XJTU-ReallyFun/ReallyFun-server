package com.reallyfun.server.util;

import com.reallyfun.server.service.ex.FileToolException;
import com.reallyfun.server.service.ex.UserException;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 文件操作的工具类
 */
public class FileTool {
    /**
     * 根据原文件名随机生成带原有后缀的文件名
     *
     * @param file 文件对象
     * @return 生成的文件名
     */
    public static String randomFileName(MultipartFile file)
            throws FileToolException {
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex == -1) {
            throw new FileToolException("文件名没有后缀");
        }
        String suffix = originalFilename.substring(beginIndex);
        String filename = UUID.randomUUID().toString() + suffix;
        return filename;
    }

    /**
     * 检查文件基本信息
     *
     * @param file    文件对象
     * @param maxSize 文件大小上限（字节）
     * @param types   支持的文件类型
     * @throws FileToolException
     */
    public static void checkFile(MultipartFile file, Integer maxSize, List<String> types)
            throws FileToolException {
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
    }

    /**
     * 上传文件
     *
     * @param file      待传文件
     * @param uploadDir 文件存储目录
     * @param filename  文件名
     * @param maxSize   文件大小上限（字节）
     * @param types     支持的文件类型
     * @throws FileToolException
     */
    public static void uploadFile(MultipartFile file, String uploadDir, String filename, Integer maxSize, List<String> types)
            throws FileToolException {
        // 检查文件基本信息
        checkFile(file, maxSize, types);

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
     * @return 是否删除成功
     * @throws FileToolException
     */
    public static Boolean deleteFile(String dir, String filename)
            throws FileToolException {
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

    /**
     * 解压缩文件并检查入口文件是否存在
     *
     * @param dir           待解压文件所在目录
     * @param filename      待解压文件名
     * @param outputDir     解压目录
     * @param entryFilename 入口文件名
     * @throws FileToolException
     */
    public static void checkEntryFileAndUnzip(
            String dir,
            String filename,
            String outputDir,
            String entryFilename
    ) throws FileToolException {
        File file = new File(dir, filename);

        // 若文件不存在则返回
        if (!file.exists()) {
            throw new FileToolException("待解压文件不存在");
        }

        try {
            ZipFile zipFile = new ZipFile(file);

            // 检查压缩文件是否有效
            if (!zipFile.isValidZipFile()) {
                throw new FileToolException("压缩文件无效");
            }

            // 检查入口文件是否存在
            List<FileHeader> fileHeaders = zipFile.getFileHeaders();
            Boolean flag = false;
            for (FileHeader header : fileHeaders) {
                if (header.getFileName().equals(entryFilename)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                throw new FileToolException("入口文件不存在");
            }

            // 解压缩文件
            zipFile.extractAll(outputDir);
        } catch (ZipException e) {
            throw new FileToolException("文件解压失败");
        }
    }
}
