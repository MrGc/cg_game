package com.cg.train.util.scanner;

import com.cg.train.util.scanner.filter.AbstractAnnotationClassFilter;
import com.cg.train.util.scanner.filter.AbstractClassFilter;
import com.cg.train.util.scanner.filter.AbstractSupperClassFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * @ClassName DefaultClassScanner
 * @Description
 * @Author craig
 * @Date 2022/10/20 00:12
 * @Version 1.0
 */
public class DefaultClassScanner implements ClassScanner{
    private static final DefaultClassScanner INSTANCE = new DefaultClassScanner();

    private DefaultClassScanner() {}

    public static DefaultClassScanner getInstance() {
        return INSTANCE;
    }

    @Override
    public Set<Class<?>> getClassList(String packageName, String pattern) {
        return new AbstractClassFilter(packageName) {
            @Override
            public boolean filterCondition(Class<?> cls) {
                String className = cls.getName();
                String patternStr = (null == pattern || pattern.isEmpty()) ? ".*" : pattern;
                return className.startsWith(packageName) && className.matches(patternStr);

            }
        }.getClassList();
    }

    @Override
    public Set<Class<?>> getClassList(String packageName, String pattern, ClassLoader classLoader) {
        return new AbstractClassFilter(packageName, classLoader) {
            @Override
            public boolean filterCondition(Class<?> cls) {
                String className = cls.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));
                String patternStr = (null == pattern || pattern.isEmpty()) ? ".*" : pattern;
                return pkgName.startsWith(packageName) && pkgName.matches(patternStr);
            }
        }.getClassList();
    }

    @Override
    public Set<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new AbstractAnnotationClassFilter(packageName, annotationClass) {
            @Override
            public boolean filterCondition(Class<?> cls) {
                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    @Override
    public Set<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass, ClassLoader classLoader) {
        return new AbstractAnnotationClassFilter(packageName, annotationClass, classLoader) {
            @Override
            public boolean filterCondition(Class<?> cls) { // 这里去掉了内部类
                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    @Override
    public Set<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return new AbstractSupperClassFilter(packageName, superClass) {
            @Override
            public boolean filterCondition(Class<?> clazz) { // 这里去掉了内部类
                return superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)
                        && !Modifier.isInterface(clazz.getModifiers()) && !Modifier.isAbstract(clazz.getModifiers())
                        && Modifier.isPublic(clazz.getModifiers());
                // !cls.getName().contains("$");
            }

        }.getClassList();
    }

    @Override
    public Set<Class<?>> getClassListBySuper(String packageName, Class<?> superClass, ClassLoader classLoader) {
        return new AbstractSupperClassFilter(packageName, superClass, classLoader) {
            @Override
            public boolean filterCondition(Class<?> cls) { // 这里去掉了内部类
                return superClass.isAssignableFrom(cls) && !superClass.equals(cls);// &&
                // !cls.getName().contains("$");
            }

        }.getClassList();
    }
}
