package com.study.provider;

import com.study.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider", fallback = ConsumerServiceHystric.class) //服务名
public interface CityFeignService {

    @GetMapping("city/tree")
    Result getTree();

}
