package tokyo.ramune.savannacore.menu.listener;

import org.bukkit.event.Listener;
import tokyo.ramune.savannacore.SavannaCore;
import tokyo.ramune.savannacore.menu.MenuHandler;

public class MenuHandlerListener implements Listener {
    public MenuHandler getMenuHandler() {
        return SavannaCore.getMenuHandler();
    }
}
