package com.cg.train.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @ClassName CgServerInitializer
 * @Description
 * @Author craig
 * @Date 2022/10/21 23:58
 * @Version 1.0
 */
public class CgServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("ping", new IdleStateHandler(0, 0, 60));
        pipeline.addLast(new CgPackDecode());
        pipeline.addLast(new CgPackEncode());
        pipeline.addLast(new CgServerHandler());
    }
}
