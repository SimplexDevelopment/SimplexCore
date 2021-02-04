package io.github.paldiu.simplexcore;

import io.github.paldiu.simplexcore.utils.Constants;
import org.bukkit.Bukkit;

public class CoreState {
    enum State {
        ON,
        DEBUG,
        SUSPENDED,
        VOLATILE
    }

    public State getState() {
        if (SimplexCore.isDebug()) {
            return State.DEBUG;
        }

        if (Constants.getPlugin().isEnabled()) {
            return State.ON;
        }

        if (SimplexCore.isSuspended()) {
            return State.SUSPENDED;
        }

        return State.VOLATILE;
    }

    String message;

    public CoreState() {
        switch (getState()) {
            case ON:
                message = "The Core is currently ON";
                break;
            case SUSPENDED:
                message = "The Core is currently SUSPENDED. Please report this to the developer.";
                break;
            case DEBUG:
                message = "The Core is currently in DEBUG mode. Do not use this if you don't know what you're doing.";
                break;
            case VOLATILE:
                message = "The Core state is currently unknown! Please report this to the developer!";
                break;
        }
    }

    public String getMessage() {
        return message;
    }
}