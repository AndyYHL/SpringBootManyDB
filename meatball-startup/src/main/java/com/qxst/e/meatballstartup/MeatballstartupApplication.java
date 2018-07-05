package com.qxst.e.meatballstartup;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling//启动定时探测
@EnableAsync//启动自定义线程池
@EnableTransactionManagement//启动事物管理
public class MeatballstartupApplication {
    /**
     * 添加第三方json工具
     * 1、需要再pom.xml加入相关以来
     * 2、需要再APP 继承 WeWebMvcConfigurerAdapter  重写configureMessageConverters
     * 3、或者使用bean注入fastJsonHttpMessageConverters
     *
     *
     * 配置fastjson支持两种方法
     * 一：1、启动继承 WebMvcConfigurerAdapter 2、覆盖方法configureMessageConverters
     * 二：使用bean注入fastJsonHttpMessageConverters
     * 这里使用@Bean注入 HttpMessageConverters
     * @Description
     * @author Administrator
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1、定义convert转换消息对象
        FastJsonHttpMessageConverter fasConverter  = new  FastJsonHttpMessageConverter();
        //2、添加fastJson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");    // 自定义时间格式
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fasConverter.setSupportedMediaTypes(fastMediaTypes);
        fasConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter = fasConverter;
        return new HttpMessageConverters(converter);
    }
    public static void main(String[] args) {
        SpringApplication.run(MeatballstartupApplication.class, args);
    }
}
