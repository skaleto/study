package com.skaleto.things.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class TestCirculor {


    public TestCirculor() {

    }

    @Test
    public void test() throws UnknownHostException {
        Class<?> claz = InetAddress.class;
        Class<?>[] innerClaz=claz.getClasses();
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
    }
}
