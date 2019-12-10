package com.example.demo1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class DataBind {

    @ModelAttribute(name = "mp")
    public Map<String, String> mapBind() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("sex", "男");
        return map;
    }
}
