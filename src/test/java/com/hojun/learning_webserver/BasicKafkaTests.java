package com.hojun.learning_webserver;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hojun.learning_webserver.kafka.MessageListener;
import com.hojun.learning_webserver.kafka.MessageProducer;

@SpringBootTest
public class BasicKafkaTests {
    @Autowired
    MessageProducer messageProducer;

    @Autowired
    MessageListener messageListener;

    @Test
    public void test() throws Exception{
        messageProducer.sendMessage("Hello foo!");
        messageProducer.sendMessage("Hello foo! 1");
        messageProducer.sendMessage("Hello foo! 2");
    }
}
