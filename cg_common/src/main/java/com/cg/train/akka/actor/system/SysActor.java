package com.cg.train.akka.actor.system;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Receive;
import com.cg.train.akka.actor.player.KickOff;
import com.cg.train.akka.actor.player.ProtoCmd;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/31 11:21
 * @Version: 1.0.0
 */
public class SysActor extends AbstractBehavior<CgSysCommand> {
    private Map<String, Long> nameToId = new HashMap<>();
    private Map<Long, String> IdToName = new HashMap<>();
    private AtomicLong userIdGe = new AtomicLong();

    public SysActor(ActorContext<CgSysCommand> context) {
        super(context);
    }

    @Override
    public Receive<CgSysCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(RegisterPlayer.class, this::onRegisterPlayer)
                .build();
    }

    private Behavior<CgSysCommand> onRegisterPlayer(RegisterPlayer registerPlayer) {

        return null;
    }
}
