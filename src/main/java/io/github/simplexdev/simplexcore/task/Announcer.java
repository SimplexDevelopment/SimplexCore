package io.github.simplexdev.simplexcore.task;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.chat.Messages;
import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class Announcer extends SimplexTask {

    private final List<String> stringList = new ArrayList<>() {{
        add("Join our discord!" + Messages.DISCORD.getMessage());
        add("Thank you for using SimplexCore!");
        add("https://github.com/SimplexDevelopment/SimplexCore");
    }};

    public Announcer() {
        super(20L);
        register(this, SimplexCorePlugin.getInstance(), true, false);
    }

    @Override
    public void accept(BukkitTask bukkitTask) {
        Bukkit.getServer().broadcastMessage(stringList.get(RandomUtils.nextInt(stringList.size())));
    }
}
