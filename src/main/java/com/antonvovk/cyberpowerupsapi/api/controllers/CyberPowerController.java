package com.antonvovk.cyberpowerupsapi.api.controllers;

import com.antonvovk.cyberpowerupsapi.services.cyberpower.CyberPowerService;
import com.antonvovk.cyberpowerupsapi.services.cyberpower.models.CyberPowerUps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cyberpower/ups")
@RequiredArgsConstructor
public class CyberPowerController {

    private final CyberPowerService cyberPowerService;

    @GetMapping
    public CyberPowerUps getUps() {
        return cyberPowerService.getUps();
    }
}
