package com.study.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

@Component
@Slf4j
public class DynamicRouteServiceByNacos {

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;

    @Value("${spring.cloud.nacos.config.dataId}")
    private String dataId;

    @Value("${spring.cloud.nacos.config.group}")
    private String group;

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @PostConstruct
    public void dynamicRouteByNacosListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            // 程序首次启动, 并加载初始化路由配置
            String initConfigInfo = configService.getConfig(dataId, group, 5000);
            addAndPublishBatchRoute(initConfigInfo);
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    addAndPublishBatchRoute(configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    private void addAndPublishBatchRoute(String configInfo) {
        dynamicRouteService.clear();
        try {
            List<RouteDefinition> gatewayRouteDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
            for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
                dynamicRouteService.add(routeDefinition);
            }
            dynamicRouteService.publish();
            log.info("Dynamic config gateway route finished, {}.", JSON.toJSONString(gatewayRouteDefinitions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
