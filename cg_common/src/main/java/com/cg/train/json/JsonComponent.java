package com.cg.train.json;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName JsonComponent
 * @Description json组件
 * @Author craig
 * @Date 2022/10/17 21:59
 * @Version 1.0
 */
public class JsonComponent {
    private static final Map<Class<? extends BaseJson>, List<BaseJson>> jsonList = new ConcurrentHashMap<>(10);
    private static final Map<Class<? extends BaseJson>, Map<Integer, BaseJson>> oneKeyMap = new ConcurrentHashMap<>(10);
    private static final Map<Class<? extends BaseJson>, Map<Integer, Map<Integer, BaseJson>>> towKeyMap = new ConcurrentHashMap<>(10);

    public void reload() {

    }
}
