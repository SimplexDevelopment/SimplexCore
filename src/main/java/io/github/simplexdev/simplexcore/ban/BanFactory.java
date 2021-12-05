package io.github.simplexdev.simplexcore.ban;

import io.github.simplexdev.api.IBan;
import io.github.simplexdev.api.func.VoidSupplier;
import io.github.simplexdev.simplexcore.chat.Messages;
import io.github.simplexdev.simplexcore.config.Yaml;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.TickedTime;
import io.github.simplexdev.simplexcore.utils.Utilities;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import static io.github.simplexdev.simplexcore.utils.Utilities.pathway;

public final class BanFactory {
    private final SimplexModule<?> plugin;
    private final Player player;
    private final CommandSender sender;
    private final Date banDate;
    private final BanType type;
    private final String banId;
    private Component banReason;
    private long banDuration;

    public BanFactory(SimplexModule<?> plugin, Player player, CommandSender sender, Date banDate, BanType type) {
        this.plugin = plugin;
        this.player = player;
        this.sender = sender;
        this.banDate = banDate;
        this.type = type;

        this.banReason = Messages.BAN.getMessage();

        banId = createBanId();
    }

    /**
     * The values here are optional to define. They are defined by default, and this does not need to be used.
     *
     * @param banDuration The duration of the ban. Use Constants.getTimeValues() for respective amounts of time.
     * @param banReason   The reason for the ban. By default, this uses Messages#BAN for the message.
     * @return The current instance of BanFactory.
     */
    public BanFactory defineOptional(long banDuration, Component banReason) {
        this.banDuration = banDuration;
        this.banReason = banReason;
        return this;
    }

    public void write(@NotNull Yaml yaml, @NotNull IBan ban) {
        yaml.set(pathway("offender"), ban.getOffender());
        yaml.set(pathway("sender"), ban.getSender());
        yaml.set(pathway("duration"), ban.getBanDuration());
        yaml.set(pathway("date"), ban.getDate());
        yaml.set(pathway("type"), ban.getBanType());
        yaml.set(pathway("id"), ban.getBanId());
        yaml.set(pathway("reason"), ban.getBanReason());
        yaml.create();
    }

    @Contract(pure = true)
    public @Nullable Yaml read(File yamlFile) {
        return null;
    }

    /**
     * Creates a new Ban Entry.
     *
     * @return A new ban instance.
     */
    @Contract(" -> new")
    public @NotNull Ban create() {
        return new Ban(plugin, player, sender, type, banDuration) {
            @Override
            public UUID getOffender() {
                return player.getUniqueId();
            }

            @Override
            public String getSender() {
                return sender.getName();
            }

            @Override
            public Component getBanReason() {
                return banReason;
            }

            @Override
            public String getBanId() {
                return banId;
            }

            @Override
            public Date getDate() {
                return banDate;
            }

            @Override
            public long getBanDuration() {
                return banDuration;
            }

            @Override
            public BanType getBanType() {
                return type;
            }
        };
    }

    public void deleteBan(IBan ban) {
        // TODO
    }

    @Contract(pure = true)
    public @Nullable IBan getBan(String banId) {
        return null;
    }

    @Contract(pure = true)
    public @Nullable IBan getBan(Player player) {
        return null;
    }

    @Contract(pure = true)
    public @Nullable IBan getBan(UUID offenderUUID) {
        return null;
    }

    @Contract(pure = true)
    private @NotNull VoidSupplier assignBanDuration(Long... time) {
        return () -> {
            if (type.equals(BanType.PERMANENT)) {
                banDuration = TickedTime.YEAR * 99;
            } else if (type.equals(BanType.TEMPORARY)) {
                banDuration = TickedTime.DAY;
            } else if (type.equals(BanType.CUSTOM)) {
                long tmp = 0L;
                for (long t : time) {
                    tmp += t;
                }
                banDuration = tmp;
            } else {
                banDuration = TickedTime.MINUTE * 5;
            }
        };
    }

    private @NotNull String createBanId() {
        return Utilities.generateBanId(type);
    }
}
