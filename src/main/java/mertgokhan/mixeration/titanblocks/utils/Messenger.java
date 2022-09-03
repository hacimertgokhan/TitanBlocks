package mertgokhan.mixeration.titanblocks.utils;

import mertgokhan.mixeration.titanblocks.config.Message;
import mertgokhan.mixeration.titanblocks.utils.interfaces.MessageAdapter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messenger implements MessageAdapter {
    @Override
    public void send(CommandSender player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.getConfig().getString(message)));
    }

    @Override
    public String message(String message) {
        return Message.getConfig().getString(message);
    }

}
