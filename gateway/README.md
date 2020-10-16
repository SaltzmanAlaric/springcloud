## 1、简介
&emsp;&emsp;Spring Cloud Gateway 提供了内置的端点，用来提供路由相关的操作，如获取过滤器列表、路由列表、单个路由信息等。Spring Cloud Gateway 的内置API纳入了Spring Boot Actuator 中，所以需要引用 spring-boot-starter-actuator 的依赖。

## 2、Spring Cloud Gateway 内置API
Spring Cloud Gateway 提供了多个内置API， 如下为 Spring Cloud Gateway 的主要 API 端点。

- /actuator/gateway/routes/{id}， methods=[ DELETE]， 删除单个路由

- /actuator/gateway/routes/{id}， methods=[ POST]， 新增单个路由

- /actuator/gateway/routes/{id}， methods=[ GET]， 查看单个路 由。

- /actuator/gateway/routes， methods=[ GET]， 获取路由列表

- /actuator/gateway/refresh， methods=[ POST]， 路由刷新

- /actuator/gateway/globalfilters， methods=[ GET]， 获取全局过滤器列表

- /actuator/gateway/routefilters， methods=[ GET]， 路由过滤器工厂列表

- /actuator/gateway/routes/{id}/combinedfilters， methods=[ GET]， 获取 单个路由的联合过滤器

这些路由的API实现均在 GatewayControllerEndpoint