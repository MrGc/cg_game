package com.cg.train.dispatcher;

import java.lang.reflect.Method;

/**
 * @Description: 协议方法对象
 * @Author: Craig
 * @Date: 2022/10/17 18:20
 * @Version: 1.0.0
 */
public record Commander(Object o, Method method) {
}
