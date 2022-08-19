package srdqrk.withering.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import srdqrk.withering.Core;
import srdqrk.withering.listeners.dificultades.Day3;
import srdqrk.withering.listeners.dificultades.Day5;
import srdqrk.withering.listeners.dificultades.Day7;

public class ListenerManager {
    private Core instance;

    public ListenerManager(Core instance) {
        this.instance = instance;
        registerListener(new GlobalListener(instance));
        registerListener(new Day3(instance,3));
        registerListener(new Day5(instance,5));
        registerListener(new Day7(instance,7));
        registerListener(new ItemsSkills(instance));


    }

    public void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, instance);
    }

}
