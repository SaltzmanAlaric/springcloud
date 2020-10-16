## SpringCloud整合

### 分层介绍：
- common 公共模块
- provider 微服务之一：生产者模块（默认端口8081）
- consumer 微服务之一：消费者模块（默认端口8082）
- feign-api feign模块，负责服务间通信、调用，如consumer调用provider接口
- gateway 微服务之一：网关管理，通过nacos配置中心实现动态路由（默认端口8000）
- doc 其他文档，如sql脚本等

### 框架技术：
- springboot 基础组件
- springcloud-gateway 网关
- alibaba-nacos 配置中心及服务注册(需要到官网下载对应安装包)
- springcloud-openfeign 服务通信
- mysql 数据库
- mybatisplus 持久层框架