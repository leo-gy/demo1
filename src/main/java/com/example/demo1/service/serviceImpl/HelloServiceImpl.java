package com.example.demo1.service.serviceImpl;

import com.example.demo1.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if (name == null || name.equals("")) {
            throw new RuntimeException("name不能为空");
        }
        System.out.println("Hello," + name);
    }
}
