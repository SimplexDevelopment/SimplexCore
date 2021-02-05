package io.github.paldiu.simplexcore.command;

import io.github.paldiu.simplexcore.utils.Constants;
import io.github.paldiu.simplexcore.utils.Utilities;
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
    public boolean checkSender(CommandSender sender) {
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

    public void playerMsg(Player player, String... message) {
        StringBuilder builder = new StringBuilder();
        Utilities.primitiveFE(message, builder::append);
        player.sendMessage(builder.toString());
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
