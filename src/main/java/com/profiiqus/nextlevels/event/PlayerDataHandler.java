package com.profiiqus.nextlevels.event;

import com.profiiqus.nextlevels.object.NextPlayer;
import com.profiiqus.nextlevels.storage.Storage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerDataHandler implements Listener {

    private final Storage storage;

    public PlayerDataHandler(Storage storage) {
        this.storage = storage;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uniqueID = event.getPlayer().getUniqueId();
        storage.getPlayer(uniqueID, player -> {
            if(player == null) {
                player = new NextPlayer(uniqueID, 1, 0.0D);
            }
            storage.cachePlayer(player);
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uniqueID = event.getPlayer().getUniqueId();
        storage.savePlayer(uniqueID);
        storage.dropPlayer(uniqueID);
    }
}
