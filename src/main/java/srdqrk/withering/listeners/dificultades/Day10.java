package srdqrk.withering.listeners.dificultades;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import srdqrk.withering.Core;

import java.util.Random;

import static srdqrk.withering.utilities.Items.getPillagerSkeletonCrossBow;
import static srdqrk.withering.utilities.WitheringUtils.isEnd;

public class Day10 extends DifficultChange{
    Random rand = new Random();
    public Day10(Core instance, int dayAble){
        super(instance,dayAble);
    }


    @EventHandler
    public void onMonsterSpawns(CreatureSpawnEvent e){
        Entity spawned = e.getEntity();
        if (spawned instanceof Creeper mob){
            mob.setExplosionRadius(6);
            if (isEnd(mob.getLocation())){
                mob.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 6010 * 20, 0));
            }
        }
        else if (isEnd(e.getLocation()) && !(spawned instanceof Creeper)){
            if (rand.nextInt(11) < 2 ){
                spawned.remove();
                final Entity entityNew =  spawned.getWorld().spawnEntity(spawned.getLocation(), EntityType.CREEPER);
                ((Creeper)entityNew).setPowered(true);
                entityNew.setCustomName(ChatColor.WHITE + "" + ChatColor.BOLD + "END CREEPER");
            }
        }
        else if (spawned instanceof Enderman mob){
            mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6010 * 20, 5));
            mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6010 * 20, 3));
        }else if (spawned instanceof Ghast mob ){
            mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
            mob.setHealth(40D);
        }else if(spawned instanceof Pillager mob){
            if (rand.nextInt(11) < 2 ){
                mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100D);
                mob.setHealth(100D);
                mob.getEquipment().setItemInMainHand(getPillagerSkeletonCrossBow());
            }
        }
    }
}
