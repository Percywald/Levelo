package com.profiiqus.nextlevels.storage.providers.mysql;

import com.profiiqus.nextlevels.object.NextPlayer;
import com.profiiqus.nextlevels.storage.IDataProvider;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class MySQLProvider implements IDataProvider {

    public MySQLProvider() {

    }

    public void init() {

    }

    @Override
    public NextPlayer getPlayer(UUID uniqueID) {
        return null;
    }

    @Override
    public Map<UUID, NextPlayer> getPlayers(Collection<? extends Player> players) {
        return null;
    }

    @Override
    public void savePlayer(NextPlayer player) {

    }

    @Override
    public void savePlayers(Map<UUID, NextPlayer> players) {

    }
}
