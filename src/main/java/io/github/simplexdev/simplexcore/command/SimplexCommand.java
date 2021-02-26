package io.github.simplexdev.simplexcore.command;

import io.github.simplexdev.simplexcore.utils.Constants;
import io.github.simplexdev.simplexcore.utils.Utilities;
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

public abstract class SimplexCommand implements CommandExecutor, TabCompleter {

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    @Nullable
    public Player getPlayer(String name) {
        return Constants.getServer().getPlayer(name);
    }

    @Nullable
    public Player getPlayer(UUID uuid) {
        return Constants.getServer().getPlayer(uuid);
    }

    @Nullable
    public Player getPlayer(CommandSender sender) {
        return isPlayer(sender) ? (Player) sender : null;
    }

    public void playerMsg(Player player, String... messages) {
        StringBuilder builder = new StringBuilder();
        Utilities.forEach(messages, builder::append);
        player.sendMessage(builder.toString());
    }

    public void msg(CommandSender sender, String... messages) {
        StringBuilder builder = new StringBuilder();
        Utilities.forEach(messages, builder::append);
        sender.sendMessage(builder.toString());
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
