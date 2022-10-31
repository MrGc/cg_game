import com.cg.train.dispatcher.Commander;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/26 11:20
 * @Version: 1.0.0
 */
public record ProtoCmd(Commander commander, String data) implements CgPlayerCommand {
}
