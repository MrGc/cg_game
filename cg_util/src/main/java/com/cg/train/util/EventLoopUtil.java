package com.cg.train.util;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @ClassName EventLoopUtil
 * @Description 获取EventLoop工具根据平台获取
 * @Author craig
 * @Date 2022/10/21 23:49
 * @Version 1.0
 */
public class EventLoopUtil {
    /**
     * 获取LoopGroup 根据平台
     * @param threadCount
     * @param groupName
     * @return
     */
    public static EventLoopGroup generateLoopGroup(int threadCount, String groupName){
        if(Epoll.isAvailable()) {
            return new EpollEventLoopGroup(threadCount,new DefaultThreadFactory(groupName));
        }else if(KQueue.isAvailable()){
            return new KQueueEventLoopGroup(threadCount,new DefaultThreadFactory(groupName));
        }else{
            return new NioEventLoopGroup(threadCount,new DefaultThreadFactory(groupName));
        }
    }

    /**
     * 产生ServerChannel class 根据平台
     * @return
     */
    public static Class<? extends ServerChannel> generateServerChannelClass(){
        Class<? extends ServerChannel> channelClass;
        if(Epoll.isAvailable()) {
            channelClass = EpollServerSocketChannel.class;
        }else if(KQueue.isAvailable()){
            channelClass = KQueueServerSocketChannel.class;
        }else{
            channelClass= NioServerSocketChannel.class;
        }
        return channelClass;
    }

    /**
     * 产生SocketChannel class 根据平台
     * @return
     */
    public static Class<? extends SocketChannel> generateClientChannelClass(){
        Class<? extends SocketChannel> channelClass;
        if(Epoll.isAvailable()) {
            channelClass = EpollSocketChannel.class;
        }else if(KQueue.isAvailable()){
            channelClass = KQueueSocketChannel.class;
        }else{
            channelClass= NioSocketChannel.class;
        }
        return channelClass;
    }


}
