package tokyo.ramune.savannacore.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.savannacore.SavannaCore;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class Menu {
    private Component title;
    private int size;
    private Set<MenuItem> items = new HashSet<>();

    public Menu(@Nonnull Component title, int size) {
        this.title = title;
        this.size = size;
    }

    public Component getTitle() {
        return title;
    }

    public Menu setTitle(Component title) {
        this.title = title;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Menu setSize(int size) {
        this.size = size;
        return this;
    }

    public Set<MenuItem> getItems() {
        return items;
    }

    public void setItems(Set<MenuItem> items) {
        this.items = items;
    }

    public final void open(@Nonnull Player player) {
        final Inventory menu = Bukkit.createInventory(player, getSize(), getTitle());

        for (MenuItem item : items) {
            menu.setItem(item.getSlot(), item.getItem());
        }
        SavannaCore.getInstance().getMenuHandler().registerMenu(this, menu);
    }
}
