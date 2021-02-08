package io.github.paldiu.simplexcore.future;

import io.github.paldiu.simplexcore.chat.Messages;
import io.github.paldiu.simplexcore.utils.Constants;
import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class Announcer extends SimplexTask {

    private final List<String> stringList = new ArrayList<>() {{
        add("Join our discord!" + Messages.DISCORD.getMessage());
        add("Thank you for using SimplexCore!");
        add("https://github.com/Paldiu/SimplexCore");
    }};

    public Announcer() {
        super(20L);
        register(this, Constants.getPlugin(), true, false);
    }

    @Override
    public void accept(BukkitTask bukkitTask) {
        Bukkit.getServer().broadcastMessage(stringList.get(RandomUtils.nextInt(stringList.size())));
    }
}
