package com.heasy.common.utils;

import com.heasy.common.enums.FileUnitEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 描述 ：
 * 作者 ：WYH
 * 时间 ：2019/12/6 15:46
 **/
@Slf4j
public class FileUtil {

    //文件单位变化值
    private static final long UNITF = 1024L;

    private static String WINFILEDIR = "D:/media/";
    private static String LINUXFILEDIR = "/media/";

    private static String dir;

    static {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            dir = WINFILEDIR;
        } else {
            dir = LINUXFILEDIR;
        }
    }

    /**
     * 获取文件创建时间
     */
    public static Long getFileCreateTime(File file) {
        try {
            Path path = Paths.get(file.getCanonicalPath());
            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();
            return attr.creationTime().toMillis();
        } catch (Exception e) {
            log.error("获取时间出错：" + e);
            return file.lastModified();
        }
    }

    /**
     * 根据文件长获取格式化后的文件大小
     */
    public static String getFileSize(double length) {
        int unitNum = 0;
        while ((length /= UNITF) >= UNITF) {
            unitNum++;
        }
        String l = String.format("%.1f", length);
        return l + FileUnitEnum.getUnitById(++unitNum);
    }

    /**
     * 获取文件存放路径
     */
    public static String getDir() {
        return dir;

    }
}
