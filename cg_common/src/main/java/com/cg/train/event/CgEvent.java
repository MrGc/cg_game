package com.cg.train.event;

/**
 * @Description: 事件接口
 * @Author: Craig
 * @Date: 2022/10/21 15:04
 * @Version: 1.0.0
 */
public abstract class CgEvent {
    protected final int eventId;

    public CgEvent(int eventId) {
        this.eventId = eventId;
    }
}
