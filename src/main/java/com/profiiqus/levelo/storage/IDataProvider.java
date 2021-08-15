package com.profiiqus.levelo.storage;

import com.profiiqus.levelo.object.LeveloPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Interface for the creation of different Data providers
 * @author ProfiiQus
 */
public interface IDataProvider {

    /**
     * Initializes the selected data provider.
     * Only run, if the provider is selected as the active one.
     */
    void init();

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
    Map<UUID, LeveloPlayer> getPlayers(Collection<? extends Player> players);

    /**
     * Saves player into Data provider.
     * @param player Player object being saved
     */
    void savePlayer(LeveloPlayer player);

    /**
     * Saves players into Data provider.
     * @param players Hash map of player data to save
     */
    void savePlayers(final Map<UUID, LeveloPlayer> players);

}
