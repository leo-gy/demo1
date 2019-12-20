package com.example.demo1.aop;

import com.example.demo1.service.UserValidator;
import com.example.demo1.service.serviceImpl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @DeclareParents(value = "com.example.demo1.service.IUserService", defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    @Pointcut("execution(* com.example.demo1.service.IUserService.getUser(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        if(joinPoint!=null){
            System.out.println("**************************before param is not null***************************");
        }
        System.out.println("**************************before***************************");
    }

    @Before("pointCut()")
    public void beforeParam(JoinPoint point) {
        System.out.println("**************************beforeParam***************************");
        Object[] args=point.getArgs();
    }

    @After("pointCut())")
    public void after(JoinPoint  joinPoint) {
        System.out.println("**************************after***************************");
        if(joinPoint!=null){
            System.out.println("**************************after param is not null***************************");
        }
    }

    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("**************************afterReturning***************************");
        if(joinPoint!=null){
            System.out.println("**************************afterReturning param is not null***************************");
        }
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("**************************afterThrowing***************************");
        if(joinPoint!=null){
            System.out.println("**************************afterThrowing param is not null***************************");
        }
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("**************************around before***************************");
        jp.proceed();
        System.out.println("**************************around after***************************");
    }

}
