package io.github.simplexdev.simplexcore.ban;

import io.github.simplexdev.api.IBan;
import io.github.simplexdev.simplexcore.chat.Messages;
import io.github.simplexdev.api.func.VoidSupplier;
import io.github.simplexdev.simplexcore.utils.Constants;
import io.github.simplexdev.simplexcore.utils.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public final class BanFactory {
    private final Player player;
    private final CommandSender sender;
    private final Date banDate;
    private final BanType type;
    private final String banId;
    private String banReason;
    private long banDuration;

    public BanFactory(Player player, CommandSender sender, Date banDate, BanType type) {
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
    public BanFactory defineOptional(long banDuration, String banReason) {
        this.banDuration = banDuration;
        this.banReason = banReason;
        return this;
    }

    /**
     * Creates a new instance of the abstract class Ban.
     *
     * @return A new ban instance.
     */
    public Ban create() {
        return new Ban(player, sender, type, banDuration) {
            @Override
            public UUID getOffender() {
                return player.getUniqueId();
            }

            @Override
            public String getSender() {
                return sender.getName();
            }

            @Override
            public String getBanReason() {
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

    }

    private VoidSupplier assignBanDuration() {
        return () -> {
            if (type.equals(BanType.PERMANENT)) {
                banDuration = Constants.getPlugin().getInstances().getTimeValues().YEAR() * 99;
            } else if (type.equals(BanType.TEMPORARY)) {
                banDuration = Constants.getPlugin().getInstances().getTimeValues().DAY();
            } else {
                banDuration = Constants.getPlugin().getInstances().getTimeValues().MINUTE() * 5;
            }
        };
    }

    private String createBanId() {
        return Utilities.generateBanId(type);
    }
}
