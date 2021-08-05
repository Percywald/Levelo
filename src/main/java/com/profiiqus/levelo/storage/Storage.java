package com.profiiqus.levelo.storage;

import com.profiiqus.levelo.Levelo;
import com.profiiqus.levelo.config.Configuration;
import com.profiiqus.levelo.object.LeveloPlayer;
import com.profiiqus.levelo.storage.providers.mysql.MySQLProvider;
import com.profiiqus.levelo.storage.providers.yaml.YAMLProvider;

import java.util.HashMap;
import java.util.UUID;

public class Storage {

    private final Levelo plugin;
    private final Configuration config;
    private final HashMap<String, IDataProvider> availableProviders;
    private final HashMap<UUID, LeveloPlayer> playerData;
    private final IDataProvider dataProvider;

    public Storage(Levelo plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfiguration();
        this.availableProviders = new HashMap<String, IDataProvider>() {
            {
                put("yaml", new YAMLProvider());
                put("mysql", new MySQLProvider());
            }
        };
        this.dataProvider = this.getConfiguredProvider();
    }

    public IDataProvider getConfiguredProvider() {
        String configuredProvider = config.getDataProviderType().toLowerCase();
        if(this.availableProviders.containsKey(configuredProvider)) {
            return this.availableProviders.get(configuredProvider);
        } else {
            return new YAMLProvider();
        }
    }

}
