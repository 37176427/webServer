package com.heasy.system.controller;

import com.heasy.common.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wf.jwtp.annotation.RequiresPermissions;

import java.io.File;
import java.util.Arrays;

/**
 * 描述 ：
 * 作者 ：WYH
 * 时间 ：2019/12/4 15:31
 **/

@Api(value = "个人信息", tags = "file")
@Controller
@RequestMapping("${api.version}/file")
public class FileController {


    @RequiresPermissions("get:/v1/file")
    @ApiOperation(value = "查询所有文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping()
    public PageResult list(String keyword){
        File dir = new File("/media/");
        File[] files = dir.listFiles();
        PageResult<File> pageResult = new PageResult<>(Arrays.asList(files));
        return pageResult;
    }
}
