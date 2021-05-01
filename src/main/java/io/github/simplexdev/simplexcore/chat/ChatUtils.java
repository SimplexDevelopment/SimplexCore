package io.github.simplexdev.simplexcore.chat;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public final class ChatUtils {
    protected final CommandSender target;
    protected final TextComponentFactory factory = new TextComponentFactory();

    private <T extends CommandSender> ChatUtils(T target) {
        this.target = target;
    }

    public static <T extends CommandSender> ChatUtils target(T target) {
        return new ChatUtils(target);
    }

    public void msg(String message) {
        target.sendMessage(message);
    }

    public void msg(TextComponent component) {
        target.spigot().sendMessage(component);
    }

    public void err(Messages message) {
        target.sendMessage(message.getMessage());
    }

    public void color(String message) {
        target.sendMessage(factory.colorize(message));
    }

    public void color(TextComponent component) {
        target.sendMessage(factory.colorize(component.getText()));
    }


}
