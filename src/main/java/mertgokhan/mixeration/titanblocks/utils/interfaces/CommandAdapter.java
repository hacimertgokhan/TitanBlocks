package mertgokhan.mixeration.titanblocks.utils.interfaces;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface CommandAdapter {

    boolean isConsole(CommandSender commandSender);

    boolean has(CommandSender player);

}
