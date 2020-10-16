package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

//消费端目前没有服务提供,不用查数据库,但是依赖了common,common有数据库连接,所以先排除
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients({"com.study.provider"})
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
