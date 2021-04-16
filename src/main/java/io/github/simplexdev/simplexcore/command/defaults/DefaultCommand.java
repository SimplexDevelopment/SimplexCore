package io.github.simplexdev.simplexcore.command.defaults;

import io.github.simplexdev.api.annotations.CommandInfo;
import io.github.simplexdev.simplexcore.command.SimplexCommand;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@CommandInfo(name = "default", usage = "/<command>", description = "Default plugin command.")
public final class DefaultCommand extends SimplexCommand {
    public DefaultCommand(SimplexModule<?> plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("If you are seeing this when running your command, your command didn't register properly.");
        return true;
    }
}
