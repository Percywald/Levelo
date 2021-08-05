package com.profiiqus.levelo.storage.providers.yaml;

import com.profiiqus.levelo.object.LeveloPlayer;

public interface PlayerLoadCallback {

    void onLoadingDone(LeveloPlayer player);

}