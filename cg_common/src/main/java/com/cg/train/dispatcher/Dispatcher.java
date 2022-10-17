package com.cg.train.dispatcher;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/17 16:04
 * @Version: 1.0.0
 */
@Slf4j
public class Dispatcher {
    private static final Dispatcher instance = new Dispatcher();
    private Dispatcher() {}
    public static final Dispatcher getInstance() {
        return instance;
    }

    private final Map<String, Commander> commanders = new HashMap<>();

    public Pack invoke(long playerId, int componentId, int cmdId, String data) throws Exception {
        //todo craig
        String cmd = componentId + "_" + cmdId;
        Commander commander = commanders.get(cmd);
        if (commander == null) {
            return null;
        }
        long begin = System.currentTimeMillis();

        log.info("客户端协议内容：player:{}, cmd: {}, param:{}", playerId, cmd,  data);
        Object[] argsValues = new Object[2];
        long used = System.currentTimeMillis() - begin;
        log.debug("协议[{}]处理完成，耗时{}ms", cmd, used);
        return null;
    }
}
