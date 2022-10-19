package com.cg.train.annotation;

import java.lang.annotation.*;

/**
 * @ClassName SPI
 * @Description 远程actor调用
 * @Author craig
 * @Date 2022/10/19 23:20
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SPI {
    String value();

    String message() default "";
}
