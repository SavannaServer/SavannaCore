package tokyo.ramune.savannacore.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.savannacore.SavannaCore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public final class SideBarHandler {
    private Set<SideBar> sideBars = new HashSet<>();
    private BukkitTask updateTimer;

    public SideBarHandler() {
        startUpdateTimer();
    }

    private void startUpdateTimer() {
        if (updateTimer == null || updateTimer.isCancelled())
            updateTimer = Bukkit.getScheduler().runTaskTimerAsynchronously(
                    SavannaCore.getInstance(),
                    this::updateAll,
                    20, 5
            );
    }

    @Nullable
    public SideBar getCurrentSideBar(Player player) {
        try {
            return sideBars.stream()
                    .filter(sidebar -> sidebar.getPlayer().equals(player))
                    .findFirst()
                    .orElse(null);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Set<SideBar> getSideBars() {
        return sideBars;
    }

    @Nullable
    public SideBar getSideBar(@Nonnull Player player) {
        for (SideBar sideBar : sideBars) {
            if (sideBar.getPlayer().equals(player)) return sideBar;
        }
        return null;
    }

    public void setSideBar(SideBar instance) {
        remove(instance.getPlayer());

        sideBars.add(instance);
    }

    private void updateAll() {
        sideBars.forEach(SideBar::update);
    }

    public void remove(Player player) {
        for (SideBar sideBar : sideBars) {
            if (sideBar.getPlayer().equals(player)) {
                sideBar.remove();
                sideBars.remove(sideBar);
                return;
            }
        }
    }

    public void removeAll() {
        sideBars = new HashSet<>();
    }
}