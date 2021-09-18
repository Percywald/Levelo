package com.profiiqus.nextlevels;

import com.profiiqus.nextlevels.config.Configuration;
import com.profiiqus.nextlevels.object.NextPlayer;
import com.profiiqus.nextlevels.storage.Storage;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextLevels extends JavaPlugin {

    @Getter
    private Configuration configuration;

    @Getter
    private Storage storage;

    @Override
    public void onEnable() {

        try {
            this.configuration = new Configuration(this);
            NextPlayer.config = this.configuration;

            this.storage = new Storage(this);
        }
    }

    @Override
    public void onDisable() {

    }
}
