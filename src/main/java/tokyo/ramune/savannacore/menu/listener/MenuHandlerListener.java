package tokyo.ramune.savannacore.menu.listener;

import org.bukkit.event.Listener;
import tokyo.ramune.savannacore.SavannaCore;
import tokyo.ramune.savannacore.menu.MenuHandler;

public class MenuHandlerListener implements Listener {
    private final MenuHandler menuHandler = SavannaCore.getMenuHandler();

    public MenuHandler getMenuHandler() {
        return menuHandler;
    }
}
