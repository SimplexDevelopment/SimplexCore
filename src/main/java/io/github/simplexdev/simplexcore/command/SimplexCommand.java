package io.github.simplexdev.simplexcore.command;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.Utilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.JoinConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public abstract class SimplexCommand implements CommandExecutor, TabCompleter {
    private final SimplexModule<?> plugin;

    public SimplexCommand(SimplexModule<?> plugin) {
        this.plugin = plugin;
    }

    public final SimplexModule<?> getPlugin() {
        return plugin;
    }

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    /**
     * Gets an online player from their username
     *
     * @param name The player's username
     * @return An instance of {@link Player} which represents the online player in question.
     */
    @Nullable
    public Player getPlayer(String name) {
        return SimplexCorePlugin.getInstance().getServer().getPlayer(name);
    }

    /**
     * Gets an online player from their {@link UUID}.
     *
     * @param uuid The player's UUID
     * @return An instance of {@link Player} which represents the online player in question.
     */
    @Nullable
    public Player getPlayer(UUID uuid) {
        return SimplexCorePlugin.getInstance().getServer().getPlayer(uuid);
    }

    /**
     * Gets an instance of {@link Player} based off an instance of {@link CommandSender}.
     * This will be null if the condition {CommandSender instanceof Player} is false.
     *
     * @param sender The CommandSender to cast
     * @return An instance of Player relating to CommandSender.
     */
    @Nullable
    public Player getPlayer(CommandSender sender) {
        return isPlayer(sender) ? (Player) sender : null;
    }

    /**
     * Send a message or a group of messages to a {@link Player}.
     * If you want the messages to send on new lines, put \n at the end of each message to send.
     *
     * @param player   The Player to send a message to
     * @param messages The messages to send.
     */
    public void playerMsg(@NotNull Player player, String... messages) {
        StringBuilder builder = new StringBuilder();
        Utilities.forEach(messages, builder::append);
        player.sendMessage(Component.text(builder.toString()));
    }

    /**
     * Send a message or a group of messages to a {@link CommandSender}
     * If you want the messages to send on separate lines,
     * put \n at the end of each message to send.
     *
     * @param sender   The CommandSender to send a message to.
     * @param messages The messages to send.
     */
    public void msg(@NotNull CommandSender sender, String... messages) {
        StringBuilder builder = new StringBuilder();
        Utilities.forEach(messages, builder::append);
        sender.sendMessage(Component.text(builder.toString()));
    }

    /**
     * Send a component or a group of components to a {@link CommandSender}.
     * If you want the components to occupy separate lines,
     * use the separator boolean.
     *
     * @param sender     The CommandSender to send components to
     * @param components The components to send.
     */
    public void msg(@NotNull CommandSender sender, boolean separator, Component... components) {
        if (separator) {
            AtomicReference<Component> primary = new AtomicReference<>(Component.text(""));
            AtomicReference<Component> temp = new AtomicReference<>(Component.text(""));
            Utilities.forEach(components, component -> {
                temp.set(Component.newline().append(component));
                primary.set(primary.get().append(temp.get()));
            }); // The way I'm doing this is probably unnecessary, but I did it anyway.
            sender.sendMessage(primary.get());
            return;
        }

        sender.sendMessage(Component.join(JoinConfiguration.builder().build(), components));
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
