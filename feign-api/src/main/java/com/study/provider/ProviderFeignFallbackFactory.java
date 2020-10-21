package com.study.provider;

import com.study.provider.CityFeign;
import com.study.common.web.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;

@Component
@Slf4j(topic = "【provider-feign】")
public class ProviderFeignFallbackFactory implements FallbackFactory<CityFeign>{

    @Override
    public CityFeign create(Throwable arg0) {
        log.error("调用异常：{}", arg0.getMessage());
        return new CityFeign(){
            @Override
            public Result getTree() {
                return Result.error("服务失效，降级处理:city error");
            }

            @Override
            public Result<String> getId() {
                return Result.error("服务失效，降级处理:id error");
            }
        };
    }
}
