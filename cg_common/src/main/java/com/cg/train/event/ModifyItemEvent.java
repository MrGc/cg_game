package com.cg.train.event;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/21 15:27
 * @Version: 1.0.0
 */
public class ModifyItemEvent extends CgUserEvent {
    public ModifyItemEvent(int eventId, long userId) {
        super(eventId, userId);
    }
}
