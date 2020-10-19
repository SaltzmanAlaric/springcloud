package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.study.common.utils.Result;
import com.study.provider.CityFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@ApiSort(1)
@Api(tags = "测试接口")
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private CityFeignService cityFeignService;

    @ApiOperationSupport(order = 1, author = "test")
    @ApiOperation("获取城市信息feign")
    @GetMapping("city")
    public Result getCity(){
        return cityFeignService.getTree();
    }

    @ApiOperationSupport(order = 2, author = "tom")
    @ApiOperation("获取操作系统名称")
    @GetMapping("system-info")
    public Result getSystemInfo(){
        String system = System.getProperty("os.name");
        return Result.ok().put("osName",system);
    }

}
