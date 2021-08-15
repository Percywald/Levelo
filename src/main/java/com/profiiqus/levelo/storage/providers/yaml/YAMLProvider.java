package com.profiiqus.levelo.storage.providers.yaml;

import com.profiiqus.levelo.Levelo;
import com.profiiqus.levelo.object.LeveloPlayer;
import com.profiiqus.levelo.storage.IDataProvider;
import com.profiiqus.levelo.storage.Storage;
import org.bukkit.Bukkit;
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

    private final Levelo plugin;
    private final Storage storage;
    private final File file;

    public YAMLProvider(final Levelo plugin) {
        this.plugin = plugin;
        this.storage = plugin.getStorage();
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
    public LeveloPlayer getPlayer(final UUID uniqueID) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String stringID = uniqueID.toString();
        if(config.contains(stringID)) {
            int level = config.getInt(stringID + ".level");
            double experience = config.getDouble(stringID + ".experience");
            return new LeveloPlayer(uniqueID, level, experience);
        }

        // player does not exist, return null
        return null;
    }

    @Override
    public Map<UUID, LeveloPlayer> getPlayers(Collection<? extends Player> players) {
        return null;
    }

    @Override
    public void savePlayer(LeveloPlayer player) {

    }

    @Override
    public void savePlayers(Map<UUID, LeveloPlayer> players) {

    }
}
