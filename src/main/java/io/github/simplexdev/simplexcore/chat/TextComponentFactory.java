package io.github.simplexdev.simplexcore.chat;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TextComponentFactory {
    @NotNull
    public TextComponent textComponent(@NotNull String message) {
        return Component.text(message);
    }

    @NotNull
    public Component clickableComponent(@NotNull String message, @NotNull String clickAction, @NotNull ClickEvent.Action actionType) {
        Component comp = Component.text(message);
        ClickEvent onClick = ClickEvent.clickEvent(actionType, clickAction);
        return comp.clickEvent(onClick);
    }

    @NotNull
    public Component addColor(@NotNull Component component, @NotNull TextColor color) {
        return component.color(color);
    }

    @NotNull
    public Component resetColor(@NotNull Component component) {
        return component.color(TextColor.fromHexString("#FFFFFF"));
    }

    @NotNull
    public Component hoverText(@NotNull String message, @NotNull String hoverMessage) {
        Component comp = Component.text(message);
        Component msg = Component.text(hoverMessage);
        HoverEvent<Component> event = HoverEvent.showText(msg);
        return comp.hoverEvent(event);
    }
}
