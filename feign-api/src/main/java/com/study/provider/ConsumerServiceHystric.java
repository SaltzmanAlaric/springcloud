package com.study.provider;

import com.study.common.utils.Result;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceHystric implements CityFeignService {
    @Override
    public Result getTree() {
        return Result.error("city error");
    }
}
