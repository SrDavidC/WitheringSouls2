package srdqrk.withering.listeners.dificultades;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import srdqrk.withering.Core;

public class Day5 extends DifficultChange {
    public Day5(Core instance, int dayAble){
        super(instance,dayAble);
    }

    @EventHandler
    public void onMonsterSpawns(CreatureSpawnEvent e){
        if (instance.isOn(dayAble)){
            Entity spawned = e.getEntity();
            if (spawned instanceof Drowned mob){ // Drowneds now have more health and trident
                mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
                mob.setHealth(40D);
                mob.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT));
            }
            else if(spawned instanceof Zombie mob){ // Zombies more health
                mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
                mob.setHealth(40D);
            }else if (spawned instanceof Skeleton mob && instance.getGame().getDay() < 7) { // Skeleton upgrades potion and health
                mob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 6010 * 20, 1));
                mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6010 * 20, 1));
                mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6010 * 20, 1));
                mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
                mob.setHealth(40D);
            }else if (spawned instanceof Creeper mob && dayAble < 10){ // More explosion radius
                mob.setExplosionRadius(4);
            }else if( (spawned instanceof Evoker || spawned instanceof Ravager) && instance.getGame().getDay() < 7){ // Raiders with double of their health
               double health = (( (Raider) spawned).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * 2;
                ((Raider) spawned).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((health));
                ((Raider) spawned).setHealth(health);
            }
        }// ABLE
    }// end method

    @EventHandler
    public void onMonsterDies(EntityDeathEvent e){
        Entity dead = e.getEntity();
        if (instance.isOn(dayAble)){

            if (dead instanceof Evoker){
                e.getDrops().clear();
            }else if (dead instanceof Ravager){
                if(e.getEntity().getKiller() != null) {
                    int random = (int) ((Math.random() * 10) + 1);
                    if (random <= 4) {
                        dead.getWorld().dropItem(dead.getLocation(), new ItemStack(Material.TOTEM_OF_UNDYING));
                    }
                }
            }
        }// ABLE


    }// end method






}
