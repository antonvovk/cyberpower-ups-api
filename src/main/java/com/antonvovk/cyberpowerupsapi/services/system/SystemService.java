package com.antonvovk.cyberpowerupsapi.services.system;

import com.antonvovk.cyberpowerupsapi.services.system.models.CommandResponse;

public interface SystemService {

    CommandResponse executeCommand(String... command);
}
