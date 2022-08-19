package srdqrk.withering.commands;
import co.aikar.commands.PaperCommandManager;
import srdqrk.withering.Core;

public class CommandManager {
    PaperCommandManager commandManager;
    public CommandManager(Core instance){
        this.commandManager = new PaperCommandManager(instance);
        commandManager.registerCommand(new GlobalCMD(instance));
        commandManager.registerCommand(new SetDays(instance));
    }

}
