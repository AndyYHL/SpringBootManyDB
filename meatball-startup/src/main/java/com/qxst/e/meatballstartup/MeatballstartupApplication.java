package com.qxst.e.meatballstartup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling//启动定时探测
@EnableAsync//启动自定义线程池
@EnableTransactionManagement//启动事物管理
public class MeatballstartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeatballstartupApplication.class, args);
    }
}
