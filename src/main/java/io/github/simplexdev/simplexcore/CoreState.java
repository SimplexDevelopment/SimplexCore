package io.github.simplexdev.simplexcore;

public class CoreState {
    private final SimplexCorePlugin plugin;
    private String message;

    public CoreState(SimplexCorePlugin plugin) {
        this.plugin = plugin;
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

    public State getState() {
        if (SimplexCorePlugin.isDebug()) {
            return State.DEBUG;
        }

        if (this.plugin.isEnabled()) {
            return State.ON;
        }

        if (SimplexCorePlugin.isSuspended()) {
            return State.SUSPENDED;
        }

        return State.VOLATILE;
    }

    public String getMessage() {
        return message;
    }

    enum State {
        ON,
        DEBUG,
        SUSPENDED,
        VOLATILE
    }
}