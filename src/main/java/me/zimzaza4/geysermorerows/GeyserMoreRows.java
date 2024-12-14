package me.zimzaza4.geysermorerows;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class GeyserMoreRows extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        PacketEvents.getAPI().getEventManager().registerListener(new OpenChestListener(this), PacketListenerPriority.HIGHEST);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
