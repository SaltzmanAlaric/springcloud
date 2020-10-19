package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.study.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiSort(2)
@Api(tags = "haha-a")
@RestController
@RequestMapping("a")
public class AController {

    @ApiOperationSupport(order = 1, author = "haha")
    @ApiOperation("获取id")
    @GetMapping("id")
    public Result getId() {
        return Result.ok().put("a", "id");
    }

    @ApiOperationSupport(order = 2, author = "haha")
    @ApiOperation("获取key")
    @GetMapping("key")
    public Result getKey() {
        return Result.ok().put("a", "key");
    }

    @ApiOperationSupport(order = 3, author = "haha")
    @ApiOperation("获取名称")
    @PostMapping("name")
    public Result getName() {
        return Result.ok().put("a", "name");
    }

}
