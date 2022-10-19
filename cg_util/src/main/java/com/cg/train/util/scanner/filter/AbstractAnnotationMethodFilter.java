package com.cg.train.util.scanner.filter;

import java.lang.annotation.Annotation;

/**
 * @ClassName AbstractAnnotationMethodFilter
 * @Description 注解方法过滤器
 * @Author craig
 * @Date 2022/10/19 23:29
 * @Version 1.0
 */
public abstract class AbstractAnnotationMethodFilter extends AbstractMethodFilter {

  protected final Class<? extends Annotation> annotationType;

  protected AbstractAnnotationMethodFilter(Class<?> clazz, Class<? extends Annotation> annotationType) {
    super(clazz);
    this.annotationType = annotationType;
  }

}