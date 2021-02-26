package io.github.simplexdev.simplexcore.command.defaults;

import io.github.simplexdev.api.annotations.CommandInfo;
import io.github.simplexdev.simplexcore.command.SimplexCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@CommandInfo(name = "Info", description = "Gets info on this API / Library.", usage = "/<command>", permission = "simplex.core.info")
public class Command_info extends SimplexCommand {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("This is an API!");
        return true;
    }
}
