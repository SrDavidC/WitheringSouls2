package srdqrk.withering.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Conditions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import srdqrk.withering.Core;

import static srdqrk.withering.utilities.Items.getExplosiveBow;
import static srdqrk.withering.utilities.WitheringUtils.noPerms;
import static srdqrk.withering.utilities.WitheringUtils.prefix;


public class SetDays extends BaseCommand {
    Core instance;
    public SetDays(Core instance) {
        this.instance = instance;
    }

        @CommandAlias("DaysSet")
        public void onSend(Player sender, @Conditions("min=1 , max=31")Integer number){
            if (!sender.hasPermission("abysm.daysset")){
                Bukkit.broadcastMessage(prefix + noPerms);
                sender.playSound(sender.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM,1.0F,0);
                return;
            }

            instance.getGame().setDay(number);
            sender.sendMessage(ChatColor.AQUA + "Los dias se han cambiado a: "
                    + ChatColor.DARK_AQUA + instance.getGame().getDay());
            for (World w :  Bukkit.getServer().getWorlds()){
                System.out.println(w.toString());
            }
        }

    @CommandAlias("getBowExplosive")
    public void getExBow(Player sender){
        if (!sender.hasPermission("abysm.daysset")){
            Bukkit.broadcastMessage(prefix + noPerms);
            sender.playSound(sender.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM,1.0F,0);
            return;
        }

        sender.getInventory().setItemInMainHand(getExplosiveBow());
    }
    @CommandAlias("getBowSoul")
    public void getSoulBow(Player sender){
        if (!sender.hasPermission("abysm.daysset")){
            Bukkit.broadcastMessage(prefix + noPerms);
            sender.playSound(sender.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM,1.0F,0);
            return;
        }

        sender.getInventory().setItemInMainHand(getExplosiveBow());
    }




    }


