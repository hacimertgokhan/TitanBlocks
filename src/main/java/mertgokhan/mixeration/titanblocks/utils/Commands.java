package mertgokhan.mixeration.titanblocks.utils;

import mertgokhan.mixeration.titanblocks.config.Plugin;
import mertgokhan.mixeration.titanblocks.utils.interfaces.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Commands implements CommandAdapter {
    @Override
    public boolean isConsole(CommandSender commandSender) {
        return !(commandSender instanceof Player);
    }

    @Override
    public boolean has(CommandSender player) {
        return player.hasPermission(Plugin.getConfig().getString("titan-blocks.administrator")) && player.hasPermission("titan.blocks") && player.isOp();
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isWorldNull(String w) {
        World world = Bukkit.getWorld(w);
        if(world == null) return false;
        else return true;
    }

}
