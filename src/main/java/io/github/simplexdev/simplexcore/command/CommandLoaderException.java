package io.github.simplexdev.simplexcore.command;

import org.apache.commons.lang.exception.ExceptionUtils;

public class CommandLoaderException extends RuntimeException {
    public CommandLoaderException() {
        super("The Command Loader has encountered an exception and has failed to execute properly. " +
                "Some commands may not be loaded.");
        ExceptionUtils.getMessage(super.getCause());
    }

    public CommandLoaderException(String msg) {
        super(msg);
    }

    public CommandLoaderException(Throwable th) {
        super(th);
    }
}
