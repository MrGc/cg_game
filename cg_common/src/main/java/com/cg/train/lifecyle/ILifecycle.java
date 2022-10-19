package com.cg.train.lifecyle;

/**
 * @ClassName ILifecycle
 * @Description
 * @Author craig
 * @Date 2022/10/19 22:58
 * @Version 1.0
 */
public interface ILifecycle extends IInit {
    /**
     * start
     */
    void start();

    /**
     * stop
     */
    void stop();

    /**
     * whether is running?
     * @return true if component is running or false;
     */
    boolean isRunning();


}
