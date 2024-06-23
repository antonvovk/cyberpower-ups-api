package com.antonvovk.cyberpowerupsapi.services.system;

import com.antonvovk.cyberpowerupsapi.services.system.exceptions.ProcessResponseReadException;
import com.antonvovk.cyberpowerupsapi.services.system.exceptions.ProcessStartFailureException;
import com.antonvovk.cyberpowerupsapi.services.system.exceptions.ProcessWaitInterruptedException;
import com.antonvovk.cyberpowerupsapi.services.system.models.CommandResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;

@Service
@Slf4j
class SystemServiceImpl implements SystemService {

    @Override
    public CommandResult executeCommand(String... command) {
        try {
            var process = new ProcessBuilder().command(command).start();
            var exitCode = process.waitFor();
            log.info("Executed command {} with exit code {}", command, exitCode);
            return new CommandResult(getResponse(process), exitCode);
        } catch (InterruptedException e) {
            log.error("", e);
            throw new ProcessWaitInterruptedException(e);
        } catch (IOException e) {
            log.error("", e);
            throw new ProcessStartFailureException(e);
        }
    }

    private String getResponse(Process process) {
        try (var inputStream = new BufferedInputStream(process.getInputStream())) {
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            log.error("", e);
            throw new ProcessResponseReadException(e);
        }
    }
}
