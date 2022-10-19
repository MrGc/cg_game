package com.cg.train.lifecyle;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName AbstractLifecycle
 * @Description
 * @Author craig
 * @Date 2022/10/19 23:09
 * @Version 1.0
 */
public abstract class AbstractLifecycle extends AbstractInit implements ILifecycle{
    private AtomicBoolean running = new AtomicBoolean();

    @Override
    public void start() {
        if (running.compareAndSet(false, true)) {
            log.debug("Start {}", this);
            doStart();
        }
    }

    @Override
    public void stop() {
        if (running.compareAndSet(true, false)) {
            log.debug("Stop {}", this);
            doStop();
        }
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    protected void doInit() {

    }

    /**
     * do start thing
     */
    protected abstract void doStart();

    /**
     * do stop thing
     */
    protected abstract void doStop();
}
