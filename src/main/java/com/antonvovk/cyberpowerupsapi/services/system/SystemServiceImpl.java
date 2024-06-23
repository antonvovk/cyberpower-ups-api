package com.antonvovk.cyberpowerupsapi.services.system;

import com.antonvovk.cyberpowerupsapi.services.system.exceptions.ProcessExecutionErrorException;
import com.antonvovk.cyberpowerupsapi.services.system.exceptions.ProcessInputStreamReadException;
import com.antonvovk.cyberpowerupsapi.services.system.exceptions.ProcessStartFailedException;
import com.antonvovk.cyberpowerupsapi.services.system.exceptions.ProcessWaitInterruptedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
class SystemServiceImpl implements SystemService {

    @Override
    public String executeCommand(String... command) {
        try {
            var process = new ProcessBuilder().command(command).start();
            var exitCode = process.waitFor();
            if (exitCode != 0) {
                var error = readError(process);
                log.error("Executed command {} with exit code {}. Error: {}", command, exitCode, error);
                throw new ProcessExecutionErrorException(error);
            }

            var response = readResponse(process);
            log.info("Executed command {} with exit code {}. Response: {}", command, exitCode, response);
            return response;
        } catch (InterruptedException e) {
            log.error("", e);
            throw new ProcessWaitInterruptedException(e);
        } catch (IOException e) {
            log.error("", e);
            throw new ProcessStartFailedException(e);
        }
    }

    private String readResponse(Process process) {
        return readInputStream(process.getInputStream());
    }

    private String readError(Process process) {
        return readInputStream(process.getErrorStream());
    }

    private String readInputStream(InputStream input) {
        try (var stream = new BufferedInputStream(input)) {
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            log.error("", e);
            throw new ProcessInputStreamReadException(e);
        }
    }
}
