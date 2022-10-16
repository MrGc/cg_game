package com.cg.train.config;

import com.cg.train.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * @ClassName Config
 * @Description 配置文件处理类
 * @Author craig
 * @Date 2022/10/15 22:18
 * @Version 1.0
 */
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static Properties properties = null;
    /** 服务器根目录 */
    private static String serverPath = null;

    /**
     * 初始化配置文件
     * @param path
     * @return
     */
    public static boolean initConfig(String path) {
        if (StringUtils.isNullOrEmpty(path)) {
            return false;
        }

        Optional<Properties> opProperties = loadProperties(path);
        if (opProperties.isEmpty()) {
            return false;
        }
        properties = opProperties.get();
        String tempPath = properties.getProperty(ConfigKey.SERVER_PATH);
        if (StringUtils.isNullOrEmpty(tempPath)) {
            return false;
        }

        serverPath = tempPath;
        return true;
    }

    private static Optional<Properties> loadProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedInputStream(new FileInputStream(path)));
            return Optional.of(properties);
        } catch (IOException e) {
            logger.error("", e);
        }
        return Optional.empty();
    }

    /**
     * 获取配置的值
     * @param key
     * @return
     */
    public static String getPath(String key) {
        if (properties == null) {
            return null;
        }

        String path = properties.getProperty(key);
        if (StringUtils.isNullOrEmpty(path)) {
            return "";
        }

        if (StringUtils.isNotNullOrEmpty(path)) {
            if (path.startsWith("/") || serverPath.endsWith("/")) {
                path = serverPath + path;
            } else {
                path = serverPath + "/" + path;
            }
        }

        return path;
    }

    /**
     * 获取整型配置
     * @param key
     * @return
     */
    public static int getInt(String key) {
        if (properties == null) {
            return 0;
        }
        String val = properties.getProperty(key);
        if (val == null) {
            logger.warn("配置" + key + "不存在");
            return 0;
        }
        return StringUtils.parseInt(val);
    }
}
