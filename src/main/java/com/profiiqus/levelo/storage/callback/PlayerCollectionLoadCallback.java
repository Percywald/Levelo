package com.profiiqus.levelo.storage.callback;

import com.profiiqus.levelo.object.LeveloPlayer;

import java.util.Map;
import java.util.UUID;

public interface PlayerCollectionLoadCallback {

    void onLoadingDone(Map<UUID, LeveloPlayer> playerData);

}
