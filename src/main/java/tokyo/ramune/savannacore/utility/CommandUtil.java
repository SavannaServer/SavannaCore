package tokyo.ramune.savannacore.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import tokyo.ramune.savannacore.command.CommandArgs;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CommandUtil {
    private final static Set<Command> registeredCommands = new HashSet<>();

    public static void register(@Nonnull String fallbackPrefix, @Nonnull Command... commands) {
        for (Command command : commands) {
            Bukkit.getCommandMap().register(fallbackPrefix, command);
        }
    }

    public static void unregister(Command... commands) {
        for (Command command : commands) {
            command.unregister(Bukkit.getCommandMap());
        }
    }

    public static void register(tokyo.ramune.savannacore.command.Command... commands) {
        for (tokyo.ramune.savannacore.command.Command command : commands) {
            if (isRegistered(command)) return;
            CommandUtil.register(
                    command.getName(),
                    new Command(command.getName()) {
                        @Override
                        public boolean execute(@Nonnull CommandSender sender, @Nonnull String commandLabel, @Nonnull String[] args) {
                            if (command.getRequirePermission() != null && !sender.hasPermission(command.getRequirePermission())) {
                                ChatUtil.requirePermission(sender, command.getRequirePermission());
                                return false;
                            }
                            return command.getOnCommand() != null && command.getOnCommand().apply(new CommandArgs(sender, args));
                        }

                        @Override
                        public @Nonnull List<String> tabComplete(@Nonnull CommandSender sender, @Nonnull String alias, @Nonnull String[] args) throws IllegalArgumentException {
                            if (command.getRequirePermission() != null && !sender.hasPermission(command.getRequirePermission()))
                                return new ArrayList<>();
                            return command.getOnTabComplete() == null ? new ArrayList<>() : command.getOnTabComplete().apply(new CommandArgs(sender, args));
                        }
                    });
        }
    }

    public static boolean isRegistered(tokyo.ramune.savannacore.command.Command command) {
        return Bukkit.getCommandMap().getCommand(command.getName()) != null;
    }

    public static void unregisterAll() {
        for (Command command : registeredCommands) {
            unregister(command);
        }
        registeredCommands.clear();
    }

    public static void mismatchSender(@Nonnull CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You can't execute this command from here.");
    }

    public static void success(@Nonnull CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + "Success!");
    }
}
