package tokyo.ramune.savannacore.major.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.savannacore.utility.Util;

public final class JoinQuitMessageListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        final Component message = Util.text("[")
                .append(Util.coloredText(TextColor.color(0x5FF09), "+"))
                .append(Util.text("] "))
                .append(Util.coloredText(TextColor.color(0xFFAAF1), "✿ " + player.getName() + "が参加しました ✿"));
        final Component firstMessage = Util.coloredText(TextColor.color(0xFFAAF1), "\nこのプレイヤーは初参加です! 歓迎しましょう!");

        event.joinMessage(player.hasPlayedBefore() ? message : message.append(firstMessage));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        final Component message = Util.text("[")
                .append(Util.coloredText(TextColor.color(0xFF0C00), "-"))
                .append(Util.text("] "))
                .append(Util.coloredText(TextColor.color(0xFFAAF1), "✿ " + player.getName() + "が退出しました ✿"));

        event.quitMessage(message);
    }
}
