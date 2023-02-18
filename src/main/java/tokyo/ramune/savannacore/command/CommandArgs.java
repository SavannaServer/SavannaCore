package tokyo.ramune.savannacore.command;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public final class CommandArgs {
    private final CommandSender sender;
    private final String[] args;

    public CommandArgs(@Nonnull CommandSender sender, @Nonnull String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String[] getArgs() {
        return args;
    }
}
