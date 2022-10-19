package com.cg.train.dispatcher;

import com.cg.train.annotation.*;
import com.cg.train.util.ClassUtil;
import com.cg.train.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/17 16:04
 * @Version: 1.0.0
 */
public class Dispatcher {
    public static final Logger log = LoggerFactory.getLogger(Dispatcher.class);
    private static final Dispatcher INSTANCE = new Dispatcher();
    private Dispatcher() {}
    public static Dispatcher getInstance() {
        return INSTANCE;
    }

    private final Map<String, Commander> commanders = new HashMap<>();
    /** 游戏对象 key-注解的对象类名, value-object */
    private final Map<String, Object> gameBean = new ConcurrentHashMap<>();

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
        gameBean.clear();
        classes.forEach(cls -> {
            //扫描到controller层，协议入口都是在controller类下面
            if (cls.isAnnotationPresent(Controller.class)) {
                loadController(cls);
            }
            if (cls.isAnnotationPresent(Service.class)) {
                loadService(cls);
            }
            if (cls.isAnnotationPresent(Dao.class)) {
                loadDao(cls);
            }
            if (cls.isAnnotationPresent(JsonBean.class)) {
                loadJson(cls);
            }
        });

        //装配gameBean
        autowiredGameBean();
    }

    private void autowiredGameBean() {
        for (Object bean : gameBean.values()) {
            Class<?> beanClass = bean.getClass();
            Field[] fields = beanClass.getFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    boolean b = field.canAccess(bean);
                    field.setAccessible(true);
                    Object fieldBean = getBean(field.getType());
                    if (fieldBean == null) {
                        log.warn("Autowired对象不存在：{}", field.getType());
                        continue;
                    }
                    try {
                        field.set(bean, fieldBean);
                    } catch (Exception e) {
                        log.error("Autowired对象装配实拍", e);
                    }
                    field.setAccessible(b);
                }
            }
        }
    }

    private void loadDao(Class<?> cls) {
        try {
            Object o = ClassUtil.createObject(cls.getName());
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> inter : interfaces) {
                //缓存游戏对象
                gameBean.put(inter.getName(), o);

            }
            gameBean.put(cls.getName(), o);
        } catch (Exception e) {
            log.error("Service 【" + cls + "】 加载出错！！！", e);
        }
    }

    private void loadService(Class<?> cls) {
        try {
            Object o = ClassUtil.createObject(cls.getName());
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> inter : interfaces) {
                //缓存游戏对象
                gameBean.put(inter.getName(), o);

            }
        } catch (Exception e) {
            log.error("Service 【" + cls + "】 加载出错！！！", e);
        }
    }

    private void loadJson(Class<?> cls) {
        try {
            if (Arrays.stream(cls.getInterfaces()).anyMatch(p -> p == IReload.class)) {
                Object o = ClassUtil.createObject(cls.getName());
                //缓存游戏对象
                gameBean.put(cls.getName(), o);
                //reload json配置
                ((IReload)o).reload();
            }

        } catch (Exception e) {
            log.error("json[" + cls + "]加载出错！！！", e);
        }
    }

    private void loadController(Class<?> cls) {
        try {
            Object o = ClassUtil.createObject(cls.getName());
            //缓存游戏对象
            gameBean.put(cls.getName(), o);
            //缓存协议对象
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

    public <T> T getBean(Class<T> cls) {
        Object o = gameBean.get(cls.getName());
        if (o == null) {
            return null;
        }
        return (T)o;
    }
}
