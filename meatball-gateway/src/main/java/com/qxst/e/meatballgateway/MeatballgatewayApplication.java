package com.qxst.e.meatballgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //注册客户端
public class MeatballgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeatballgatewayApplication.class, args);
    }
}
