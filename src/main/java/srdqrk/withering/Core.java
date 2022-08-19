package srdqrk.withering;

import fr.mrmicky.fastinv.FastInvManager;
import kr.entree.spigradle.annotations.SpigotPlugin;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import srdqrk.withering.commands.CommandManager;
import srdqrk.withering.listeners.ListenerManager;
import srdqrk.withering.utilities.NegativeSpaces;
import srdqrk.withering.utilities.TCT.BukkitTCT;

@SpigotPlugin
public class Core extends JavaPlugin {

    private static @Getter Core instance;
    private @Getter Game game;
    private @Getter CommandManager commandManager;
    private @Getter ListenerManager listenerManager;

    @Override
    public void onEnable() {
        instance = this;
        FastInvManager.register(this);
        BukkitTCT.registerPlugin(this);
        NegativeSpaces.registerCodes();

        game = new Game(this);
        game.runTaskTimerAsynchronously(this, 0L, 20L);

        //LISTENERS
        listenerManager = new ListenerManager(instance);
        //COMMANDS
        commandManager = new CommandManager(instance);


    }

    @Override
    public void onDisable() {

    }

    public boolean isOn(int day){

        //Bukkit.broadcastMessage("Me dan: " + day + "\nY los dias son: " + this.getGame().getDay() +
          //      " \nPor lo tanto " + (day <= this.getGame().getDay()) );
        return day <= this.getGame().getDay();
    }

}