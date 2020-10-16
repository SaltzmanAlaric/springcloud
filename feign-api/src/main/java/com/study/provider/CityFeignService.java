package com.study.provider;

import com.study.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("provider") //服务名
public interface CityFeignService {

    @GetMapping("/city/getTree")
    Result getTree();
}
