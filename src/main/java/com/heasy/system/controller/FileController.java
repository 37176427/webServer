package com.heasy.system.controller;

import com.heasy.common.JsonResult;
import com.heasy.common.PageResult;
import com.heasy.common.enums.FileUnitEnum;
import com.heasy.common.utils.FileUtil;
import com.heasy.system.model.MyFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wf.jwtp.annotation.RequiresPermissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述 ：
 * 作者 ：WYH
 * 时间 ：2019/12/4 15:31
 **/

@Api(value = "文件管理", tags = "file")
@RestController
@RequestMapping("${api.version}/file")
@Slf4j
public class FileController {

    @RequiresPermissions("get:/v1/file")
    @ApiOperation(value = "查询所有文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping()
    public PageResult<MyFile> list(int page, int limit, MyFile myFile) {
        if (0 == page) {
            page = 1;
        }
        if (limit == 0) {
            limit = 20;
        }
        String path = FileUtil.getDir();
        File dir = new File(path);
        File[] files;
        if (myFile == null) {
            files = dir.listFiles(File::isFile);
        } else {
            files = dir.listFiles((file) -> {
                String name = "", type = "";
                if (StringUtils.isNotBlank(myFile.getName())) {
                    name = myFile.getName();
                }
                if (StringUtils.isNotBlank(myFile.getType())) {
                    type = myFile.getType().toLowerCase();
                }
                return file.isFile() && file.getName().contains(name) && file.getName().endsWith(type);
            });
        }
        List<MyFile> resultList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                MyFile model = new MyFile();
                String name = file.getName();
                String type = "";
                if (name.contains(".")) {
                    type = name.substring(name.lastIndexOf(".") + 1);
                }
                double length = file.length();
                String size = FileUtil.getFileSize(length);
                long time = FileUtil.getFileCreateTime(file);
                Date date = new Date(time);
                model.setName(name);
                model.setSize(size);
                model.setType(type);
                model.setDate(date);
                resultList.add(model);
            }
        }
        return new PageResult<>(resultList.subList((page - 1) * limit, Math.min(page * limit, resultList.size())), resultList.size());
    }


    /**
     * 上传文件
     */
    @RequiresPermissions("post:/v1/file")
    @PostMapping()
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
        if (null == file) {
            return JsonResult.error("文件为空");
        }
        double length = file.getSize();
        if (length == 0) {
            return JsonResult.error("文件为空");
        }
        String name = file.getOriginalFilename();
        log.info("开始上传文件：{} ,文件容量：{}", name, FileUtil.getFileSize(length));
        String dir = FileUtil.getDir();
        File f = new File(dir, name);
        if (f.exists() && f.isFile()){
            return JsonResult.error("文件名重复！");
        }
        try {
            file.transferTo(f);
        } catch (IOException e) {
            log.error("传输过程出错：{}", name);
            return JsonResult.error("上传出错：" + e);
        }
        log.info("上传文件成功：{} ,文件容量：{}", name, FileUtil.getFileSize(length));
        return JsonResult.ok("上传成功：" + name);
    }
}

