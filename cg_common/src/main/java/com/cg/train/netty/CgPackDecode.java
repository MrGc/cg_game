package com.cg.train.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName CgPackDecode
 * @Description 通信解码
 * @Author craig
 * @Date 2022/10/22 00:08
 * @Version 1.0
 */
public class CgPackDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        CgPackCodec.tcpDecode(in);
    }
}
