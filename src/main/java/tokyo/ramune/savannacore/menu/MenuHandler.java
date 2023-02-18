package tokyo.ramune.savannacore.menu;

import org.bukkit.inventory.Inventory;
import tokyo.ramune.savannacore.SavannaCore;
import tokyo.ramune.savannacore.menu.listener.MenuItemClickListener;
import tokyo.ramune.savannacore.menu.listener.UnregisterMenuListener;
import tokyo.ramune.savannacore.utility.EventUtil;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public final class MenuHandler {
    private final Map<Inventory, Menu> openingMenus = new HashMap<>();

    public MenuHandler() {
        EventUtil.register(
                SavannaCore.getInstance(),
                new MenuItemClickListener(),
                new UnregisterMenuListener()
        );
    }

    public Map<Inventory, Menu> getOpeningMenus() {
        return openingMenus;
    }

    public void registerMenu(@Nonnull Menu menu, @Nonnull Inventory opened) {
        openingMenus.put(opened, menu);
    }

    public void unregisterMenu(@Nonnull Inventory inventory) {
        openingMenus.remove(inventory);
    }
}
