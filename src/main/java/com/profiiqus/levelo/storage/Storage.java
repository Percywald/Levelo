package com.profiiqus.levelo.storage;

import com.profiiqus.levelo.Levelo;
import com.profiiqus.levelo.config.Configuration;
import com.profiiqus.levelo.object.LeveloPlayer;
import com.profiiqus.levelo.storage.callback.PlayerLoadCallback;
import com.profiiqus.levelo.storage.providers.mysql.MySQLProvider;
import com.profiiqus.levelo.storage.providers.yaml.YAMLProvider;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Storage {

    private final Levelo plugin;
    private final Configuration config;
    private final Map<String, IDataProvider> availableProviders;
    private final Map<UUID, LeveloPlayer> playerData;
    private final IDataProvider dataProvider;

    public Storage(Levelo plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfiguration();
        this.availableProviders = new HashMap<String, IDataProvider>() {
            {
                put("yaml", new YAMLProvider());
                put("mysql", new MySQLProvider());
            }
        };
        this.dataProvider = this.getConfiguredProvider();
        this.playerData = this.dataProvider.getPLaye
    }

    public IDataProvider getConfiguredProvider() {
        String configuredProvider = config.getDataProviderType().toLowerCase();
        if(this.availableProviders.containsKey(configuredProvider)) {
            return this.availableProviders.get(configuredProvider);
        } else {
            return new YAMLProvider();
        }
    }

    public void getPlayer(final UUID uniqueID, final PlayerLoadCallback callback) {
        new BukkitRunnable() {
            @Override
            public void run() {
                final LeveloPlayer player = dataProvider.getPlayer(uniqueID);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        callback.onLoadingDone(player);
                    }
                }.runTask(plugin);
            }
        }.runTaskAsynchronously(this.plugin);
    }

    public void getPlayer(final String playerName, final PlayerLoadCallback callback) {
        new BukkitRunnable() {
            @Override
            public void run() {
                final LeveloPlayer player = dataProvider.getPlayer(playerName);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        callback.onLoadingDone(player);
                    }
                }.runTask(plugin);
            }
        }.runTaskAsynchronously(this.plugin);
    }

    public void cachePlayer(final LeveloPlayer player) {
        this.playerData.put(player.getUniqueID(), player);
    }

    public void dropPlayer(final UUID uniqueID) {
        this.playerData.remove(uniqueID);
    }

}
