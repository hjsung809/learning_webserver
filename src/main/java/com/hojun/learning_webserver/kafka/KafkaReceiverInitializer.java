package com.hojun.learning_webserver.kafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

@Profile("kafka")
@Component
@Slf4j
public class KafkaReceiverInitializer {
    @Value("${kafka.bootstrapAddress}")
    String bootstrapServer;

    @Value("${message.topic.name}")
    private String topicName;

    @PostConstruct
    public void init() {
        Map<String, Object> consumerProps = new HashMap<>();
        List<String> bootstrapServers = List.of(bootstrapServer);
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        ReceiverOptions<String, String> receiverOptions =
                ReceiverOptions.<String, String>create(consumerProps)
                               .subscription(Collections.singleton(topicName));

        Flux<ReceiverRecord<String, String>> inboundFlux =
                KafkaReceiver.create(receiverOptions)
                             .receive();

        inboundFlux.subscribe(r -> {
            log.info("Received message: " + r.key() + " : " + r.value());
            r.receiverOffset().acknowledge();
        });
    }
}
