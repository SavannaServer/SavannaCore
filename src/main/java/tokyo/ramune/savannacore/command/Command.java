package tokyo.ramune.savannacore.command;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import tokyo.ramune.savannacore.utility.CommandUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public class Command {
    private final String name;
    @Nullable
    private final Function<CommandArgs, Boolean> onCommand;
    @Nullable
    private final Function<CommandArgs, List<String>> onTabComplete;
    @Nullable
    private final Permission requirePermission;

    public Command(@Nonnull String name, @Nullable Function<CommandArgs, Boolean> onCommand, @Nullable Function<CommandArgs, List<String>> onTabComplete, @Nullable Permission requirePermission) {
        this.name = name;
        this.onCommand = onCommand;
        this.onTabComplete = onTabComplete;
        this.requirePermission = requirePermission;
    }

    public Command(@Nonnull String name, @Nullable Function<CommandArgs, Boolean> onCommand, @Nullable Permission requirePermission) {
        this.name = name;
        this.onCommand = onCommand;
        this.onTabComplete = null;
        this.requirePermission = requirePermission;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Function<CommandArgs, Boolean> getOnCommand() {
        return onCommand;
    }

    @Nullable
    public Function<CommandArgs, List<String>> getOnTabComplete() {
        return onTabComplete;
    }

    @Nullable
    public Permission getRequirePermission() {
        return requirePermission;
    }

    public void register() {
        CommandUtil.register(this);
    }

    public void unregister() {
        org.bukkit.command.Command registeredCommand = Bukkit.getServer().getCommandMap().getCommand(getName());
        if (registeredCommand == null) return;
        registeredCommand.unregister(Bukkit.getServer().getCommandMap());
    }

    public boolean isRegistered(@Nonnull Command command) {
        return Bukkit.getCommandMap().getCommand(command.getName()) != null;
    }
}
