//package com.study.common.config;
//
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.study.common.redis.GlobalHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import javax.annotation.Resource;
//
///**
// * Redis队列配置
// */
//@Configuration
//public class RedisConfig {
//
//    /**
//     * jackJSON转换对象
//     */
//    @Resource
//    private ObjectMapper objectMapper;
//
//    /**
//     * websocket配置文件
//     */
//    /*@Autowired
//    private WsProperties wsProperties;*/
//
//    /**
//     * 创建Redis监听容器
//     *
//     * @param connectionFactory reids链接工厂
//     * @return Redis监听容器
//     */
//    @Bean
//    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//                                                   MessageListenerAdapter globalListenerAdapter) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(globalListenerAdapter, new ChannelTopic("topic"));
//        return container;
//    }
//
//    /**
//     * 获取泛型集合的类型
//     *
//     * @param collectionClass 集合class
//     * @param elementClasses  集合元素的class
//     * @return 泛型集合的类型
//     */
//    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
//        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
//    }
//
//    @Bean
//    public MessageListenerAdapter globalListenerAdapter(GlobalHandler globalHandler) {
//        return new MessageListenerAdapter(globalHandler);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> globalRedis(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> globalRedis = new RedisTemplate<>();
//        globalRedis.setConnectionFactory(redisConnectionFactory);
//        globalRedis.setKeySerializer(new StringRedisSerializer());
//        globalRedis.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class));
//        return globalRedis;
//    }
//
//}
