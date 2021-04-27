package io.github.simplexdev.simplexcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class PlayerTools {

    @NotNull
    public static String stringedUUID(@NotNull Player player) {
        return player.getUniqueId().toString();
    }

    @Nullable
    public static Player getPlayer(@NotNull String nameOrUUID) {
        if (nameOrUUID.length() > 16) {
            UUID uuid = UUID.fromString(nameOrUUID);
            return Bukkit.getServer().getPlayer(uuid);
        } else {
            return Bukkit.getServer().getPlayer(nameOrUUID);
        }
    }

    @NotNull
    public static OfflinePlayer getOfflinePlayer(@NotNull String nameOrUUID) {
        if (nameOrUUID.length() > 16) {
            UUID uuid = UUID.fromString(nameOrUUID);
            return Bukkit.getOfflinePlayer(uuid);
        }
        return Bukkit.getOfflinePlayer(nameOrUUID);
    }
}
