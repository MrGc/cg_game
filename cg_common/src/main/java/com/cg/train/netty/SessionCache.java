package com.cg.train.netty;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName SessionCache
 * @Description
 * @Author craig
 * @Date 2022/10/22 11:40
 * @Version 1.0
 */
public class SessionCache {
    private static final SessionCache INSTANCE = new SessionCache();
    private SessionCache() {}

    public static SessionCache getInstance() {
        return INSTANCE;
    }
    private static AtomicInteger SESSION_ID = new AtomicInteger();
    private static final Map<Integer, PlayerSession> SESSION_CACHE = new HashMap<>();

    public PlayerSession getSession(Channel channel) {
        int sessionId = SESSION_ID.addAndGet(1);
        if (sessionId == Integer.MAX_VALUE || sessionId < 0) {
            SESSION_ID = new AtomicInteger();
            sessionId = SESSION_ID.addAndGet(1);
        }

        while (SESSION_CACHE.containsKey(sessionId)) {
            sessionId = SESSION_ID.addAndGet(1);
        }

        PlayerSession playerSession = new PlayerSession(sessionId, channel);
        SESSION_CACHE.put(sessionId, playerSession);
        return playerSession;
    }

    public void removeSession(int sessionId) {
        SESSION_CACHE.remove(sessionId);
    }
}
