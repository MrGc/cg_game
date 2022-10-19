package com.cg.train.lifecyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName AbstractInit
 * @Description
 * @Author craig
 * @Date 2022/10/19 23:01
 * @Version 1.0
 */
public abstract class AbstractInit implements IInit{
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final AtomicBoolean init = new AtomicBoolean();

    @Override
    public void init() {
        if (init.compareAndSet(false, true)) {
            log.debug("Init {}", this);
            doInit();
        }
    }

    /**
     * 实际初始化的执行方法，一般应用调用初始化调用init()方法
     */
    protected abstract void doInit();
}
