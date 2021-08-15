package com.profiiqus.levelo.storage;

import com.profiiqus.levelo.object.LeveloPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

/**
 * Interface for the creation of different Data providers
 * @author ProfiiQus
 */
public interface IDataProvider {

    /**
     * Gets player by his UUID
     * @param uniqueID Player's UUID
     * @return
     */
    LeveloPlayer getPlayer(UUID uniqueID);

    /**
     * Gets player by his nickname
     * @param playerName Player's nickname
     * @return
     */
    LeveloPlayer getPlayer(String playerName);

    /**
     * Gets player data of provided player collection.
     * @param players Players to get from Data Provider
     * @return
     */
    HashMap<UUID, LeveloPlayer> getPlayers(Collection<Player> players);

    /**
     * Saves player into Data provider.
     * @param player Player object being saved
     */
    void savePlayer(LeveloPlayer player);

    /**
     * Saves players into Data provider.
     * @param players Hash map of player data to save
     * @param async Whether to save them async (onDisable -> needs to be sync)
     */
    void savePlayers(HashMap<UUID, LeveloPlayer> players, boolean async);

}
