package mertgokhan.mixeration.titanblocks.config;

import mertgokhan.mixeration.titanblocks.TitanBlocks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class Storage {
    private static TitanBlocks plugin;
    private static String path;
    private static String folderpath;
    private static File file;
    private static FileConfiguration config;

    public Storage(TitanBlocks plugin, String path) {
        this.plugin = plugin;
        this.path = path;
        file = null;
        config = null;
    }

    public static void create() {
        file = new File(plugin.getDataFolder(), path);
        if (!file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }

    }

    public static FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }

        return config;
    }

    public static void reloadConfig() {
        if (config == null) {
            file = new File(plugin.getDataFolder(), path);
        }

        config = YamlConfiguration.loadConfiguration(file);

        try {
            Reader defaultConfigStream = new InputStreamReader(plugin.getResource(path), "UTF8");
            if (defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                config.setDefaults(defaultConfig);
            }
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
        } catch (NullPointerException var3) {
            var3.printStackTrace();
        }

    }

    public static void saveConfig() {
        try {
            config.save(file);
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }

    public void saveDefaultConfig() {
        if (file == null) {
            file = new File(plugin.getDataFolder(), path);
        }

        if (!file.exists()) {
            plugin.saveResource(path, false);
        }

    }

    public String getPath() {
        return path;
    }

    public String getFolderpath() {
        return folderpath;
    }
}