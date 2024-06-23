package com.antonvovk.cyberpowerupsapi.services.cyberpower;

import com.antonvovk.cyberpowerupsapi.services.cyberpower.models.CyberPowerUps;
import com.antonvovk.cyberpowerupsapi.services.system.SystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class CyberPowerServiceImpl implements CyberPowerService {

    private final SystemService systemService;

    @Override
    public CyberPowerUps getUps() {
        var response = systemService.executeCommand("sudo", "pwrstat", "-status");
        var ups = CyberPowerUps.builder()
                .modelName(extractStringValue(response, Pattern.compile("Model Name\\.* (.*)")))
                .firmwareNumber(extractStringValue(response, Pattern.compile("Firmware Number\\.* (.*)")))
                .ratingVoltage(extractIntegerValue(response, Pattern.compile("Rating Voltage\\.* ([0-9]*)")))
                .ratingPowerInWatts(extractIntegerValue(response, Pattern.compile("Rating Power\\.* ([0-9]*)")))
                .state(extractStringValue(response, Pattern.compile("State\\.* (.*)")))
                .powerSupplyBy(extractStringValue(response, Pattern.compile("Power Supply by\\.* (.*)")))
                .utilityVoltage(extractIntegerValue(response, Pattern.compile("Utility Voltage\\.* ([0-9]*)")))
                .outputVoltage(extractIntegerValue(response, Pattern.compile("Output Voltage\\.* ([0-9]*)")))
                .batteryCapacity(extractIntegerValue(response, Pattern.compile("Battery Capacity\\.* ([0-9]*)")))
                .remainingRuntimeInMinutes(extractIntegerValue(response, Pattern.compile("Remaining Runtime\\.* ([0-9]*)")))
                .loadInWatts(extractIntegerValue(response, Pattern.compile("Load\\.* ([0-9]*) Watt")))
                .loadInPercentage(extractIntegerValue(response, Pattern.compile("Load\\.* [0-9]* Watt\\(([0-9]*) %\\)")))
                .lineInteraction(extractStringValue(response, Pattern.compile("Line Interaction\\.* (.*)")))
                .testResult(extractStringValue(response, Pattern.compile("Test Result\\.* (.*)")))
                .lastPowerEvent(extractStringValue(response, Pattern.compile("Last Power Event\\.* (.*)")))
                .build();
        log.info("Retrieved UPS {}", ups);
        return ups;
    }

    private String extractStringValue(String content, Pattern pattern) {
        var matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private Integer extractIntegerValue(String content, Pattern pattern) {
        var stringValue = extractStringValue(content, pattern);
        if (stringValue == null) {
            return null;
        }
        return Integer.parseInt(stringValue);
    }
}
