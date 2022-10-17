package com.cg.train.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 功能注解
 * @Author: Craig
 * @Date: 2022/10/17 14:17
 * @Version: 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
    /** 功能id，用于功能开启判断，功能未开启直接拦截协议 */
    int functionId();

}
