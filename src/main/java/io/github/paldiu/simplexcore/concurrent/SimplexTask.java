package io.github.paldiu.simplexcore.concurrent;

import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Constants;
import org.bukkit.scheduler.BukkitTask;

import java.util.Date;
import java.util.function.Consumer;

public abstract class SimplexTask implements Consumer<BukkitTask> {
    protected final long DELAY;
    protected final long INTERVAL;
    protected Date lastRan = new Date();

    protected SimplexTask(long initialDelay, long interval) {
        DELAY = initialDelay;
        INTERVAL = interval;
    }

    protected SimplexTask(long interval) {
        DELAY = 0L;
        INTERVAL = interval;
    }

    protected SimplexTask() {
        DELAY = Constants.getTimeValues().SECOND() * 30; // 30 seconds until the task triggers for the first time.
        INTERVAL = Constants.getTimeValues().MINUTE() * 5; // Task will run at 5 minute intervals once the first trigger has been called.
    }

    public <T extends SimplexTask> void register(T task, SimplexAddon<?> plugin, boolean repeating, boolean delayed) {
        if (delayed && repeating) {
            Constants.getScheduler().runTaskTimerAsynchronously(plugin, task, DELAY, INTERVAL);
        } else if (delayed) {
            Constants.getScheduler().runTaskLaterAsynchronously(plugin, task, DELAY);
        } else if (repeating) {
            Constants.getScheduler().runTaskTimerAsynchronously(plugin, task, 0L, INTERVAL);
        } else {
            Constants.getScheduler().runTaskAsynchronously(plugin, task);
        }
    }

    public <T extends SimplexTask> void register(T task, SimplexAddon<?> plugin, boolean delayed) {
        register(task, plugin, false, delayed);
    }

    public <T extends SimplexTask> void register(T task, SimplexAddon<?> plugin) {
        register(task, plugin, false, false);
    }

    public Date getLastRan() {
        return lastRan;
    }

    public void setLastRan(Date lastRan) {
        this.lastRan = lastRan;
    }
}
