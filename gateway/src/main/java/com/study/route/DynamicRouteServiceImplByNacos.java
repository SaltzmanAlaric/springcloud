package com.study.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;

@Component
public class DynamicRouteServiceImplByNacos {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    public DynamicRouteServiceImplByNacos() {

        dynamicRouteByNacosListener("gateway","DEFAULT_GROUP");
    }

    /**
     * 监听Nacos Server下发的动态路由配置
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener (String dataId, String group){
        try {
            ConfigService configService=NacosFactory.createConfigService("127.0.0.1:8848");
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
