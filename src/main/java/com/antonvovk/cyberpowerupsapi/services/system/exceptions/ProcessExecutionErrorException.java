package com.antonvovk.cyberpowerupsapi.services.system.exceptions;

public class ProcessExecutionErrorException extends RuntimeException {

    public ProcessExecutionErrorException(String message) {
        super(message);
    }
}
