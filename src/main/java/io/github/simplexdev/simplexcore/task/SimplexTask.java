package io.github.simplexdev.simplexcore.task;

import io.github.simplexdev.api.func.VoidSupplier;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
import org.bukkit.scheduler.BukkitTask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

// TODO: Rewrite this entire class and the task system to have more control over tasks.
public abstract class SimplexTask implements Consumer<BukkitTask> {
    protected final long DELAY;
    protected final long INTERVAL;
    protected Date lastRan = new Date();
    protected Timer timer = new Timer();

    protected SimplexTask(long initialDelay, long interval) {
        DELAY = initialDelay;
        INTERVAL = interval;
    }
    
    protected SimplexTask(long interval) {
        DELAY = 0L;
        INTERVAL = interval;
    }

    protected SimplexTask() {
        DELAY = SimplexCorePlugin.getInstance().getTimeValues().SECOND() * 30; // 30 seconds until the task triggers for the first time.
        INTERVAL = SimplexCorePlugin.getInstance().getTimeValues().MINUTE() * 5; // Task will run at 5 minute intervals once the first trigger has been called.
    }

    public <T extends SimplexTask> void register(T task, SimplexAddon<?> plugin, boolean repeating, boolean delayed) {
        if (delayed && repeating) {
            SimplexCorePlugin.getInstance().getScheduler().runTaskTimer(plugin, task, DELAY, INTERVAL);
        } else if (delayed) {
            SimplexCorePlugin.getInstance().getScheduler().runTaskLater(plugin, task, DELAY);
        } else if (repeating) {
            SimplexCorePlugin.getInstance().getScheduler().runTaskTimer(plugin, task, 0L, INTERVAL);
        } else {
            SimplexCorePlugin.getInstance().getScheduler().runTask(plugin, task);
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

    protected Timer getTimer() {
        return timer;
    }

    protected TimerTask newTimer(VoidSupplier supplier) {
        return new TimerTask() {
            @Override
            public void run() {
                supplier.get();
            }
        };
    }
}
