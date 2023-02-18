package tokyo.ramune.savannacore.sidebar;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.*;
import java.util.function.Supplier;

public class SideBar {
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Player player;
    private Map<Integer, Supplier<Component>> lines;
    private Component title;

    public SideBar(Player player, Component title) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final String id = "SavannaCore.sideBar." + player.getUniqueId();
        this.objective = scoreboard.registerNewObjective(id, Criteria.DUMMY, Component.text("TEXT"), RenderType.INTEGER);
        this.player = player;
        this.lines = new HashMap<>();
        this.title = title;

        initialize();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getObjective() {
        return objective;
    }

    public Player getPlayer() {
        return player;
    }

    public Component getTitle() {
        return title;
    }

    public SideBar setTitle(Component title) {
        this.title = title;
        return this;
    }

    public Map<Integer, Supplier<Component>> getLines() {
        return lines;
    }

    public SideBar setLines(Map<Integer, Supplier<Component>> lines) {
        this.lines = lines;
        return this;
    }

    public SideBar setLine(int index, Supplier<Component> line) {
        lines.put(index, line);
        return this;
    }

    public SideBar addLine(Supplier<Component> line) {
        if (lines.isEmpty()) {
            lines.put(0, line);
            return this;
        }

        lines.put(Collections.max(lines.keySet()) + 1, line);
        return this;
    }

    public SideBar addBlankLine() {
        return addLine(() -> Component.text(ChatColor.RESET.toString()));
    }

    public Map<Integer, Supplier<Component>> getFixedLines() {
        List<Supplier<Component>> lineList = new ArrayList<>();
        lines.forEach(lineList::add);

        Collections.reverse(lineList);

        Map<Integer, Supplier<Component>> lineMap = new HashMap<>();
        for (int i = 0; i < lineList.size(); i++)
            lineMap.put(i, lineList.get(i));

        return lineMap;
    }

    public SideBar show() {
        player.setScoreboard(scoreboard);
        return this;
    }

    public SideBar hide() {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        return this;
    }

    public boolean isShowing() {
        return player.getScoreboard().equals(scoreboard);
    }

    private boolean isMatchLines() {
        Set<Integer>
                currentIndexList = new HashSet<>(getFixedLines().keySet()),
                teamIndexList = new HashSet<>();
        scoreboard.getTeams().forEach(team -> teamIndexList.add(Integer.parseInt(team.getName())));

        return currentIndexList.equals(teamIndexList);
    }

    private String getFakeEntryName(int index) {
        String name = ChatColor.RESET.toString();

        for (int i = 0; i < index; i++) {
            name += ChatColor.RESET.toString();
        }

        return name;
    }

    public void initialize() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (!scoreboard.getTeams().isEmpty())
            scoreboard.getTeams().forEach(Team::unregister);

        getFixedLines().forEach((index, line) -> {
            Team team = scoreboard.registerNewTeam(String.valueOf(index));
            team.addEntry(getFakeEntryName(index));
            team.prefix(line.get());
            objective.getScore(getFakeEntryName(index)).setScore(index);
        });
    }

    public void update() {
        if (!isMatchLines())
            initialize();

        if (!objective.displayName().equals(title))
            objective.displayName(title);

        getFixedLines().forEach((index, line) -> {
            Team team = scoreboard.getTeam(String.valueOf(index));
            if (team != null && !team.prefix().equals(line.get()))
                Objects.requireNonNull(scoreboard.getTeam(Integer.toString(index))).prefix(line.get());
        });
    }

    public void remove() {
        scoreboard.clearSlot(DisplaySlot.SIDEBAR);
        for (Team team : scoreboard.getTeams()) {
            team.unregister();
        }
        objective.unregister();
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
}
