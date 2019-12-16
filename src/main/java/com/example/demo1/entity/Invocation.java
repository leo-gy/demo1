package com.example.demo1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Invocation {

    private Object[] params;

    private Method method;

    private Object target;

    public Invocation(Object[] params, Method method, Object target) {
        this.params = params;
        this.method = method;
        this.target = target;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }
}
