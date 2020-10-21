package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.study.common.web.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiSort(2)
@Api(tags = "step-a")
@RestController
@RequestMapping("a")
public class AController {

    @ApiOperationSupport(order = 1, author = "tom")
    @ApiOperation("获取id")
    @GetMapping("id")
    public Result<String> getId() {
        return Result.success("id");
    }

    @ApiOperationSupport(order = 2, author = "judy")
    @ApiOperation("获取key")
    @GetMapping("key")
    public Result<String> getKey() {
        return Result.success("key");
    }

    @ApiOperationSupport(order = 3, author = "alice")
    @ApiOperation("获取名称")
    @PostMapping("name")
    public Result<String> getName() {
        return Result.success("name");
    }

}
