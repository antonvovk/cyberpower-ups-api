package com.antonvovk.cyberpowerupsapi.services.cyberpower.models;

import lombok.Builder;

@Builder
public record CyberPowerUps(String modelName,
                            String firmwareNumber,
                            Integer ratingVoltage,
                            Integer ratingPowerInWatts,
                            String state,
                            String powerSupplyBy,
                            Integer utilityVoltage,
                            Integer outputVoltage,
                            Integer batteryCapacity,
                            Integer remainingRuntimeInMinutes,
                            Integer loadInWatts,
                            Integer loadInPercentage,
                            String lineInteraction,
                            String testResult,
                            String lastPowerEvent
) {
}
