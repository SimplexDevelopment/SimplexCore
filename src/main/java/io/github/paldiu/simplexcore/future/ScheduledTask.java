package io.github.paldiu.simplexcore.future;

import io.github.paldiu.simplexcore.utils.Constants;
import io.github.paldiu.simplexcore.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.Date;
import java.util.function.Consumer;

public abstract class ScheduledTask implements Consumer<BukkitTask> {
    protected Date lastRan = new Date();

    protected ScheduledTask() {

    }

    public Date getLastRan() {
        return lastRan;
    }

    public void setLastRan(Date lastRan) {
        this.lastRan = lastRan;
    }
}
