package com.study.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.study.common.redis.RedisUtils;
import com.study.common.web.Result;
import com.study.provider.CityFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@ApiSort(1)
@Api(tags = "测试接口")
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private CityFeign cityFeign;
    @Resource
    private RedisUtils redisUtils;

    @ApiOperationSupport(order = 1, author = "test")
    @ApiOperation("获取城市信息feign")
    @GetMapping("city")
    public Result getCity(){
        return cityFeign.getTree();
    }

    @ApiOperationSupport(order = 2, author = "test")
    @ApiOperation("获取id feign")
    @GetMapping("id")
    public Result getId(){
        return cityFeign.getId();
    }

    @ApiOperationSupport(order = 3, author = "tom")
    @ApiOperation("获取操作系统名称")
    @GetMapping("system-info")
    public Result<String> getSystemInfo(){
        String system = System.getProperty("os.name");
        return Result.success("获取操作系统名称成功", system);
    }

    @ApiOperationSupport(order = 4, author = "test")
    @ApiOperation("获取redis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "键", required = true, dataType = "String", paramType = "path", example = "id")
    })
    @GetMapping("redis/{key}")
    public Result getRedis(@PathVariable("key")String key){
        return Result.success("获取redis键的值成功", redisUtils.get(key));
    }
}
