package io.github.simplexdev.simplexcore.ban;

import io.github.simplexdev.api.IBan;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.chat.Messages;
import io.github.simplexdev.simplexcore.config.Yaml;
import io.github.simplexdev.simplexcore.config.YamlFactory;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.TickedTime;
import io.github.simplexdev.simplexcore.utils.Utilities;
import net.kyori.adventure.text.Component;
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
 * Use this in synchrony with {@link SimplexListener} to process bans on player login/join.
 * Use this in synchrony with {@link YamlFactory} to create a new yaml file to store your bans, or to create an individual yaml file per user ban.
 */
public abstract class Ban implements IBan {
    private final Player player;
    private final CommandSender sender;
    private final BanType type;
    private final Date banDate;
    private final long banDuration;
    private final SimplexModule<?> plugin;

    private final String banId;
    private final Component banReason;

    /**
     * Creates a new Ban Entry.
     * @param plugin Your plugin instance
     * @param player The player to be banned
     * @param sender The command sender.
     */
    public Ban(SimplexModule<?> plugin, Player player, CommandSender sender) {
        this(plugin, player, sender, BanType.TEMPORARY);
    }

    /**
     * Creates a new Ban Entry.
     * @param plugin Your plugin instance.
     * @param player The player to be banned.
     * @param sender The command sender.
     * @param type The type of ban. See {@link BanType}.
     */
    public Ban(SimplexModule<?> plugin, Player player, CommandSender sender, BanType type) {
        this(plugin, player, sender, type, TickedTime.DAY);
    }

    /**
     * Creates a new Ban Entry.
     * @param plugin Your plugin instance.
     * @param player The player to be banned.
     * @param sender The command sender.
     * @param type The type of ban. See {@link BanType}.
     * @param banDuration How long the ban should last.
     */
    public Ban(SimplexModule<?> plugin, Player player, CommandSender sender, BanType type, long banDuration) {
        this(plugin, player, sender, type, Utilities.generateBanId(type), Messages.BAN.getMessage(), new Date(), banDuration);
    }

    /**
     * Creates a new Ban Entry.
     * @param plugin Your plugin instance.
     * @param player The player to be banned.
     * @param sender The command sender.
     * @param type The type of ban. See {@link BanType}.
     * @param banId A custom Ban ID.
     * @param banReason The reason why the user was banned.
     * @param banDate The date when the ban was created.
     * @param banDuration How long the ban should last.
     */
    public Ban(SimplexModule<?> plugin, Player player, CommandSender sender, BanType type, String banId, Component banReason, Date banDate, long banDuration) {
        this.plugin = plugin;
        this.player = player;
        this.sender = sender;
        this.type = type;
        this.banId = banId;
        this.banReason = banReason;
        this.banDuration = banDuration;
        this.banDate = banDate;
    }

    /**
     * Writes the Ban to a file.
     * @param separateFiles Whether to create individual files for players or store
     *                     them all in one bans.yml file.
     */
    public void writeToFile(boolean separateFiles) {
        File fileLocation = new File(plugin.getParentFolder(), "bans");

        Yaml yaml;
        if (separateFiles) {
            yaml = new YamlFactory(plugin).from(null, fileLocation, player.getName() + ".yml");
            ConfigurationSection section = yaml.getConfig().createSection(getOffender().toString());
            section.set("name", player.getName());
            section.set("ban_id", banId);
            section.set("sender", sender.getName());
            section.set("reason", banReason.toString());
            section.set("duration", banDuration);
            section.set("date", banDate.getTime());
            section.set("type", type.toString());
            try {
                yaml.save();
            } catch (IOException e) {
                plugin.getLogger().severe(e.getMessage());
            }
        } else {
            yaml = new YamlFactory(plugin).from(null, fileLocation, "bans.yml");
            ConfigurationSection section = yaml.getConfig().createSection(getOffender().toString());
            section.set("name", player.getName());
            section.set("ban_id", banId);
            section.set("sender", sender.getName());
            section.set("reason", banReason.toString());
            section.set("duration", banDuration);
            section.set("date", banDate.getTime());
            section.set("type", type.toString());
            try {
                yaml.save();
            } catch (IOException ex) {
                plugin.getLogger().severe(ex.getMessage());
            }
        }
        yaml.reload();
    }
}
