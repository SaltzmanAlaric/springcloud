package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.study.common.utils.Result;
import com.study.entity.City;
import com.study.service.ICityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "mam-b")
@RestController
@RequestMapping("/b")
@ApiSort(1)
public class BController {

    @ApiOperation("获取名称")
    @ApiOperationSupport(order = 1, author = "mam")
    @PostMapping("name")
    public Result getName() {
        return Result.ok().put("b", "name");
    }

    @ApiOperation("获取key")
    @ApiOperationSupport(order = 2, author = "mam")
    @GetMapping("key")
    public Result getKey() {
        return Result.ok().put("b", "key");
    }

    @ApiOperation("获取id")
    @ApiOperationSupport(order = 3, author = "mam")
    @GetMapping("id")
    public Result getId() {
        return Result.ok().put("b", "id");
    }

}
