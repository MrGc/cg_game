package com.cg.train.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;

/**
 * @ClassName ClassUtil
 * @Description 类对象工具类
 * @Author craig
 * @Date 2022/10/17 23:36
 * @Version 1.0
 */
@Slf4j
public class ClassUtil {
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
