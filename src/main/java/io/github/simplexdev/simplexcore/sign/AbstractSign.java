package io.github.simplexdev.simplexcore.sign;

import io.github.simplexdev.api.IUsableSign;
import io.github.simplexdev.api.func.VoidSupplier;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractSign implements IUsableSign {
    protected final Sign sign;
    protected final Location location;
    protected final World world;
    protected final List<String> lines;

    protected String signTag = null;
    protected VoidSupplier executeScript = null;
    protected boolean canInteract = false;

    protected AbstractSign(Sign sign) {
        this.sign = sign;
        this.location = sign.getLocation();
        this.world = sign.getWorld();
        this.lines = Arrays.asList(sign.getLines());
    }

    @Override
    public Sign getSign() {
        return sign;
    }

    @Override
    public Location getSignLocation() {
        return location;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }

    public void setSignTag(String signTag) {
        this.signTag = signTag();
    }

    public void setExecuteScript(VoidSupplier executeScript) {
        this.executeScript = executeScript;
    }

    public void setCanInteract(boolean canInteract) {
        this.canInteract = canInteract;
    }
}
