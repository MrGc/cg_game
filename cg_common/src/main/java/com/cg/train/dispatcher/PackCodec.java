package com.cg.train.dispatcher;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 协议封装解析器
 * @Author: Craig
 * @Date: 2022/10/17 17:42
 * @Version: 1.0.0
 */
@Slf4j
public class PackCodec {
    /** 协议头的长度 */
    public static final int HEAD_LEN = 12;

    public static Pack tcpDecode(ByteBuf msg) {
        msg.markReaderIndex();
        int length = msg.readInt();
        int dataLen = length - HEAD_LEN;
        if (dataLen < 0) {
            msg.resetReaderIndex();
            log.error("协议长度不对： LEN = " + length);
            return null;
        }
        int componentId = msg.readInt();
        int cmdId = msg.readInt();
        byte[] newData = new byte[dataLen];

        if (newData.length > 0) {
            msg.readBytes(newData);
        }

        return new Pack(componentId, cmdId, new String(newData));
    }

    /**
     * 封装tcpSocket协议
     * @param pack
     * @return
     */
    public static byte[] tcpEncode(Pack pack) {
        int componentId = pack.componentId();
        int cmd = pack.cmdId();
        byte[] data = pack.msg().getBytes();

        int length = HEAD_LEN + data.length;
        ByteBuf buf = Unpooled.buffer(length);
        buf.writeInt(length);
        buf.writeInt(componentId);
        buf.writeInt(cmd);
        buf.writeBytes(data);
        byte[] newData = new byte[buf.readableBytes()];
        buf.readBytes(newData);
        log.debug("封装协议 length = {}, cmd = {}_{}", length, componentId, cmd);
        return newData;
    }

}
