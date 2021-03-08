package io.github.simplexdev.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation must be used to annotate every command class.
 * Please also ensure that your commands are in their own package (ie .commands)
 * This helps the {@link io.github.simplexdev.simplexcore.command.CommandLoader} to correctly load your commands.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    /**
     * @return The name of the command.
     */
    String name();

    /**
     * @return The description of the command.
     */
    String description();

    /**
     * @return How the command should be used.
     */
    String usage();

    /**
     * @return A list of aliases of the command separated by commas in a single String.
     */
    String aliases() default "";

    /**
     * @return The permission for the command
     */
    String permission() default "simplex.core";

    /**
     * @return The message to send if someone does not have permission to use the command.
     */
    String permissionMessage() default "You do not have permission to use this command.";
}
