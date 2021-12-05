package io.github.simplexdev.simplexcore.chat;

import net.kyori.adventure.text.Component;

public enum Messages {
    NO_PERMS(Component.text("You do not have permission to use this command!")),
    DISCORD(Component.text("https://discord.gg/Rumx5dTJuf")),
    BAN(Component.text("You have been banned from this server.")),
    KICK(Component.text("You have been kicked by a moderator.")),
    AFK_KICK(Component.text("You were kicked to ensure space for active players.")),
    PERMBAN(Component.text("You are permanently banned from this server."));


    final Component message;

    Messages(Component message) {
        this.message = message;
    }

    public Component getMessage() {
        return message;
    }
}
