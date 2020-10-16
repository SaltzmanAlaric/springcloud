package com.study.controller;

import com.study.common.utils.Result;
import com.study.entity.City;
import com.study.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService service;

    @GetMapping("/getTree")
    public Result getTree(){
        List<City> cityWithTree = service.getCityWithTree();
        return Result.ok().put("city",cityWithTree);
    }
}
