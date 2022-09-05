package mertgokhan.mixeration.titanblocks.utils;

import mertgokhan.mixeration.titanblocks.TitanBlocks;
import mertgokhan.mixeration.titanblocks.commands.TitanBlocksAdministrator;
import mertgokhan.mixeration.titanblocks.config.Storage;
import mertgokhan.mixeration.titanblocks.events.BlockBreak;
import mertgokhan.mixeration.titanblocks.utils.interfaces.ReadyHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import static mertgokhan.mixeration.titanblocks.TitanBlocks.isDebugOn;

public class Ready implements ReadyHandler {
    private PluginManager pluginManager = Bukkit.getPluginManager();
    public static boolean isStorageNull;
    public Messenger messenger = new Messenger();

    @Override
    public void loadAll() {
        loadCommands();
        loadEvents();
        loadMaps();
    }

    @Override
    public void loadEvents() {
        pluginManager.registerEvents(new BlockBreak(TitanBlocks.get()), TitanBlocks.get());
        Bukkit.getLogger().warning("[TitanBlocks@Events]: Loaded events for TitanBlocks");

    }

    @Override
    public void loadCommands() {
        TitanBlocks.get().getCommand("TitanBlocks").setExecutor(new TitanBlocksAdministrator(TitanBlocks.get()));
        Bukkit.getLogger().warning(String.format("[TitanBlocks@Commands]: Loaded commands (%s) for TitanBlocks", TitanBlocks.get().getDescription().getCommands().size()));
    }

    @Override
    public void createBackUp() {

    }

    @Override
    public void loadMaps() {
        if (Storage.getConfig().getConfigurationSection("titan-blocks.") == null) {
            Bukkit.getLogger().warning("[TitanBlocks@Error]: Storage.yml:titan-blocks is empty or null !");
            isStorageNull = true;
            if(isDebugOn()) {
                messenger.debug("isStorageNull: true");
            }
        } else {
            isStorageNull = false;
            messenger.debug("isStorageNull: false");
            for (String blocks : Storage.getConfig().getConfigurationSection("titan-blocks.").getKeys(false)) {
                String name = Storage.getConfig().getString("titan-blocks." + blocks + ".name");
                for (int i = blocks.length(); i < 100; i++) {
                    Maps.healthCounter.put(name, 0);
                    Maps.healthy.put(name, true);
                }
            }

        }
    }
}
