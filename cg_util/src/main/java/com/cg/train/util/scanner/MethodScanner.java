package com.cg.train.util.scanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName MethodScanner
 * @Description 方法扫描接口
 * @Author craig
 * @Date 2022/10/20 21:07
 * @Version 1.0
 */
public interface MethodScanner {
    /**
     * 获取一个类上方法名符合正则的所有的方法
     *
     * @param clazz clazz
     * @param methodPattern methodPattern
     * @return List
     */
    List<Method> getMethodList(Class<?> clazz, String methodPattern);

    /**
     * 获取一个类上有期望Annotation的所有方法
     *
     * @param clazz clazz
     * @param annotationClass annotationClass
     * @return List
     */
    List<Method> getMethodListByAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass);

    /**
     * 获取一个类及接口类上有期望Annotation的所有方法
     *
     * @param clazz clazz
     * @param annotationClass annotationClass
     * @return {@link Method }
     */
    List<Method> getMethodListByAnnotationInterface(Class<?> clazz, Class<? extends Annotation> annotationClass);
}
