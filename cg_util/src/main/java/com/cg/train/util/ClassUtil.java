package com.cg.train.util;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ClassUtil
 * @Description 类对象工具类
 * @Author craig
 * @Date 2022/10/17 23:36
 * @Version 1.0
 */
public class ClassUtil {
    private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);
    /**
     * 创建一个对象
     * @param className
     * @return
     */
    public static Object createObject(String className) {
        try {
            return ClassUtils.getClass(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
