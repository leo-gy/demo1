package com.example.demo1.aop;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.service.UserValidator;
import com.example.demo1.service.serviceImpl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @DeclareParents(value = "com.example.demo1.service.serviceImpl.UserServiceImpl", defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    @Pointcut("execution(* com.example.demo1.service.serviceImpl.UserServiceImpl.getUser(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("**************************before***************************");
    }

    @Before("pointCut()")
    public void beforeParam(JoinPoint point) {
        System.out.println("**************************beforeParam***************************");
        Object[] args=point.getArgs();
    }

    @After("pointCut())")
    public void after() {
        System.out.println("**************************after***************************");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("**************************afterReturning***************************");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("**************************afterThrowing***************************");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("**************************around before***************************");
        jp.proceed();
        System.out.println("**************************around after***************************");
    }

}
