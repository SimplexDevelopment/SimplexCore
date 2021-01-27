package io.github.paldiu.simplexcore.command;

import org.bukkit.ChatColor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String name();

    String description();

    String usage();

    String aliases() default "";

    String permission() default "simplex.core";

    String permissionMessage() default "You do not have permission to use this command.";
}
