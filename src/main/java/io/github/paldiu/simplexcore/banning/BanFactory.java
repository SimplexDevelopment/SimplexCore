package io.github.paldiu.simplexcore.banning;

import io.github.paldiu.simplexcore.chat.Messages;
import io.github.paldiu.simplexcore.functional.Guard;
import io.github.paldiu.simplexcore.utils.Constants;
import io.github.paldiu.simplexcore.utils.Utilities;
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

    private BanFactory(Player player, CommandSender sender, Date banDate, BanType type) {
        this.player = player;
        this.sender = sender;
        this.banDate = banDate;
        this.type = type;

        this.banReason = Messages.BAN.getMessage();
        assignBanDuration().verify();

        banId = createBanId();
    }

    public static BanFactory define(Player player, CommandSender sender, Date banDate, BanType type) {
        return new BanFactory(player, sender, banDate, type);
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

    private Guard assignBanDuration() {
        return () -> {
            if (type.equals(BanType.PERMANENT)) {
                banDuration = Constants.getTimeValues().YEAR() * 99;
            } else if (type.equals(BanType.TEMPORARY)) {
                banDuration = Constants.getTimeValues().DAY();
            } else {
                banDuration = Constants.getTimeValues().MINUTE() * 5;
            }
        };
    }

    private String createBanId() {
        return Utilities.generateBanId(type);
    }
}
