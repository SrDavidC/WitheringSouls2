package srdqrk.withering;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import srdqrk.withering.events.GameTickEvent;

import java.util.Random;

@Data
@EqualsAndHashCode(callSuper = false)
public class Game extends BukkitRunnable {
    Core instance;
    Random rand;
    long gameTime = 0;
    long startTime = 0;
    @Getter
    private int day = 0;

    public Game(Core instance) {
        this.instance = instance;
        this.startTime = System.currentTimeMillis();

    }

    @Override
    public void run() {

        var new_time = (int) (Math.floor((System.currentTimeMillis() - startTime) / 1000.0));

        gameTime = new_time;

        Bukkit.getPluginManager().callEvent(new GameTickEvent(new_time, true));
    }
}