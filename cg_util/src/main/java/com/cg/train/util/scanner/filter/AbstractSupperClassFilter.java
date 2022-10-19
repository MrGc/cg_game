package com.cg.train.util.scanner.filter;

/**
 * @ClassName AbstractSupperClassFilter
 * @Description 父类过滤器
 * @Author craig
 * @Date 2022/10/19 23:29
 * @Version 1.0
 */
public abstract class AbstractSupperClassFilter extends AbstractClassFilter {

  protected final Class<?> superClass;

  protected AbstractSupperClassFilter(String packageName, Class<?> superClass) {
    super(packageName);
    this.superClass = superClass;
  }

  public AbstractSupperClassFilter(String packageName, Class<?> superClass, ClassLoader loader) {
    super(packageName, loader);
    this.superClass = superClass;
  }
}