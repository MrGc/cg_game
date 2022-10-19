package com.cg.train.util.scanner.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AbstractMethodFilter
 * @Description 方法过滤器
 * @Author craig
 * @Date 2022/10/19 23:59
 * @Version 1.0
 */
public abstract class AbstractMethodFilter {
    protected final Class<?> clazz;

    protected AbstractMethodFilter(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * 是否保存方法
     * @param method
     * @return
     */
    public abstract boolean filterCondition(Method method);

    public List<Method> getMethodList() {
        List<Method> mList = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            if (filterCondition(method)) {
                mList.add(method);
            }
        }
        return mList;
    }
}
