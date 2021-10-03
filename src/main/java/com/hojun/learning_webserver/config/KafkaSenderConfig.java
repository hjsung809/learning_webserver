package com.hojun.learning_webserver.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Profile("kafka")
@Configuration
public class KafkaSenderConfig {
    @Value("${kafka.bootstrapAddress}")
    String bootstrapServer;

    @Bean
    public KafkaSender<String, String> kafkaSender() {
        Map<String, Object> producerProps = new HashMap<>();

        List<String> bootstrapServers = List.of(bootstrapServer);
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        SenderOptions<String, String> senderOptions = SenderOptions.<String, String>create(producerProps)
                .maxInFlight(1024);
        return KafkaSender.create(senderOptions);
    }
}
