package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.study.common.web.Result;
import com.study.entity.City;
import com.study.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@ApiSort(284)
@Api(tags = "信息接口")
@RestController
@RequestMapping("city")
public class CityController {

    @Resource
    private CityService service;

    @ApiOperationSupport(order = 1, author = "admin")
    @ApiOperation("获取城市信息")
    @ApiImplicitParam(name = "id", value = "城市id", required = false, dataType = "String", example = "310000")
    @GetMapping("tree")
    public Result<List<City>> getTree() {
        List<City> cityWithTree = service.getCityWithTree();
        return Result.success( "获取城市树级数据成功", cityWithTree);
    }
}
