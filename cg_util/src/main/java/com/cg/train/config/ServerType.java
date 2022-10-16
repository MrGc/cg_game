package com.cg.train.config;

/**
 * @ClassName ServerType
 * @Description 服务器类型
 * @Author craig
 * @Date 2022/10/16 23:35
 * @Version 1.0
 */
public enum ServerType {
    GAME(1, "game"),
    GATWAY(2, "gatWay"),
    CROSS(3, "cross"),
    LOGIN(4, "login"),
    DB(5, "db");

    final int type;
    final String name;

    ServerType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getName(int type) {
        for (ServerType serverType : values()) {
            if (serverType.type == type) {
                return serverType.name;
            }
        }
        return "";
    }
}
