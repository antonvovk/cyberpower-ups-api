package com.antonvovk.cyberpowerupsapi.services.system.exceptions;

public class ProcessWaitInterruptedException extends RuntimeException {

    public ProcessWaitInterruptedException(Throwable cause) {
        super(cause);
    }
}
