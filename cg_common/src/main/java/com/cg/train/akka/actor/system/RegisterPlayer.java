package com.cg.train.akka.actor.system;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/31 11:37
 * @Version: 1.0.0
 */
public record RegisterPlayer(String name, int sex) implements CgSysCommand{
}
