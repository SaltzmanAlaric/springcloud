package com.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.entity.City;

import java.util.List;

public interface ICityService extends IService<City> {

    List<City> getCityWithTree();
}
