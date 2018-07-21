package com.qxst.e.meatballgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MeatballgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeatballgatewayApplication.class, args);
    }
}
