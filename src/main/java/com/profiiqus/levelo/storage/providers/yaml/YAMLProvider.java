package com.profiiqus.levelo.storage.providers.yaml;

import com.profiiqus.levelo.Levelo;
import com.profiiqus.levelo.object.LeveloPlayer;
import com.profiiqus.levelo.storage.Storage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class YAMLProvider {

    private final Levelo plugin;
    private final Storage storage;
    private final File file;

    public YAMLProvider(final Levelo plugin, final Storage storage) {
        this.plugin = plugin;
        this.storage = storage;
        this.file = new File(plugin.getDataFolder(), "player-data.yml");
        this.file.getParentFile().mkdirs();
        if(!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<UUID, LeveloPlayer> loadOnlinePlayers() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Map<UUID, LeveloPlayer> resultMap = new HashMap<>();

        for(Player p: Bukkit.getOnlinePlayers()) {
            UUID uniqueID = p.getUniqueId();
            String stringID = uniqueID.toString();
            if(config.getKeys(false).contains(stringID)) {
                resultMap.put(uniqueID, new NextPlayer(
                        uniqueID,
                        config.getLong(stringID + ".time-played"),
                        config.getLong(stringID + ".first-joined"))
                );
            } else {
                resultMap.put(uniqueID, new NextPlayer(
                        uniqueID,
                        0l,
                        System.currentTimeMillis()
                ));
            }
        }

        return resultMap;
    }
}
