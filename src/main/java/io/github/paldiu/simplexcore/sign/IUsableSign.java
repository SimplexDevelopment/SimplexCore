package io.github.paldiu.simplexcore.sign;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public interface IUsableSign {
    Block getBlock();

    Location getSignLocation();

    World getWorld();

    String getSignText();

    void executeOnInteract();
}
