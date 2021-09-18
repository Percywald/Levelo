package com.profiiqus.nextlevels.storage;

import com.profiiqus.nextlevels.NextLevels;
import com.profiiqus.nextlevels.config.Configuration;
import com.profiiqus.nextlevels.object.NextPlayer;
import com.profiiqus.nextlevels.storage.callback.PlayerCollectionLoadCallback;
import com.profiiqus.nextlevels.storage.callback.PlayerLoadCallback;
import com.profiiqus.nextlevels.storage.providers.mysql.MySQLProvider;
import com.profiiqus.nextlevels.storage.providers.yaml.YAMLProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Storage {

    private final NextLevels plugin;
    private final Configuration config;
    private final Map<String, IDataProvider> availableProviders;
    private Map<UUID, NextPlayer> playerData;
    private final IDataProvider dataProvider;

    public Storage(NextLevels plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfiguration();
        this.availableProviders = new HashMap<String, IDataProvider>() {
            {
                put("yaml", new YAMLProvider(plugin));
                put("mysql", new MySQLProvider());
            }
        };
        this.dataProvider = this.getConfiguredProvider();
        this.dataProvider.init();
        this.loadPlayers(Bukkit.getOnlinePlayers(), data -> playerData = data);
    }

    public IDataProvider getConfiguredProvider() {
        String configuredProvider = config.getDataProviderType().toLowerCase();
        if(this.availableProviders.containsKey(configuredProvider)) {
            return this.availableProviders.get(configuredProvider);
        } else {
            return this.availableProviders.get("yaml");
        }
    }

    public void getPlayer(final UUID uniqueID, final PlayerLoadCallback callback) {
        new BukkitRunnable() {
            @Override
            public void run() {
                final NextPlayer player = dataProvider.getPlayer(uniqueID);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        callback.onLoadingDone(player);
                    }
                }.runTask(plugin);
            }
        }.runTaskAsynchronously(this.plugin);
    }

    public void cachePlayer(final NextPlayer player) {
        this.playerData.put(player.getUniqueID(), player);
    }

    public void dropPlayer(final UUID uniqueID) {
        this.playerData.remove(uniqueID);
    }

    public void loadPlayers(final Collection<? extends Player> players, final PlayerCollectionLoadCallback callback) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Map<UUID, NextPlayer> playerData = dataProvider.getPlayers(players);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        callback.onLoadingDone(playerData);
                    }
                }.runTaskAsynchronously(plugin);
            }
        }.runTaskAsynchronously(this.plugin);
    }

    public void savePlayer(final UUID uniqueID) {
        new BukkitRunnable() {
            @Override
            public void run() {
                dataProvider.savePlayer(playerData.get(uniqueID));
            }
        }.runTaskAsynchronously(this.plugin);
    }

    public void savePlayers(final Map<UUID, NextPlayer> playerData, final boolean async) {
        if(async) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    dataProvider.savePlayers(playerData);
                }
            }.runTaskAsynchronously(this.plugin);
            return;
        }

        dataProvider.savePlayers(playerData);
    }

    public Map<UUID, NextPlayer> getPlayerData() {
        return this.playerData;
    }

}
