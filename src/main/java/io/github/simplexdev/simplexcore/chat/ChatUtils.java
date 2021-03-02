package io.github.simplexdev.simplexcore.chat;

import io.github.simplexdev.simplexcore.utils.Wrapper;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public final class ChatUtils {
    protected final Wrapper<? extends CommandSender> target;
    protected final TextComponentFactory factory = new TextComponentFactory();

    private ChatUtils(Wrapper<? extends CommandSender> target) {
        this.target = target;
    }

    public static <T extends CommandSender> ChatUtils target(T target) {
        return new ChatUtils(Wrapper.of(target));
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
