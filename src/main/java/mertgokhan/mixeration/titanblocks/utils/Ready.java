package mertgokhan.mixeration.titanblocks.utils;

import mertgokhan.mixeration.titanblocks.TitanBlocks;
import mertgokhan.mixeration.titanblocks.commands.TitanBlocksAdministrator;
import mertgokhan.mixeration.titanblocks.events.BlockBreakEvent;
import mertgokhan.mixeration.titanblocks.utils.interfaces.ReadyHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Ready implements ReadyHandler {
    private PluginManager pluginManager = Bukkit.getPluginManager();

    @Override
    public void loadAll() {
        loadCommands();
        loadEvents();
    }

    @Override
    public void loadEvents() {
        pluginManager.registerEvents(new BlockBreakEvent(TitanBlocks.get()), TitanBlocks.get());
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
}
