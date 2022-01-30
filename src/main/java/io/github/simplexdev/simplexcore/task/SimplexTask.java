package io.github.simplexdev.simplexcore.task;

import io.github.simplexdev.api.func.VoidSupplier;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.TickedTime;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

// TODO: Rewrite this entire class and the task system to have more control over tasks.
public abstract class SimplexTask implements Consumer<BukkitTask> {
    protected final long DELAY;
    protected final long INTERVAL;
    protected final SimplexModule<?> plugin;
    protected Date lastRan = new Date();
    protected Timer timer = new Timer();
    protected int taskId;

    /**
     * Creates a new instance of this class, with a custom defined DELAY and INTERVAL.
     *
     * @param initialDelay How long before the first time the task executes.
     * @param interval     How long before the task repeats itself.
     */
    protected SimplexTask(SimplexModule<?> plugin, long initialDelay, long interval) {
        DELAY = initialDelay;
        INTERVAL = interval;
        this.plugin = plugin;
    }

    /**
     * Constructor which automatically registers the DELAY as 0 seconds (NO DELAY)
     * and assigns a custom INTERVAL at which it executes.
     *
     * @param interval How often the task should repeat. Every 20L is 1 second.
     *                 You should use {@link TickedTime} for determining the related server timings.
     */
    protected SimplexTask(SimplexModule<?> plugin, long interval) {
        DELAY = 0L;
        INTERVAL = interval;
        this.plugin = plugin;
    }

    /**
     * Constructor which automatically registers the DELAY as 30 seconds
     * and the INTERVAL at which it executes as 5 minutes.
     */
    protected SimplexTask(SimplexModule<?> plugin) {
        this.plugin = plugin;
        DELAY = TickedTime.SECOND * 30; // 30 seconds until the task triggers for the first time.
        INTERVAL = TickedTime.MINUTE * 5; // Task will run at 5 minute intervals once the first trigger has been called.
    }

    /**
     * Registers the parent interface {@link Consumer<BukkitTask>} with the
     * {@link org.bukkit.scheduler.BukkitScheduler}.
     *
     * @param task      The instance of a subclass of this class.
     * @param repeating Whether the task should repeat.
     * @param delayed   Whether to delay the first time the task runs.
     * @param <T>       A subclass of SimplexTask.
     */
    public <T extends SimplexTask> void register(T task, boolean repeating, boolean delayed) {
        if (delayed && repeating) {
            setTaskId(SimplexCorePlugin.getInstance().getScheduler().scheduleSyncRepeatingTask(plugin, (Runnable)task, DELAY, INTERVAL));
        } else if (delayed) {
            setTaskId(SimplexCorePlugin.getInstance().getScheduler().scheduleSyncDelayedTask(plugin, (Runnable)task, DELAY));
        } else if (repeating) {
            setTaskId(SimplexCorePlugin.getInstance().getScheduler().scheduleSyncRepeatingTask(plugin, (Runnable)task, 0L, INTERVAL));
        } else {
            SimplexCorePlugin.getInstance().getScheduler().runTask(plugin, task);
            setTaskId(-1);
        }
    }


    /**
     * Registers the parent interface {@link Consumer<BukkitTask>} with the
     * {@link org.bukkit.scheduler.BukkitScheduler}.
     * This version of this method will not create a repeating task..
     *
     * @param task    The instance of a subclass of this class.
     * @param delayed Whether to delay the start of the task.
     * @param <T>     A subclass of SimplexTask.
     */
    public <T extends SimplexTask> void register(T task, boolean delayed) {
        register(task, false, delayed);
    }

    /**
     * Registers the parent interface {@link Consumer<BukkitTask>} with the
     * {@link org.bukkit.scheduler.BukkitScheduler}.
     * This version of this method will not create a delayed or repeating task.
     *
     * @param task   The instance of a subclass of this class.
     * @param <T>    A subclass of SimplexTask.
     */
    public <T extends SimplexTask> void register(T task) {
        register(task, false, false);
    }

    /**
     * Gets the last time the task ran.
     *
     * @return The last time as a {@link Date} this task last ran.
     */
    public Date getLastRan() {
        return lastRan;
    }

    /**
     * Sets the last time this task ran. This should be done in a subclass inside of the
     * {@link Consumer#accept(Object)} method.
     *
     * @param lastRan An instance of {@link Date} returning the last time this task ran.
     */
    public void setLastRan(Date lastRan) {
        this.lastRan = lastRan;
    }

    /**
     * Gets the {@link Timer} instance associated with this class.
     * This is for creating and running tasks separate from the {@link org.bukkit.scheduler.BukkitScheduler}.
     *
     * @return A {@link Timer} object.
     */
    protected Timer getTimer() {
        return timer;
    }

    /**
     * Creates a new {@link TimerTask} to run with the {@link Timer}
     *
     * @param supplier A task to complete.
     * @return A new instance of TimerTask.
     */
    protected TimerTask newTimer(VoidSupplier supplier) {
        return new TimerTask() {
            @Override
            public void run() {
                supplier.get();
            }
        };
    }

    private void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
