package tokyo.ramune.savannacore.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class MenuContentBuilder {
    private final Map<String, MenuItem> menuItemMap = new HashMap<>();
    private String[] itemKeys = {"a"};

    public MenuContentBuilder() {
        menuItemMap.put("a", new MenuItem(new ItemStack(Material.AIR), -1));
    }

    public MenuContentBuilder putKeyValue(@Nonnull String key, @Nonnull MenuItem value) {
        menuItemMap.put(key, value);
        return this;
    }

    public MenuContentBuilder putKeyValue(@Nonnull Map.Entry<String, MenuItem>... keyValues) {
        for (Map.Entry<String, MenuItem> entry : keyValues) {
            menuItemMap.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public MenuContentBuilder setItemKeys(@Nonnull String[] itemKeys) {
        this.itemKeys = itemKeys;
        return this;
    }

    public void build(@Nonnull Menu menu) {
        final Set<MenuItem> menuItems = new HashSet<>();
        for (int i = 0; i < itemKeys.length; i++) {
            final MenuItem menuItem = menuItemMap.getOrDefault(itemKeys[i], new MenuItem(new ItemStack(Material.AIR), -1));
            menuItems.add(new MenuItem(menuItem.getItem(), i, menuItem.getOnClick()));
        }
        menu.setItems(menuItems);
    }
}
