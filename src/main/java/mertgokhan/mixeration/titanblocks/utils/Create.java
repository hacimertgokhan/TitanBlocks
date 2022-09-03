package mertgokhan.mixeration.titanblocks.utils;

import mertgokhan.mixeration.titanblocks.config.Storage;
import org.bukkit.Bukkit;

public class Create {

    public void createNewBlock(int x, int y, int z, String name) {
        if(Storage.getConfig().getString("titan-blocks." + name) == null) {
            Storage.getConfig().set("titan-blocks." + name + ".x", x);
            Storage.getConfig().set("titan-blocks." + name + ".y", y);
            Storage.getConfig().set("titan-blocks." + name + ".z", z);
            Storage.getConfig().set("titan-blocks." + name + ".triggered-at", 5);
            Storage.saveConfig();
            Bukkit.getLogger().warning("[TitanBlocks@Error] New block created");
        } else {
            Bukkit.getLogger().warning("[TitanBlocks@Error] A block found with that name...");
        }
    }

}
