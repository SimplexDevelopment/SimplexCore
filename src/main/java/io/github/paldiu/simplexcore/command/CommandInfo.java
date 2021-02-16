package io.github.paldiu.simplexcore.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();

    String description();

    String usage();

    String aliases() default "";

    String permission() default "simplex.core";

    String permissionMessage() default "You do not have permission to use this command.";
}
