package com.cg.train.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 协议方法注解
 * @Author: Craig
 * @Date: 2022/10/17 14:17
 * @Version: 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cmd {
    /** 协议号 */
    int cmd();
    /** 是否需要登录 */
    boolean needLogin();
}
