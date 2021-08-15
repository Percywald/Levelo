package com.profiiqus.levelo.event;

import com.profiiqus.levelo.object.LeveloPlayer;
import com.profiiqus.levelo.storage.Storage;
import com.profiiqus.levelo.storage.callback.PlayerLoadCallback;
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
        storage.getPlayer(uniqueID, player -> storage.cachePlayer(player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        storage.dropPlayer(event.getPlayer().getUniqueId());
    }
}
