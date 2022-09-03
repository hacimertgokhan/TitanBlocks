package mertgokhan.mixeration.titanblocks;

import mertgokhan.mixeration.titanblocks.config.Message;
import mertgokhan.mixeration.titanblocks.config.Plugin;
import mertgokhan.mixeration.titanblocks.config.Storage;
import mertgokhan.mixeration.titanblocks.utils.Ready;
import org.bukkit.plugin.java.JavaPlugin;

public final class TitanBlocks extends JavaPlugin {
    private static TitanBlocks titanBlocks;
    public static TitanBlocks get() {return titanBlocks;}
    public void set(TitanBlocks titanBlocks) {
        TitanBlocks.titanBlocks = titanBlocks;}
    public final Plugin configYAML = new Plugin(this, "config.yml");
    public final Storage storage = new Storage(this, "storage.yml");
    public final Message message = new Message(this, "lang.yml");
    public final Ready ready = new Ready();

    @Override
    public void onEnable() {
        set(this);
        configYAML.create();
        storage.create();
        message.create();
        ready.loadAll();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
