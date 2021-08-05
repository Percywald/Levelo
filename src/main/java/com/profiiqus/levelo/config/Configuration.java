package com.profiiqus.levelo.config;

import com.profiiqus.levelo.Levelo;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Logger;

public class Configuration {

    private final Levelo plugin;
    private final File configFile, messageFile, experienceFile;
    private final Logger log;

    @Getter
    private String dataProviderType;

    @Getter
    private boolean useExperienceFormula, maxLevelEnabled, maxLevelGrantXPOver;

    @Getter
    private int X, Y, Z, W, requiredStaticExperience, maxLevel;

    public Configuration(final Levelo plugin) {
        this.plugin = plugin;
        this.log = plugin.getLogger();
        this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        this.messageFile = new File(this.plugin.getDataFolder(), "messages.yml");
        this.experienceFile = new File(this.plugin.getDataFolder(), "experience.yml");
        this.reload();
    }

    private void reload() {

        if (!this.configFile.exists()) {
            this.configFile.getParentFile().mkdirs();
            this.plugin.saveResource(configFile.getName(), false);
        }

        if (!this.messageFile.exists()) {
            this.messageFile.getParentFile().mkdirs();
            this.plugin.saveResource(messageFile.getName(), false);
        }

        if (!this.experienceFile.exists()) {
            this.experienceFile.getParentFile().mkdirs();
            this.plugin.saveResource(experienceFile.getName(), false);
        }

        // Load config.yml file
        FileConfiguration config = YamlConfiguration.loadConfiguration(this.configFile);
        this.dataProviderType = config.getString("storage");

        // Load messages.yml file
        config = YamlConfiguration.loadConfiguration(this.messageFile);

        // Load experience.yml file
        config = YamlConfiguration.loadConfiguration(this.experienceFile);
        this.useExperienceFormula = config.getBoolean("use-experience-formula");
        this.X = config.getInt("experience-formula.X");
        this.Y = config.getInt("experience-formula.Y");
        this.Z = config.getInt("experience-formula.Z");
        this.W = config.getInt("experience-formula.W");
        this.requiredStaticExperience = config.getInt("static-experience");
        this.maxLevelEnabled = config.getBoolean("max-level.enabled");
        this.maxLevel = config.getInt("max-level.level");
        this.maxLevelGrantXPOver = config.getBoolean("max-level.grant-xp-over");

        log.info("Finished loading configuration!");
    }
}
