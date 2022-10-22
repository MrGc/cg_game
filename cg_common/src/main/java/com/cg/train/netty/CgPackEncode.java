package com.cg.train.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName CgPackEncode
 * @Description 通信编码
 * @Author craig
 * @Date 2022/10/22 00:09
 * @Version 1.0
 */
public class CgPackEncode extends MessageToByteEncoder<CgPack> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CgPack msg, ByteBuf out) throws Exception {
        byte[] bytes = CgPackCodec.tcpEncode(msg);
        ctx.write(bytes);
    }
}
