package com.cg.train.annotation;

/**
 * @Description: 监听事件
 * @Author: Craig
 * @Date: 2022/10/21 14:20
 * @Version: 1.0.0
 */
public @interface Listener {
    /**
     * 事件类型
     * @return
     */
    String[] eventType();

    /**
     * 是否在玩家线程
     * @return
     */
    boolean needUser();
}
