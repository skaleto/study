package com.skaleto.things.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

    public B (@Autowired A a){

    }
}
