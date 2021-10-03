package com.hojun.learning_webserver.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaAdmin;

@Profile("kafka")
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value("${message.topic.name}")
    private String topicName;

    // 설정 정보 주입
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    // 자동 토픽 생성.
    @Bean
    public NewTopic myTopic(){
        return new NewTopic(topicName, 1, (short)1 );
    }
}
