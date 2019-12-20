package com.example.demo1;

import com.zaxxer.hikari.pool.HikariProxyConnection;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.demo1.mapper"})
@EnableAspectJAutoProxy
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @PostConstruct
    public void PostConstruct(){
        System.out.println("aaaaaaaaa");
    }


}
