package io.github.simplexdev.simplexcore.chat;

public enum Messages {
    NO_PERMS("You do not have permission to use this command!"),
    DISCORD("https://discord.gg/Rumx5dTJuf"),
    BAN("You have been banned from this server."),
    KICK("You have been kicked by a moderator."),
    AFK_KICK("You were kicked to ensure space for active players.");

    String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
