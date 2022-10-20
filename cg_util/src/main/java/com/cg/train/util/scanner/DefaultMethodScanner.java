package com.cg.train.util.scanner;

import com.cg.train.util.scanner.filter.AbstractAnnotationMethodFilter;
import com.cg.train.util.scanner.filter.AbstractPatternNameMethodFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName DefaultMethodScanner
 * @Description
 * @Author craig
 * @Date 2022/10/20 21:04
 * @Version 1.0
 */
public class DefaultMethodScanner implements MethodScanner{
    private static final DefaultMethodScanner INSTANCE = new DefaultMethodScanner();
    private DefaultMethodScanner() {}

    public static DefaultMethodScanner getInstance() {
        return INSTANCE;
    }
    private static final Logger log = LoggerFactory.getLogger(DefaultMethodScanner.class);
    @Override
    public List<Method> getMethodList(Class<?> clazz, String methodPattern) {
        return new AbstractPatternNameMethodFilter(clazz, methodPattern) {

            @Override
            public boolean filterCondition(Method method) {
                return method.getName().matches(methodPattern);
            }
        }.getMethodList();
    }

    @Override
    public List<Method> getMethodListByAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return new AbstractAnnotationMethodFilter(clazz, annotationClass) {

            @Override
            public boolean filterCondition(Method method) {
                return method.isAnnotationPresent(annotationType);
            }
        }.getMethodList();
    }

    @Override
    public List<Method> getMethodListByAnnotationInterface(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return new AbstractAnnotationMethodFilter(clazz, annotationClass) {

            @Override
            public boolean filterCondition(Method method) {
                if (method.isAnnotationPresent(annotationType)) {
                    return true;
                }
                Class<?>[] cls = clazz.getInterfaces();
                for (Class<?> c : cls) {
                    try {
                        Method md = c.getDeclaredMethod(method.getName(), method.getParameterTypes());
                        if (md.isAnnotationPresent(annotationType)) {
                            return true;
                        }
                    } catch (Exception err) {
                        log.error("", err);
                    }
                }
                return false;
            }
        }.getMethodList();
    }
}
