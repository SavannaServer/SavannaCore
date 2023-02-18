package tokyo.ramune.savannacore.menu.listener;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.savannacore.menu.MenuItem;

public final class MenuItemClickListener extends MenuHandlerListener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;
        if (!getMenuHandler().getOpeningMenus().containsKey(clickedInventory)) return;
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        final ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;
        final MenuItem clickedSavannaMenuItem = getMenuHandler().getOpeningMenus().get(clickedInventory).getItems().stream()
                .filter(item -> item.getItem().equals(clickedItem))
                .findFirst()
                .orElse(null);

        if (clickedSavannaMenuItem == null) return;
        if (clickedSavannaMenuItem.getOnClick() != null) clickedSavannaMenuItem.getOnClick().accept(event);
    }
}
