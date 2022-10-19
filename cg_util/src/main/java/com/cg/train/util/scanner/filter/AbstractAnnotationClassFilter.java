package com.cg.train.util.scanner.filter;

import java.lang.annotation.Annotation;

/**
 * @ClassName AbstractAnnotationClassFilter
 * @Description 注解类过滤器
 * @Author craig
 * @Date 2022/10/19 23:29
 * @Version 1.0
 */
public abstract class AbstractAnnotationClassFilter extends AbstractClassFilter {

  protected final Class<? extends Annotation> annotationClass;

  protected AbstractAnnotationClassFilter(String packageName, Class<? extends Annotation> annotationClass) {
    super(packageName);
    this.annotationClass = annotationClass;
  }

  public AbstractAnnotationClassFilter(String packageName, Class<? extends Annotation> annotationClass,
      ClassLoader loader) {
    super(packageName, loader);
    this.annotationClass = annotationClass;
  }
}