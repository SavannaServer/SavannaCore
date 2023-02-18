package tokyo.ramune.savannacore.menu.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCloseEvent;

public final class UnregisterMenuListener extends MenuHandlerListener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryClose(InventoryCloseEvent event) {
        getMenuHandler().unregisterMenu(event.getInventory());
    }
}
