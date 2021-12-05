package io.github.simplexdev.simplexcore.ban;

import io.github.simplexdev.simplexcore.chat.Messages;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public final class BanManager extends SimplexListener {
    private final Map<Ban, BanType> banMap = new HashMap<>();

    BanManager(SimplexModule<?> plugin) {
        super(plugin);
    }

    public void addBan(Ban ban) {
        banMap.put(ban, ban.getBanType());
    }

    @Nullable
    public Ban getBan(OfflinePlayer player) {
        if (banMap.isEmpty()) {
            return null;
        }

        AtomicReference<Ban> temp = new AtomicReference<>();

        banMap.keySet()
                .stream()
                .filter(ban -> ban.getOffender().equals(player.getUniqueId()))
                .forEach(temp::set);

        return temp.get();
    }

    @EventHandler
    public void banHandler(@NotNull AsyncPlayerPreLoginEvent event) {
        UUID player = event.getUniqueId();
        OfflinePlayer op = Bukkit.getOfflinePlayer(player);
        Ban ban = getBan(op);
        if (ban != null) {
            if (ban.getBanType().equals(BanType.PERMANENT)) {
                event.disallow(Result.KICK_BANNED, Messages.PERMBAN.getMessage());
            }

            if (ban.getBanType().equals(BanType.TEMPORARY)
                    || ban.getBanType().equals(BanType.CUSTOM)) {
                if ((ban.getDate().getTime()
                        + ban.getBanDuration()) > (new Date()).getTime()) {
                    event.disallow(Result.KICK_BANNED, Messages.PERMBAN.getMessage());
                }
            }
        }
    }
}
