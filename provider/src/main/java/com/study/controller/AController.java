package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.study.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "haha-a")
@RestController
@RequestMapping("/a")
@ApiSupport(order = 2, author = "test")
public class AController {

    @ApiOperation("获取名称")
    @ApiOperationSupport(order = 3)
    @PostMapping("name")
    public Result getName() {
        return Result.ok().put("a", "name");
    }

    @ApiOperation("获取id")
    @ApiOperationSupport(order = 1)
    @GetMapping("id")
    public Result getId() {
        return Result.ok().put("a", "id");
    }

    @ApiOperation("获取key")
    @ApiOperationSupport(order = 2)
    @GetMapping("key")
    public Result getKey() {
        return Result.ok().put("a", "key");
    }
}
