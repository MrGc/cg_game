package com.cg.train.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/8 15:43
 * @Version: 1.0.0
 */
public class HttpStressTesting {

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        AtomicLong useTime = new AtomicLong(System.currentTimeMillis());
        String url = "";
        int threadNum = 2;
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {

                while (true) {
                    //todo craig 执行http

                    //todo 对执行结果进行校验

                    //qps
                    int newCount = count.addAndGet(1);
                    long now = System.currentTimeMillis();
                    if (now - useTime.get() > 1000) {
                        System.out.println("接口压测： 线程数：" + threadNum + "; QPS：" + newCount);
                        useTime.set(now);
                    }
                }

            }).start();
        }
    }
}
