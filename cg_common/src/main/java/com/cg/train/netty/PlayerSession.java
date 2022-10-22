package com.cg.train.netty;

import io.netty.channel.Channel;

/**
 * @ClassName PlayerSession
 * @Description
 * @Author craig
 * @Date 2022/10/22 11:35
 * @Version 1.0
 */
public record PlayerSession (int sessionId, Channel channel) {
}
