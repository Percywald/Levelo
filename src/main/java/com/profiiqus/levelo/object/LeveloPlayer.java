package com.profiiqus.levelo.object;

import com.profiiqus.levelo.config.Configuration;
import lombok.Getter;

import java.util.UUID;

public class LeveloPlayer {

    public static Configuration config;

    @Getter
    private final UUID uniqueID;

    @Getter
    private final String lastUsername;

    @Getter
    private final int level;

    @Getter
    private final double experience;

    public LeveloPlayer(UUID uniqueID, String lastUsername, int level, double experience) {
        this.uniqueID = uniqueID;
        this.lastUsername = lastUsername;
        this.level = level;
        this.experience = experience;
    }

    public double getRequiredExperience() {

    }

    public double getRemainingExperience() {
        return this.experience - this.getRequiredExperience();
    }
}
