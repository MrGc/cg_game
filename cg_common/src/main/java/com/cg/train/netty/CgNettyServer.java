package com.cg.train.netty;

import com.cg.train.util.EventLoopUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;

/**
 * @ClassName GcNettyServer
 * @Description netty server
 * @Author craig
 * @Date 2022/10/21 23:21
 * @Version 1.0
 */
public class CgNettyServer {
    private static final Logger log = LoggerFactory.getLogger(CgNettyServer.class);
    /**
     * boss线程模型组
     */
    private EventLoopGroup bossGroup = EventLoopUtil.generateLoopGroup(1, "bossGroup");
    /**
     * 工作线程模型组
     */
    private EventLoopGroup workerGroup = EventLoopUtil.generateLoopGroup(Runtime.getRuntime().availableProcessors(), "workerGroup");

    /**
     * 服务器端口
     */
    private final int port;

    public CgNettyServer(int port) {
        this.port = port;
    }

    public void start() {
        if (isPortAvailable(port)) {
            log.info("Server is establishing to listening at :" + port);
        } else {
            log.error("Server's port :" + port + " not available");
            System.exit(-1);
            return;
        }
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(EventLoopUtil.generateServerChannelClass())
                .option(ChannelOption.SO_BACKLOG, 500)
                .option(ChannelOption.SO_RCVBUF, 1024 * 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                .childHandler(new CgServerInitializer());

        // Start the server.
        ChannelFuture f = b.bind(port);

    }

    /**
     * <p>Title: isPortAvailable</p>
     *
     * <p>Description: 查看端口是否可用</p>
     *
     * @param port
     * @return
     */
    private static boolean isPortAvailable(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            ss.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
