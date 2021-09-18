package com.profiiqus.nextlevels.storage.callback;

import com.profiiqus.nextlevels.object.NextPlayer;

import java.util.Map;
import java.util.UUID;

public interface PlayerCollectionLoadCallback {

    void onLoadingDone(Map<UUID, NextPlayer> playerData);

}
