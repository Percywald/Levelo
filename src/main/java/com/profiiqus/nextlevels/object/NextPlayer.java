package com.profiiqus.nextlevels.object;

import com.profiiqus.nextlevels.config.Configuration;
import lombok.Getter;

import java.util.UUID;

public class NextPlayer {

    public static Configuration config;

    @Getter
    private final UUID uniqueID;

    @Getter
    private final int level;

    @Getter
    private final double experience;

    public NextPlayer(UUID uniqueID, int level, double experience) {
        this.uniqueID = uniqueID;
        this.level = level;
        this.experience = experience;
    }

    public double getRequiredExperience() {

    }

    public double getRemainingExperience() {
        return this.experience - this.getRequiredExperience();
    }
}
