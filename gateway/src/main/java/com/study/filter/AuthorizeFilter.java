package com.study.filter;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {

    public static final String API_URI = "/v2/api-docs";

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String url = request.getURI().getPath();
/*        if (url.indexOf("/login") >= 0){
            return chain.filter(exchange);
        }*/

        if (url.indexOf(API_URI) >= 0){  //  <------------------------------
            return chain.filter(exchange);
        }
//        String authorization = request.getHeaders().getFirst(tokenSign);
        //后续业务逻辑代码....
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
