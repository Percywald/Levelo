package com.profiiqus.levelo.storage.providers.mysql;

import com.profiiqus.levelo.object.LeveloPlayer;
import com.profiiqus.levelo.storage.IDataProvider;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class MySQLProvider implements IDataProvider {

    @Override
    public LeveloPlayer getPlayer(UUID uniqueID) {
        return null;
    }

    @Override
    public LeveloPlayer getPlayer(String playerName) {
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
