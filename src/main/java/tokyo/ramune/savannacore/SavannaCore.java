package tokyo.ramune.savannacore;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.savannacore.config.CoreConfig;
import tokyo.ramune.savannacore.database.DatabaseHandler;
import tokyo.ramune.savannacore.major.MajorHandler;
import tokyo.ramune.savannacore.menu.MenuHandler;
import tokyo.ramune.savannacore.sidebar.SideBarHandler;
import tokyo.ramune.savannacore.utility.SavannaRunnable;

public final class SavannaCore extends JavaPlugin {
    private static SavannaCore instance;
    private static CoreConfig config;
    private static DatabaseHandler databaseHandler;
    private static MajorHandler majorHandler;
    private static SideBarHandler sideBarHandler;
    private static MenuHandler menuHandler;

    public static SavannaCore getInstance() {
        return instance;
    }

    public static CoreConfig getConfigFile() { return config; }

    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public static MajorHandler getMajorHandler() {
        return majorHandler;
    }

    public static SideBarHandler getSideBarHandler() {
        return sideBarHandler;
    }

    public static MenuHandler getMenuHandler() {
        return menuHandler;
    }

    @Override
    public void onEnable() {
        instance = this;

        config = new CoreConfig(this);
        databaseHandler = new DatabaseHandler();
        databaseHandler.connect(
                config.getValue(CoreConfig.Path.DATABASE_HOST),
                config.getValue(CoreConfig.Path.DATABASE_PORT)
        );
        majorHandler = new MajorHandler(this);
        sideBarHandler = new SideBarHandler();
        menuHandler = new MenuHandler();

        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        SavannaRunnable.cancelAll();

        getLogger().info("The plugin has been enabled.");
    }
}
