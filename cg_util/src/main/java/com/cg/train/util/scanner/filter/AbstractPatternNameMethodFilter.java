package com.cg.train.util.scanner.filter;

/**
 * @ClassName AbstractPatternNameMethodFilter
 * @Description 方法正则表达式过滤器
 * @Author craig
 * @Date 2022/10/19 23:59
 * @Version 1.0
 */
public abstract class AbstractPatternNameMethodFilter extends AbstractMethodFilter {

  protected final String methodPattern;

  protected AbstractPatternNameMethodFilter(Class<?> clazz, String methodPattern) {
    super(clazz);
    this.methodPattern = methodPattern;
  }

}