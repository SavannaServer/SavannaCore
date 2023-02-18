package tokyo.ramune.savannacore.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MenuItem {
    private final ItemStack item;
    private final int slot;
    private Consumer<InventoryClickEvent> onClick = null;

    public MenuItem(@Nonnull ItemStack item, int slot, @Nullable Consumer<InventoryClickEvent> onClick) {
        this.item = item;
        this.slot = slot;
        this.onClick = onClick;
    }

    public MenuItem(@Nonnull ItemStack item, int slot) {
        this.item = item;
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    @Nullable
    public Consumer<InventoryClickEvent> getOnClick() {
        return onClick;
    }

    public final ItemStack getItem() {
        return item;
    }
}
