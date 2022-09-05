package mertgokhan.mixeration.titanblocks.events;

import com.sun.org.apache.xpath.internal.operations.Bool;
import mertgokhan.mixeration.titanblocks.TitanBlocks;
import mertgokhan.mixeration.titanblocks.config.Message;
import mertgokhan.mixeration.titanblocks.config.Plugin;
import mertgokhan.mixeration.titanblocks.config.Storage;
import mertgokhan.mixeration.titanblocks.utils.Messenger;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.UUID;

import static mertgokhan.mixeration.titanblocks.utils.Maps.healthCounter;
import static mertgokhan.mixeration.titanblocks.utils.Maps.healthy;
import static mertgokhan.mixeration.titanblocks.utils.Ready.isStorageNull;

public class BlockBreak implements Listener {
    public BlockBreak(TitanBlocks titanBlocks) {}
    public Messenger messenger = new Messenger();

    @EventHandler
    public void blockBreak(BlockBreakEvent blockBreakEvent) {
        Player player = blockBreakEvent.getPlayer();
        Block block = blockBreakEvent.getBlock();
        int block_x = block.getLocation().getBlockX();
        int block_y = block.getLocation().getBlockY();
        int block_z = block.getLocation().getBlockZ();
        if (!isStorageNull) {
            for (String blocks : Storage.getConfig().getConfigurationSection("titan-blocks.").getKeys(false)) {
                int x = Storage.getConfig().getInt("titan-blocks." + blocks + ".x");
                int y = Storage.getConfig().getInt("titan-blocks." + blocks + ".y");
                int z = Storage.getConfig().getInt("titan-blocks." + blocks + ".z");
                int health = Storage.getConfig().getInt("titan-blocks." + blocks + ".health");
                String name = Storage.getConfig().getString("titan-blocks." + blocks + ".name");
                String world = Storage.getConfig().getString("titan-blocks." + blocks + ".world");
                if (block_x == x) {
                    if (block_y == y) {
                        if (block_z == z) {
                            if (block.getWorld().getName().equalsIgnoreCase(world)) {
                                blockBreakEvent.setCancelled(true);
                                blockBreakEvent.setDropItems(false);
                                if (Plugin.getConfig().getBoolean("titan-blocks.blocks." + name + ".ask-permission")) {
                                    if (player.hasPermission(Plugin.getConfig().getString("titan-blocks.blocks." + name + ".permission"))) {
                                        tbGo(player, block, health, name);
                                    } else {
                                        messenger.send(player, "messages.no-permission");
                                    }
                                } else {
                                    tbGo(player, block, health, name);
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
    }

    private void tbGo(Player player, Block block, int health, String name) {
        if (healthy.get(name)) {
            if (healthCounter.get(name) < health) {
                healthCounter.put(name, (healthCounter.get(name) - 1));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.getConfig().getString("messages.block-healthy-down")));
            } else if (healthCounter.get(name) == Plugin.getConfig().getInt("titan-blocks.blocks." + name + ".send-broadcast-message-at")) {
                for (String message : Plugin.getConfig().getStringList("titan-blocks.blocks." + name + ".broadcast")) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replace("<name>", name).replace("<health>", String.valueOf(healthy.get(name)))));
                }
            } else if (healthCounter.get(name) == 0) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Message.getConfig().getString("messages.block-breaked").replace("<player>", player.getName()).replace("<name>", name)));
                for (int i = 0; i < 3; i++) {
                    block.getWorld().strikeLightning(block.getLocation());
                }
                for (int i = 0; i < 15; i++) {
                    block.getWorld().spawnParticle(Particle.REDSTONE,
                            block.getX(),
                            block.getY() + 1,
                            block.getZ(),
                            20, 0.001, 1, 0, 1);
                }
            }
        }
    }


}
