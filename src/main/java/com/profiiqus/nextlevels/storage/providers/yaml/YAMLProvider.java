package com.profiiqus.nextlevels.storage.providers.yaml;

import com.profiiqus.nextlevels.NextLevels;
import com.profiiqus.nextlevels.object.NextPlayer;
import com.profiiqus.nextlevels.storage.IDataProvider;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class YAMLProvider implements IDataProvider {

    private final File file;

    public YAMLProvider(final NextLevels plugin) {
        this.file = new File(plugin.getDataFolder(), "player-data.yml");
    }

    @Override
    public void init() {
        this.file.getParentFile().mkdirs();
        if(!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public NextPlayer getPlayer(final UUID uniqueID) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String stringID = uniqueID.toString();
        if(config.contains(stringID)) {
            int level = config.getInt(stringID + ".level");
            double experience = config.getDouble(stringID + ".experience");
            return new NextPlayer(uniqueID, level, experience);
        }

        // player does not exist, return null
        return null;
    }

    @Override
    public Map<UUID, NextPlayer> getPlayers(Collection<? extends Player> players) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Map<UUID, NextPlayer> resultMap = new HashMap<>();
        UUID uniqueID;
        String stringID;
        for(Player player: players) {
            uniqueID = player.getUniqueId();
            stringID = uniqueID.toString();
            if(config.contains(stringID)) {
                int level = config.getInt(stringID + ".level");
                double experience = config.getDouble(stringID + ".double");
                resultMap.put(uniqueID, new NextPlayer(uniqueID, level, experience));
            }
        }
        return resultMap;
    }

    @Override
    public void savePlayer(NextPlayer player) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String stringID = player.getUniqueID().toString();
        config.set(stringID + ".level", player.getLevel());
        config.set(stringID + ".experience", player.getExperience());
        this.trySaveFile(config);
    }

    @Override
    public void savePlayers(final Map<UUID, NextPlayer> players) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String stringID;
        NextPlayer player;
        for(Map.Entry<UUID, NextPlayer> entry: players.entrySet()) {
            stringID = entry.getKey().toString();
            player = entry.getValue();
            config.set(stringID + ".level", player.getLevel());
            config.set(stringID + ".experience", player.getExperience());
        }
        this.trySaveFile(config);
    }

    private void trySaveFile(final FileConfiguration config) {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
