package com.cg.train;

import com.cg.train.config.Config;
import com.cg.train.config.ConfigKey;
import com.cg.train.config.ServerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName BashServer
 * @Description 服务器基础启动类
 * @Author craig
 * @Date 2022/10/15 22:12
 * @Version 1.0
 */
public abstract class BaseServer {
    private static final Logger logger = LoggerFactory.getLogger(BaseServer.class);
    protected static String configPath;
    /** 服务器类型 */
    protected int serverType;
    /** 服务器id */
    protected int serverId;
    /** 是否启动 */
    private volatile boolean terminate;
    /** 服务器最后初始化时间 */
    private long lastInitMillis;
    /** 是否是测试模式 */
    protected boolean testMode;

    protected boolean start(int serverType, int serverId) {
        this.serverType = serverType;
        this.serverId = serverId;
        if (!Config.initConfig(configPath)) {
            logger.error("配置文件不存在{}", configPath);
            return false;
        }

        lastInitMillis = System.currentTimeMillis();
        testMode = Config.getInt(ConfigKey.TEST_MODE) == 1;
        if (testMode) {
            logger.warn("--==测试模式开启==--");
        }



        return true;
    }

    private String getServerName() {
        String serverName = ServerType.getName(serverType);
        return serverName + "_" + serverId;
    }

    /**
     * 初始化组件
     * @param initResult
     * @param componentName
     * @return
     */
    public boolean initComponent(boolean initResult, String componentName) {
        if (!initResult) {
            logger.warn(componentName + "错误");
            System.exit(-1);
        } else {
            logger.info(componentName + "加载完成.耗时 : " + (System.currentTimeMillis() - lastInitMillis) + " ms");
        }

        lastInitMillis = System.currentTimeMillis();
        return initResult;
    }
}
