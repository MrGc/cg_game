package com.cg.train.netty;

import com.cg.train.dispatcher.Commander;
import com.cg.train.dispatcher.Dispatcher;
import com.cg.train.util.ProtoStuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 协议封装解析器
 * @Author: Craig
 * @Date: 2022/10/17 17:42
 * @Version: 1.0.0
 */
public class CgPackCodec {
    public static final Logger log = LoggerFactory.getLogger(CgPackCodec.class);
    /** 协议头的长度 */
    public static final int HEAD_LEN = 12;

    public static CgPack tcpDecode(ByteBuf msg) {
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
        Object param = null;
        if (newData.length > 0) {
            Commander commander = Dispatcher.getInstance().getCommander(componentId, cmdId);
            Class<?>[] parameterTypes = commander.method().getParameterTypes();
            Class<?> parameterType = parameterTypes[0];
            msg.readBytes(newData);
            param = ProtoStuffUtil.deserializer(newData, parameterType);
        }

        return new CgPack(componentId, cmdId, param);
    }

    /**
     * 封装tcpSocket协议
     * @param cgPack
     * @return
     */
    public static byte[] tcpEncode(CgPack cgPack) {
        int componentId = cgPack.componentId();
        int cmd = cgPack.cmdId();
        byte[] data = ProtoStuffUtil.serializer(cgPack.msg());

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
