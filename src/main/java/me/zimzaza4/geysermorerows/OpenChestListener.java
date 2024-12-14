package me.zimzaza4.geysermorerows;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import net.kyori.adventure.text.Component;
import org.geysermc.floodgate.api.FloodgateApi;

public class OpenChestListener implements PacketListener {
    public OpenChestListener(GeyserMoreRows plugin) {
        this.plugin = plugin;
    }

    private GeyserMoreRows plugin;
    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.OPEN_WINDOW) {
            return;
        }

        if (FloodgateApi.getInstance().isFloodgatePlayer(event.getUser().getUUID())) {
            WrapperPlayServerOpenWindow openWindow = new WrapperPlayServerOpenWindow(event);
            Component title = openWindow.getTitle();
            if (title == null) title = Component.empty();
            if (openWindow.getType() > 4 || openWindow.getType() == 2) {
                return;
            }
            int i = openWindow.getType() + 1;
            openWindow.setTitle(title.append(Component.text(plugin.getConfig().getString("append-text." + i, ""))));
            openWindow.write();
        }
    }
}
