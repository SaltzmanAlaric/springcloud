package com.study.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

@Component
public class DynamicRouteServiceImplByNacos {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;

    @Value("${spring.cloud.nacos.discovery.dataId}")
    private String dataId = "gateway";

    @Value("${spring.cloud.nacos.discovery.group}")
    private String group = "DEFAULT_GROUP";

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    public DynamicRouteServiceImplByNacos() {

        dynamicRouteByNacosListener(dataId, group);
    }

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
            System.out.println("Dynamic config gateway route finished. " + JSON.toJSONString(gatewayRouteDefinitions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /**
         * 监听Nacos Server下发的动态路由配置
         * @param dataId
         * @param group
         */
    public void dynamicRouteByNacosListener (String dataId, String group){
        try {
            ConfigService configService=NacosFactory.createConfigService(serverAddr);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("进行网关更新:" + configInfo);
                    List<RouteDefinition> definitions = JSON.parseArray(configInfo, RouteDefinition.class);
                    for (RouteDefinition definition : definitions) {
                        System.out.println("update route : " + definition.toString());
                        dynamicRouteService.update(definition);
                    }
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            System.out.println("从nacos接收动态路由配置出错!!!");
            e.printStackTrace();
        }
    }

}
