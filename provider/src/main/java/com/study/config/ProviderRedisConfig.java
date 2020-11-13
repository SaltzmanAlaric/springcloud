//package com.study.config;
//
//import com.study.common.redis.RedisConfig;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableCaching
//@AutoConfigureAfter({RedisAutoConfiguration.class, RedisConfig.class})
//public class ProviderRedisConfig {
//
//    @Resource
//    private TopicChannel channel;
//
//    /**
//     * 创建Redis监听容器
//     *
//     * @param connectionFactory redis链接工厂
//     * @return Redis监听容器
//     */
//    @Bean("tsc-container")
//    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//                                                   MessageListenerAdapter listenerAdapter) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new ChannelTopic(channel.getWorkstatus()));
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(WorkstatusListener receiver) {
//        return new MessageListenerAdapter(receiver);
//    }
//
//}
