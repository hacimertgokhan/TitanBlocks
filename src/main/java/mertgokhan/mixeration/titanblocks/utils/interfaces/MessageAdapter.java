package mertgokhan.mixeration.titanblocks.utils.interfaces;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface MessageAdapter {

    void send(CommandSender player, String message);

    String message(String message);

    void debug(String debug);

}
