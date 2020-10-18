package com.study.config;

import com.study.model.GatewayPredicateDefinition;
import com.study.model.GatewayRouteDefinition;
import com.study.route.DynamicRouteServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.*;

@Slf4j
@Component
@Primary
@AllArgsConstructor
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<RouteDefinition> gatewayRouteDefinitions = dynamicRouteService.ROUTE_DEFINITION_LIST;
        List<String> routes = dynamicRouteService.ROUTE_LIST;

        gatewayRouteDefinitions.stream().filter(gatewayRouteDefinition -> routes.contains(gatewayRouteDefinition.getId())).forEach(gatewayRoute -> {
            gatewayRoute.getPredicates().stream()
                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                    .forEach(predicateDefinition -> resources.add(swaggerResource(gatewayRoute.getId(),
                            predicateDefinition.getArgs().get("pattern")
                                    .replace("**", "v2/api-docs?group=ALL"))));
        });


        //Arrays.asList("provider-router","consumer-router");
//        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
//        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId())).forEach(route -> {
//            route.getPredicates().stream()
//                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
//                    .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
//                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
//                                    .replace("**", "v2/api-docs"))));
//        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}",name,location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}