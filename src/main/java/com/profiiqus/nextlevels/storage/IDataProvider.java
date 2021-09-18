package com.profiiqus.nextlevels.storage;

import com.profiiqus.nextlevels.object.NextPlayer;
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
    NextPlayer getPlayer(UUID uniqueID);

    /**
     * Gets player data of provided player collection.
     * @param players Players to get from Data Provider
     * @return
     */
    Map<UUID, NextPlayer> getPlayers(Collection<? extends Player> players);

    /**
     * Saves player into Data provider.
     * @param player Player object being saved
     */
    void savePlayer(NextPlayer player);

    /**
     * Saves players into Data provider.
     * @param players Hash map of player data to save
     */
    void savePlayers(final Map<UUID, NextPlayer> players);

}
