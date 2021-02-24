package io.github.paldiu.simplexcore.ban;

import io.github.paldiu.simplexcore.chat.Messages;
import io.github.paldiu.simplexcore.config.Yaml;
import io.github.paldiu.simplexcore.config.YamlFactory;
import io.github.paldiu.simplexcore.utils.Constants;
import io.github.paldiu.simplexcore.utils.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * This class provides a way for you to handle your own banning.
 * Simply extend this class and create a new instance of the subclass.
 * Alternatively, you may use {@link BanFactory#create} to create a new Ban instance.
 * Use this in synchrony with {@link io.github.paldiu.simplexcore.listener.SimplexListener} to process bans on player login/join.
 * Use this in synchrony with {@link io.github.paldiu.simplexcore.config.YamlFactory} to create a new yaml file to store your bans, or to create an individual yaml file per user ban.
 */
public abstract class Ban implements IBan {
    private final Player player;
    private final CommandSender sender;
    private final BanType type;
    private final Date banDate;
    private final long banDuration;

    private final String banId;
    private final String banReason;

    public Ban(Player player, CommandSender sender) {
        this(player, sender, BanType.TEMPORARY);
    }

    public Ban(Player player, CommandSender sender, BanType type) {
        this(player, sender, type, Constants.getTimeValues().DAY());
    }

    public Ban(Player player, CommandSender sender, BanType type, long banDuration) {
        this(player, sender, type, Utilities.generateBanId(type), Messages.BAN.getMessage(), new Date(), banDuration);
    }

    public Ban(Player player, CommandSender sender, BanType type, String banId, String banReason, Date banDate, long banDuration) {
        this.player = player;
        this.sender = sender;
        this.type = type;
        this.banId = banId;
        this.banReason = banReason;
        this.banDuration = banDuration;
        this.banDate = banDate;
    }

    public void writeToFile(boolean separateFiles) {
        File fileLocation = new File(Constants.getPlugin().getDataFolder(), "bans");

        if (separateFiles) {
            Yaml yaml = new YamlFactory(Constants.getPlugin()).from(null, fileLocation, player.getName() + ".yml");
            yaml.getConfig().createSection(getOffender().toString());
            ConfigurationSection section = yaml.getConfigurationSection(getOffender()::toString);
            section.set("name", player.getName());
            section.set("ban_id", banId);
            section.set("sender", sender.getName());
            section.set("reason", banReason);
            section.set("duration", banDuration);
            section.set("date", banDate.getTime());
            section.set("type", type.toString());
            try {
                yaml.save();
            } catch (IOException e) {
                Constants.getLogger().severe(e.getMessage());
            }
            yaml.reload();
        } else {
            // TODO: Write to a single file as separate sections per UUID.
            Yaml yaml = new YamlFactory(Constants.getPlugin()).from(null, fileLocation, "bans.yml");
        }
    }
}
