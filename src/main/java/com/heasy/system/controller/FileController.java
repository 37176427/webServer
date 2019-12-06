package com.heasy.system.controller;

import com.heasy.common.PageResult;
import com.heasy.common.enums.FileUnitEnum;
import com.heasy.system.model.MyFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wf.jwtp.annotation.RequiresPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述 ：
 * 作者 ：WYH
 * 时间 ：2019/12/4 15:31
 **/

@Api(value = "个人信息", tags = "file")
@RestController
@RequestMapping("${api.version}/file")
public class FileController {
    //文件单位变化值
    private static final long UNITF = 1024L;

    @RequiresPermissions("get:/v1/file")
    @ApiOperation(value = "查询所有文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping()
    public PageResult<MyFile> list(int page,int limit,MyFile myFile) {
        File dir = new File("/media/");
        File[] files = dir.listFiles(File::isFile);
        List<MyFile> resultList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                MyFile model = new MyFile();
                String name = file.getName();
                String type = "";
                if (name.contains(".")) {
                    type = name.substring(name.lastIndexOf(".")+1);
                }
                double length = file.length();
                int unitNum = 0;
                while ((length /= UNITF) >= UNITF) {
                    unitNum++;
                }
                String l = String.format("%.1f", length);
                String size = l + FileUnitEnum.getUnitById(++unitNum);
                long lastModified = file.lastModified();
                Date date = new Date(lastModified);
                model.setName(name);
                model.setSize(size);
                model.setType(type);
                model.setDate(date);
                resultList.add(model);
                System.out.println(model);
            }
        }
        return new PageResult<>(resultList);
    }
}

