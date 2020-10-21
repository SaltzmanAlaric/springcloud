package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@EnableDiscoveryClient
@RestController
@RequestMapping
@MapperScan("com.study.dao")
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    /**
     * 【knife4j】个性化配置参数说明
     * <p>
     * 1.i18n国际化支持:lang=en lang可选择：中文(zh)、English(en)
     * 2.开启请求参数缓存：cache=1
     * 3.菜单Api地址显示: showMenuApi=1
     * 4.分组tag显示dsecription说明属性: showDes=1
     * 5.开启RequestMapping接口过滤,默认只显示: filterApi=1 filterApiType=post
     * 6.开启缓存已打开的api文档:cacheApi=1
     * 7.启用SwaggerBootstrapUi提供的增强功能:plus=1
     */
    @GetMapping("doc")
    void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("doc.html?plus=1&cache=1&lang=zh&showMenuApi=1&showDes=1");
    }

    /**
     * 默认按顺序轮询(负载均衡)
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
