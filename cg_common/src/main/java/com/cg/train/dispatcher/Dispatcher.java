package com.cg.train.dispatcher;

import com.cg.train.annotation.Cmd;
import com.cg.train.annotation.Controller;
import com.cg.train.annotation.JsonBean;
import com.cg.train.util.ClassUtil;
import com.cg.train.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/17 16:04
 * @Version: 1.0.0
 */
public class Dispatcher {
    public static final Logger log = LoggerFactory.getLogger(Dispatcher.class);
    private static final Dispatcher instance = new Dispatcher();
    private Dispatcher() {}
    public static Dispatcher getInstance() {
        return instance;
    }

    private final Map<String, Commander> commanders = new HashMap<>();
    //todo craig
//    private final Map<String,>

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
        Set<Class<?>> classes = FileUtil.getClasses(basePackage);
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
                    if (Arrays.stream(cls.getInterfaces()).anyMatch(p -> p == IReload.class)) {
                        IReload reloadClass = (IReload) ClassUtil.createObject(cls.getName());
                        reloadClass.reload();
                    }

                } catch (Exception e) {
                    log.error("json[" + cls + "]加载出错！！！", e);
                }
            }
        });
    }
}
