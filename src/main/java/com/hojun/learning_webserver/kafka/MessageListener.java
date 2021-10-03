package com.hojun.learning_webserver.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Profile("kafka")
@RequiredArgsConstructor
@Component
public class MessageListener {
    private final ConcurrentKafkaListenerContainerFactory fooKafkaListenerContainerFactory;

    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Object partition,
                               @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Object key,
                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Object time,
                               @Header(KafkaHeaders.RECEIVED) Object received
                               ) {
        System.out.println("Received Message in group 'foo':" + message);
        System.out.println("## key : " + key.toString());
        System.out.println("## partition : " + partition.toString());
        System.out.println("## time : " + time.toString());
        System.out.println("## received : " + received.toString());
    }
}
