package com.qxst.e.meatballstartup.theadPoolConfig;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by wuhaochao on 2017/7/25.
 */
@Component
public class AsyncTranscationTask {

    //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Async("myTaskAsyncPool")
    public void insertTransTask(int i) throws InterruptedException {
        //开始导入交易信息
        String method_BlockByNumber = "eth_getBlockByNumber";
        //批量插入
        //transcationMapper.insertTranscationList(list);
    }
}
