package io.github.paldiu.simplexcore.chat;

import io.github.paldiu.simplexcore.utils.Bean;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public final class ChatUtils {
    protected final Bean<? extends CommandSender> target;
    protected final TextComponentFactory factory = new TextComponentFactory();

    private ChatUtils(Bean<? extends CommandSender> target) {
        this.target = target;
    }

    public static <T extends CommandSender> ChatUtils target(T target) {
        return new ChatUtils(new Bean<>(target));
    }

    public void msg(String message) {
        target.get().sendMessage(message);
    }

    public void msg(TextComponent component) {
        target.get().sendMessage(component);
    }

    public void err(Messages message) {
        target.get().sendMessage(message.getMessage());
    }

    public void color(String message) {
        target.get().sendMessage(factory.colorize(message));
    }

    public void color(TextComponent component) {
        target.get().sendMessage(factory.colorize(component.getText()));
    }
}
