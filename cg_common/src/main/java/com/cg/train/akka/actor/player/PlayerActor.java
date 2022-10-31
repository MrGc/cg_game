package com.cg.train.akka.actor.player;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.cg.train.dispatcher.Commander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/26 10:27
 * @Version: 1.0.0
 */
public class PlayerActor extends AbstractBehavior<CgPlayerCommand> {
    private static final Logger log = LoggerFactory.getLogger(PlayerActor.class);
    private long userId;
    private static final Map<Long, Behavior<CgPlayerCommand>> playerActorMap = new ConcurrentHashMap<>();
    private PlayerActor(ActorContext<CgPlayerCommand> context, long userId) {
        super(context);
        this.userId = userId;
    }

    @Override
    public Receive<CgPlayerCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(ProtoCmd.class, this::onPlayerCommand)
                .onMessage(KickOff.class, this::onKickOff)
                .build();
    }

    private Behavior<CgPlayerCommand> onPlayerCommand(ProtoCmd protoCmd) {
        Commander commander = protoCmd.commander();
        Object data = protoCmd.data();
        try{
            commander.method().invoke(commander.o(), userId, data);
        }catch (Exception e){
            log.error(commander.method().getName() +" error:", e);
        }
        return this;
    }

    private Behavior<CgPlayerCommand> onKickOff(KickOff kickOff) {

        return this;
    }

    public static Behavior<CgPlayerCommand> create(long userId) {
        Behavior<CgPlayerCommand> behavior = Behaviors.setup(c -> new PlayerActor(c, userId));
        return playerActorMap.computeIfAbsent(userId, v -> behavior);
    }
}
