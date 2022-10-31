package com.cg.train.netty;

import akka.actor.typed.ActorSystem;
import com.cg.train.akka.actor.player.CgPlayerCommand;
import com.cg.train.akka.actor.player.PlayerActor;
import io.netty.channel.Channel;

/**
 * @ClassName PlayerSession
 * @Description
 * @Author craig
 * @Date 2022/10/22 11:35
 * @Version 1.0
 */
public class PlayerSession {
    private final int sessionId;
    private final Channel channel;

    private ActorSystem<CgPlayerCommand> playerActor;

    private long userId;

    public PlayerSession(int sessionId, Channel channel) {
        this.sessionId = sessionId;
        this.channel = channel;
    }

    public void createPlayerActor(long userId) {
        this.userId = userId;
        playerActor = ActorSystem.create(PlayerActor.create(userId), "playerActor");
    }

    public int getSessionId() {
        return sessionId;
    }

    public Channel getChannel() {
        return channel;
    }

    public ActorSystem<CgPlayerCommand> getPlayerActor() {
        return playerActor;
    }

    public long getUserId() {
        return userId;
    }
}
