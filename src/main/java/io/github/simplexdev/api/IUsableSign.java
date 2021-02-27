package io.github.simplexdev.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;

import java.util.List;

public interface IUsableSign {
    Sign getSign();

    Location getSignLocation();

    World getWorld();

    List<String> getLines();

    boolean canInteract();

    void execute();

    String signTag();
}
