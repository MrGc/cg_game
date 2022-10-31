import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Receive;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/26 10:27
 * @Version: 1.0.0
 */
public class PlayerActor extends AbstractBehavior<CgPlayerCommand> {
    private long userId;
    private static final Map<Long, PlayerActor> playerActorMap = new ConcurrentHashMap<>();
    private PlayerActor(ActorContext<CgPlayerCommand> context, long userId) {
        super(context);
        this.userId = userId;
    }

    @Override
    public Receive<CgPlayerCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(ProtoCmd.class, this::onPlayerCommand)
                .build();
    }

    private Behavior<CgPlayerCommand> onPlayerCommand(ProtoCmd a) {

        return this;
    }

    public PlayerActor create(long userId) {
        return playerActorMap.computeIfAbsent(userId, v -> new PlayerActor(getContext(), userId));
    }
}
