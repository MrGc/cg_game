package com.cg.train;


import com.cg.train.dispatcher.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName GameServer
 * @Description 游戏服入口
 * @Author craig
 * @Date 2022/10/17 00:07
 * @Version 1.0
 */
public class GameServer extends BaseServer{
    public static final Logger log = LoggerFactory.getLogger(GameServer.class);
    private static final GameServer instance = new GameServer();
    private GameServer() {}
    public static GameServer getInstance() {
        return instance;
    }
    public static void main(String[] args) {
        instance.start(1,1);
        instance.initComponent(Dispatcher.getInstance().load("com.cg.train"), "扫描加载游戏对象");
    }
}
