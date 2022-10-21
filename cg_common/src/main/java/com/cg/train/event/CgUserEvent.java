package com.cg.train.event;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/21 15:29
 * @Version: 1.0.0
 */
public class CgUserEvent extends CgEvent{
    protected final long userId;

    public CgUserEvent(int eventId, long userId) {
        super(eventId);
        this.userId = userId;
    }
}
