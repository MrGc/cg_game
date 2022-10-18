package com.cg.train.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Json
 * @Description json文件
 * @Author craig
 * @Date 2022/10/17 21:47
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonBean {
    String jsonPath(); //实体对应json地址
}
