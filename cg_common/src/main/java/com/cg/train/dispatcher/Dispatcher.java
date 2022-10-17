package com.cg.train.dispatcher;

import com.cg.train.annotation.Cmd;
import com.cg.train.annotation.Controller;
import com.cg.train.annotation.JsonBean;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/17 16:04
 * @Version: 1.0.0
 */
@Slf4j
public class Dispatcher {
    private static final Dispatcher instance = new Dispatcher();
    private Dispatcher() {}
    public static final Dispatcher getInstance() {
        return instance;
    }

    private final Map<String, Commander> commanders = new HashMap<>();

    public Pack invoke(long playerId, int componentId, int cmdId, String data) throws Exception {
        //todo craig
        String cmd = componentId + "_" + cmdId;
        Commander commander = commanders.get(cmd);
        if (commander == null) {
            return null;
        }
        long begin = System.currentTimeMillis();

        log.info("客户端协议内容：player:{}, cmd: {}, param:{}", playerId, cmd,  data);
        Object[] argsValues = new Object[2];
        long used = System.currentTimeMillis() - begin;
        log.debug("协议[{}]处理完成，耗时{}ms", cmd, used);
        return null;
    }

    /**
     * 扫描并加载类
     * @param basePackage
     */
    public synchronized void load(String basePackage) {
        Set<Class<?>> classes = new HashSet<>();
        commanders.clear();
        classes.forEach(cls -> {
            //扫描到controller层，协议入口都是在controller类下面
            if (cls.isAnnotationPresent(Controller.class)) {
                try {
                    Object o = cls.getDeclaredConstructor().newInstance();
                    Method[] methods = cls.getDeclaredMethods();
                    for (Method method : methods) {
                        Cmd cmd = method.getAnnotation(Cmd.class);
                        if (cmd != null) {
                            commanders.put(cmd.cmd(), new Commander(o, method));
                        }
                    }
                } catch (Exception e) {
                    log.error("协议[" + cls + "]加载出错!!!", e);
                }
            }
            if (cls.isAnnotationPresent(JsonBean.class)) {
                try {
                    JsonBean jsonBean = cls.getAnnotation(JsonBean.class);
                    String s = jsonBean.jsonPath();

                    Object o = cls.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    log.error("json[" + cls + "]加载出错！！！", e);
                }
            }
        });
    }
}
