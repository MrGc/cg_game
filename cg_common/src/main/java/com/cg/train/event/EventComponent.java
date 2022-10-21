package com.cg.train.event;

import com.cg.train.annotation.Listener;
import com.cg.train.db.entry.User;
import com.cg.train.dispatcher.Commander;
import com.cg.train.dispatcher.Dispatcher;
import com.cg.train.lifecyle.AbstractInit;
import com.cg.train.util.scanner.DefaultClassScanner;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/21 14:25
 * @Version: 1.0.0
 */
public class EventComponent extends AbstractInit {
    private final Map<String, List<Commander>> map = new ConcurrentHashMap<>();
    @Override
    protected void doInit() {
        Set<Class<?>> classes = DefaultClassScanner.getInstance().getClassList("com.cg.train", null);
        for (Class<?> cls : classes) {
            Object bean = Dispatcher.getInstance().getBean(cls);
            if (bean == null) {
                continue;
            }
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Listener.class)) {

                }
            }
        }
    }

    public void sendEvent(long userId, CgUserEvent event) {
        List<Commander> objects = map.get(event.eventId);
        if (objects == null) {
            log.warn("why not find event lister, eventId = {}", event.eventId);
            return;
        }

        try {
            for (Commander commander : objects) {
                commander.method().invoke(commander.o(), userId, event);
            }
        } catch (Exception e) {
            log.error("", e);
        }


    }
}
