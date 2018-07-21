package com.qxst.e.meatballeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //@EnableEurekaServer注解启动服务注册中心
public class MeatballEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeatballEurekaApplication.class, args);
    }
}
