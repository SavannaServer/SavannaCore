package tokyo.ramune.savannacore.major;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.savannacore.major.listener.JoinQuitMessageListener;
import tokyo.ramune.savannacore.utility.EventUtil;

import javax.annotation.Nonnull;

public final class MajorHandler {
    public MajorHandler(@Nonnull JavaPlugin plugin) {
        EventUtil.register(
                plugin,
                new JoinQuitMessageListener()
        );
    }
}
