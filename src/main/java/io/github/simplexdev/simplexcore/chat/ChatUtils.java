package io.github.simplexdev.simplexcore.chat;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class ChatUtils {
    private final CommandSender target;
    private final TextComponentFactory factory = new TextComponentFactory();

    private <T extends CommandSender> ChatUtils(T target) {
        this.target = target;
    }

    @Contract("_ -> new")
    public static <T extends CommandSender> @NotNull ChatUtils msgTarget(T target) {
        return new ChatUtils(target);
    }

    public void msg(String message) {
        target.sendMessage(message);
    }

    public void msg(Component component) {
        target.sendMessage(component);
    }

    public void err(@NotNull Messages message) {
        target.sendMessage(message.getMessage());
    }
}
