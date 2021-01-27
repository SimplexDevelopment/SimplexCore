package io.github.paldiu.simplexcore.command.defaults;

import io.github.paldiu.simplexcore.command.CommandBase;
import io.github.paldiu.simplexcore.command.CommandInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@CommandInfo(name = "defaultcommand", usage = "/<command>", description = "Default plugin command.")
public class DefaultCommand extends CommandBase {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("If you are seeing this when running your command, your command didn't register properly.");
        return true;
    }
}
