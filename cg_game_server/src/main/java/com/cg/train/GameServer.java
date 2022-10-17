package com.cg.train;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName GameServer
 * @Description 游戏服入口
 * @Author craig
 * @Date 2022/10/17 00:07
 * @Version 1.0
 */
@Slf4j
public class GameServer extends BaseServer{
    private static final GameServer instance = new GameServer();
    private GameServer() {}
    public static GameServer getInstance() {
        return instance;
    }
    public static void main(String[] args) {
        instance.start(1,1);
        instance.initComponent(true, "加载json");
    }
}
