package io.github.paldiu.simplexcore.chat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TextComponentFactory {
    @NotNull
    public TextComponent textComponent(@NotNull String message) {
        TextComponent component = new TextComponent();
        component.setText(message);
        return component;
    }

    @NotNull
    public TextComponent clickableComponent(@NotNull String message, @NotNull String clickAction, @NotNull ClickEvent.Action actionType) {
        TextComponent comp = new TextComponent();
        ClickEvent onClick = new ClickEvent(actionType, clickAction);
        comp.setText(message);
        comp.setClickEvent(onClick);
        return comp;
    }

    @NotNull
    public TextComponent coloredComponent(@NotNull String message, @Nullable ChatColor color) {
        TextComponent component = new TextComponent();
        if (color != null) component.setColor(color);
        component.setText(message);
        return component;
    }

    @NotNull
    public TextComponent clickableColored(@NotNull String message, @NotNull String clickMessage, @NotNull ClickEvent.Action actionType, @Nullable ChatColor color) {
        TextComponent comp = new TextComponent();
        ClickEvent onClick = new ClickEvent(actionType, clickMessage);
        if (color != null) comp.setColor(color);
        comp.setText(message);
        comp.setClickEvent(onClick);
        return comp;
    }

    @NotNull
    public TextComponent addColor(@NotNull TextComponent component, @NotNull ChatColor color) {
        component.setColor(color);
        return component;
    }

    @NotNull
    public TextComponent resetColor(@NotNull TextComponent component) {
        component.setColor(ChatColor.RESET);
        return component;
    }

    @NotNull
    public TextComponent hoverableText(@NotNull String message, @NotNull String hoverMessage, @NotNull HoverEvent.Action action) {
        TextComponent comp = new TextComponent();
        comp.setText(message);
        Text text = new Text(hoverMessage);
        HoverEvent event = new HoverEvent(action, text);
        comp.setHoverEvent(event);
        return comp;
    }

    @NotNull
    public String colorize(@NotNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
