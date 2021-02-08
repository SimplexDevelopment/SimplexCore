package io.github.paldiu.simplexcore.chat;

public enum Messages {
    NO_PERMS("You do not have permission to use this command!"),
    DISCORD("https://discord.gg/"),
    BAN("You have been permanently banned from this server."),
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
