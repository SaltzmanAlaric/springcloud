package com.study.config;

import com.study.route.DynamicRouteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
@Primary
@AllArgsConstructor
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<RouteDefinition> gatewayRouteDefinitions = DynamicRouteService.ROUTE_DEFINITION_LIST;
        List<String> routes = DynamicRouteService.ROUTE_LIST;

        gatewayRouteDefinitions.stream().filter(gatewayRouteDefinition -> routes.contains(gatewayRouteDefinition.getId())).sorted(Comparator.comparing(RouteDefinition::getOrder)).forEach(gatewayRoute -> {
            gatewayRoute.getPredicates().stream()
                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                    .forEach(predicateDefinition -> resources.add(swaggerResource(gatewayRoute.getId(),
                            predicateDefinition.getArgs().get("pattern")
                                    .replace("**", "v2/api-docs"))));
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}