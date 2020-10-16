package com.study.controller;

import com.study.common.utils.Result;
import com.study.provider.CityFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private CityFeignService cityFeignService;

    @GetMapping("/getCity")
    public Result getCity(){
        return cityFeignService.getTree();
    }

    @GetMapping("/getSystemInfo")
    public Result getSystemInfo(){
        String system = System.getProperty("os.name");
        return Result.ok().put("osName",system);
    }

}
