package tokyo.ramune.savannacore.config;

import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.lang.reflect.ParameterizedType;

public final class CoreConfig extends ConfigFile {
    public CoreConfig(Plugin plugin) {
        super(plugin, "config.yml");

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public <T> T getValue(@Nonnull Path<T> path) {
        return getConfig().getObject("config." + path.getPath(), path.getClazz(), path.getDefault());
    }

    public <T> void setValue(@Nonnull Path<T> path, T value) {
        getConfig().set(path.getPath(), value);
    }

    public interface Path<T> {
        Path<String> DATABASE_HOST = new Path<String>() {
            @Override
            public String getPath() {
                return "database.host";
            }

            @Override
            public String getDefault() {
                return "localhost";
            }

            @Override
            public Class<String> getClazz() {
                return String.class;
            }
        };
        Path<Integer> DATABASE_PORT = new Path<Integer>() {
            @Override
            public String getPath() {
                return "database.port";
            }

            @Override
            public Integer getDefault() {
                return 27017;
            }

            @Override
            public Class<Integer> getClazz() {
                return Integer.class;
            }
        };
        Path<Boolean> DISCORD_ENABLE = new Path<Boolean>() {
            @Override
            public String getPath() {
                return "discord.enable";
            }

            @Override
            public Boolean getDefault() {
                return false;
            }

            @Override
            public Class<Boolean> getClazz() {
                return Boolean.class;
            }
        };
        Path<String> DISCORD_TOKEN = new Path<String>() {
            @Override
            public String getPath() {
                return "discord.token";
            }

            @Override
            public String getDefault() {
                return "token";
            }

            @Override
            public Class<String> getClazz() {
                return String.class;
            }
        };

        String getPath();

        T getDefault();

        Class<T> getClazz();
    }
}
