package srdqrk.withering.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import srdqrk.withering.Core;
import srdqrk.withering.events.GameTickEvent;

public class GlobalListener implements Listener {

    Core instance;

    public GlobalListener(Core instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onGameTick(GameTickEvent e) {
        Bukkit.getScheduler().runTask(instance, () -> {

        });
    }

    /**
     * Method to prevent that creepers gives infinite potion effects
     *
     * @param e
     */
    @EventHandler
    public void onEntityExplotes(EntityExplodeEvent e) {
        if ((e.getEntity() instanceof Creeper)) {
            final Creeper creeper = (Creeper) e.getEntity();
            for (PotionEffect effect : creeper.getActivePotionEffects()
            ) {
                creeper.removePotionEffect(effect.getType());
            }
        }
    }

    @EventHandler
    public void onPlayersJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.sendMessage(ChatColor.AQUA + "Bienvenido a Withering Souls Temporada 2 \n\t Dia: " +
                instance.getGame().getDay());
        p.playSound(p.getLocation(), Sound.ENTITY_HORSE_DEATH, 1, 0.3F);
    }

    @EventHandler
    public void onMonsterDies(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        if (dead instanceof Drowned) {
            if (instance.getGame().getDay() >= 5) {
                e.getDrops().clear();
                int random = (int) ((Math.random() * 10) + 1);
                if (random == 1) {
                    dead.getWorld().dropItem(dead.getLocation(), new ItemStack(Material.TRIDENT));
                }
            }
        }// End Drowned
    }

    @EventHandler
    public void onMobShots(ProjectileLaunchEvent e) {
        if (e.getEntity() == null)
            return;
        if (e.getEntity().getShooter() instanceof Ghast) {
            Ghast ghast = (Ghast) e.getEntity().getShooter();
            if (ghast.getCustomName() == null) {
                if (instance.getGame().getDay() >= 10) {
                    ((LargeFireball) e.getEntity()).setYield(8);
                }
            }

        }

    }

}
