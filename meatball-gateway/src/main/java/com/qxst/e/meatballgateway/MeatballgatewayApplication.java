package com.qxst.e.meatballgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableEurekaClient
public class MeatballgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeatballgatewayApplication.class, args);
    }
}
