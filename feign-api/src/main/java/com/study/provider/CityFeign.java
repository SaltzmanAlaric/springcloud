package com.study.provider;

import com.study.common.web.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 服务名：provider
 */
@Component
@FeignClient(name = "provider", fallbackFactory = ProviderFeignFallbackFactory.class)
public interface CityFeign {

    @GetMapping("city/tree")
    Result getTree();

    @GetMapping("a/id")
    Result<String> getId();
}
