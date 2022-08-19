package srdqrk.withering.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import org.bukkit.entity.Player;
import srdqrk.withering.Core;

import static srdqrk.withering.utilities.WitheringUtils.checkPermission;

@CommandAlias("items")
public class Items extends BaseCommand {
    private @NonNull Core instance;

    public Items(Core instance) {
            this.instance = instance;

        }


        @Subcommand("PillagerSkeletonCrossBow")
        public void getPillagerSkeletonCrossBow(Player sender){
            if(checkPermission(sender)){

            };



        }



}
