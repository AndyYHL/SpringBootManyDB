package com.qxst.e.meatballconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer  //配置中心服务
@EnableDiscoveryClient //注册客户端
public class MeatballConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeatballConfigApplication.class, args);
    }
}
