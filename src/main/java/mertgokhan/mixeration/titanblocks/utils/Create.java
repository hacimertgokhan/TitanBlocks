package mertgokhan.mixeration.titanblocks.utils;

import mertgokhan.mixeration.titanblocks.config.Plugin;
import mertgokhan.mixeration.titanblocks.config.Storage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class Create {

    public void createNewBlock(int x, int y, int z, String name, String world) {
        if(Storage.getConfig().getString("titan-blocks." + name) == null) {
            Storage.getConfig().set("titan-blocks." + name + ".x", x);
            Storage.getConfig().set("titan-blocks." + name + ".y", y);
            Storage.getConfig().set("titan-blocks." + name + ".z", z);
            if(Bukkit.getWorld(world) == null) {
                Bukkit.getLogger().warning("[TitanBlocks@Error] World could not be null !");
            } else {
                Storage.getConfig().set("titan-blocks." + name + ".world", name);
                Bukkit.getLogger().warning(String.format("[TitanBlocks@Warning] World: %s selected for %s !", world, name));
            }

            Storage.getConfig().set("titan-blocks." + name + ".name", name);
            Storage.getConfig().set("titan-blocks." + name + ".health", 5);
            Storage.saveConfig();
            if(Plugin.getConfig().getString("titan-blocks.blocks." + name) == null) {
                List<String> broadcastList = new ArrayList<>();
                broadcastList.add(ChatColor.translateAlternateColorCodes('&', "&f"));
                broadcastList.add(ChatColor.translateAlternateColorCodes('&', "&a&lTitanBlocks&f: &7Block <name> remaining <health> health !"));
                broadcastList.add(ChatColor.translateAlternateColorCodes('&', "&f"));
                Plugin.getConfig().set("titan-blocks.blocks." + name + ".send-broadcast-message-at", 10);
                Plugin.getConfig().set("titan-blocks.blocks." + name + ".ask-permission", false);
                Plugin.getConfig().set("titan-blocks.blocks." + name + ".permission", "titanblocks.blocks." + name);
                Plugin.getConfig().set("titan-blocks.blocks." + name + ".broadcast", broadcastList);
                Plugin.saveConfig();
            } else {
                Bukkit.getLogger().warning("[TitanBlocks@Error] A block found with that name in config.yml");
            }
            Bukkit.getLogger().warning("[TitanBlocks@Error] New block created, block name: " + name);
        } else {
            Bukkit.getLogger().warning("[TitanBlocks@Error] A block found with that name in storage.yml");
        }
    }

}
