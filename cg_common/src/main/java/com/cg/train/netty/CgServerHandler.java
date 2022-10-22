package com.cg.train.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName CgServerHandler
 * @Description
 * @Author craig
 * @Date 2022/10/21 23:30
 * @Version 1.0
 */
public class CgServerHandler extends SimpleChannelInboundHandler<CgPack> {
    private static final Logger log = LoggerFactory.getLogger(CgNettyServer.class);
    /**
     * 会话对象
     */
    private PlayerSession playerSession;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CgPack msg) throws Exception {
        //todo craig 协议分发放到akka做。
//        msg.componentId()
    }

    /**
     * 链接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        if(playerSession ==null){
            playerSession = SessionCache.getInstance().getSession(ctx.channel());
            log.info("ip:"+ctx.channel().remoteAddress()+",start connecting server");
        }
    }

    /**
     * 断开链接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("ip:"+ctx.channel().remoteAddress()+",disconnected server");
        //掉线处理
//        if (playerSession.getUserId() != null) {
//            playerSession.getActor().kickOff(playerSession);
//        }

    }

    /**
     * 链接异常
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        if(cause instanceof ReadTimeoutException){
            //链接超时
            if(playerSession == null){
                log.warn("this channel is read time out address="+ctx.channel().remoteAddress());
            }else{
//                log.warn(playerSession.getUserId()+",this player read time out address="+ctx.channel().remoteAddress());
//                playerSession.getActor().kickOff(playerSession);
            }
        } else if(cause instanceof OutOfMemoryError){
            if(playerSession != null){
//                playerSession.getActor().kickOff(playerSession);
            }
        }else{
            log.error("",cause.getCause());
        }
        ctx.close();
    }
}
