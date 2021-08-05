package com.profiiqus.levelo;

import com.profiiqus.levelo.config.Configuration;
import com.profiiqus.levelo.object.LeveloPlayer;
import com.profiiqus.levelo.storage.Storage;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Levelo extends JavaPlugin {

    @Getter
    private Configuration configuration;

    @Getter
    private Storage storage;

    @Override
    public void onEnable() {

        try {
            this.configuration = new Configuration(this);
            LeveloPlayer.config = this.configuration;

            this.storage = new Storage(this);
        }
    }

    @Override
    public void onDisable() {

    }
}
