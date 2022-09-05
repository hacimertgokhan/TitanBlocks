package mertgokhan.mixeration.titanblocks.commands;

import mertgokhan.mixeration.titanblocks.TitanBlocks;
import mertgokhan.mixeration.titanblocks.config.Message;
import mertgokhan.mixeration.titanblocks.config.Plugin;
import mertgokhan.mixeration.titanblocks.config.Storage;
import mertgokhan.mixeration.titanblocks.utils.Commands;
import mertgokhan.mixeration.titanblocks.utils.Create;
import mertgokhan.mixeration.titanblocks.utils.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static mertgokhan.mixeration.titanblocks.utils.Maps.healthCounter;
import static mertgokhan.mixeration.titanblocks.utils.Maps.healthy;

public class TitanBlocksAdministrator implements CommandExecutor {
    public Commands commands = new Commands();
    public Create create = new Create();
    public Messenger messenger = new Messenger();
    public TitanBlocksAdministrator(TitanBlocks titanBlocks) {}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (!player.hasPermission(Plugin.getConfig().getString("titan-blocks.administrator"))) {
                messenger.send(sender, "messages.no-permission");
            } else {
                if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                    for (String message : Message.getConfig().getStringList("messages.help")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                    }
                } else if (args.length == 6) {
                    if (args[0].equalsIgnoreCase("create")) {
                        if (commands.isInteger(args[1])) {
                            if (commands.isInteger(args[2])) {
                                if (commands.isInteger(args[3])) {
                                    if (commands.isWorldNull(args[4])) {
                                        create.createNewBlock(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[5].toLowerCase(), args[4]);
                                        messenger.send(sender, "messages.new-block-created");
                                    } else {
                                        messenger.send(sender, "messages.world-cant-be-null");
                                    }
                                } else {
                                    messenger.send(sender, "messages.must-be-integer");
                                }
                            } else {
                                messenger.send(sender, "messages.must-be-integer");
                            }
                        } else {
                            messenger.send(sender, "messages.must-be-integer");
                        }
                    } else {
                        messenger.send(sender, "messages.usage");
                    }
                } else if (args.length == 1) {
                    if(args[0].equalsIgnoreCase("reload")) {
                        Storage.reloadConfig();
                        Message.reloadConfig();
                        Plugin.reloadConfig();
                        messenger.send(sender, "messages.plugin-reloaded");
                    } else {
                        messenger.send(sender, "messages.usage");
                    }
                } else if (args.length == 3) {
                    boolean typeBool = false;
                    if(args[0].equalsIgnoreCase("manualRegis")) {
                        if(args[1].equalsIgnoreCase("false")) {
                            typeBool = false;
                        } else if (args[1].equalsIgnoreCase("true")) {
                            typeBool = true;
                        }
                        healthy.put(args[2].toString(), typeBool);
                        healthCounter.put(args[2].toString(), 0);
                        messenger.send(sender, "messages.block-registered");
                    }
                } else {
                    messenger.send(sender, "messages.usage");
                }
            }
        } else {
            Bukkit.getLogger().warning(messenger.message("messages.cant-do-that-from-console"));
        }
        return false;
    }
}
