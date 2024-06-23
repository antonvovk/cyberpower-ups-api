package com.antonvovk.cyberpowerupsapi.services.system;

import com.antonvovk.cyberpowerupsapi.services.system.models.CommandResult;

public interface SystemService {

    CommandResult executeCommand(String... command);
}
