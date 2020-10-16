package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.study.common.utils.Result;
import com.study.entity.City;
import com.study.service.ICityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "信息接口")
@RestController
@RequestMapping("/city")
@ApiSupport(order = 284, author = "adm")
public class CityController {

    @Autowired
    private ICityService service;

    @ApiOperation("根据id获取路口")
    @ApiImplicitParam(name = "id", value = "路口id", required = false, dataType = "String", example = "310000")
    @GetMapping("/getTree")
    public Result getTree() {
        List<City> cityWithTree = service.getCityWithTree();
        return Result.ok().put("city", cityWithTree);
    }
}
