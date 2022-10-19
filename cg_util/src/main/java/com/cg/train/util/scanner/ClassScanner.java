package com.cg.train.util.scanner;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @ClassName ClassScanner
 * @Description
 * @Author craig
 * @Date 2022/10/20 00:09
 * @Version 1.0
 */
public interface ClassScanner {

    /**
     * 获取制定包名中所有的类
     * @param packageName
     * @param packagePattern
     * @return
     */
    Set<Class<?>> getClassList(String packageName, String packagePattern);

    /**
     * 自定义ClassLoader中获取指定包名中的所有类
     *
     * @param packageName packageName
     * @param packagePattern packagePattern
     * @param classLoader classLoader
     * @return Set
     */
    Set<Class<?>> getClassList(String packageName, String packagePattern, ClassLoader classLoader);

    /**
     * 获取指定包名中指定注解的相关类
     *
     * @param packageName packageName
     * @param annotationClass annotationClass
     * @return Set
     */
    Set<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 自定义ClassLoader中获取指定包名中指定注解的相关类
     *
     * @param packageName packageName
     * @param annotationClass annotationClass
     * @param classLoader classLoader
     * @return Set
     */
    Set<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass,
                                           ClassLoader classLoader);

    /**
     * 获取指定包名中指定父类或接口的相关类
     *
     * @param packageName packageName
     * @param superClass superClass
     * @return Set
     */
    Set<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);

    /**
     * 自定义ClassLoader中获取指定包名中指定父类或接口的相关类
     *
     * @param packageName packageName
     * @param superClass superClass
     * @param classLoader classLoader
     * @return Set
     */
    Set<Class<?>> getClassListBySuper(String packageName, Class<?> superClass, ClassLoader classLoader);
}
