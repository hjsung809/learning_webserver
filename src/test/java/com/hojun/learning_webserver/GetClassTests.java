package com.hojun.learning_webserver;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetClassTests {
    public static class Parent {
        public void printClass() {
            log.info(this.getClass().getName());
        }
    }

    public static class Child extends Parent{

    }

    @Test
    public void test() {
        Parent obj = new Child();
        obj.printClass();
    }
}
