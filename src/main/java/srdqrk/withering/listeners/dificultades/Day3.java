package srdqrk.withering.listeners.dificultades;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import srdqrk.withering.Core;

public class Day3 extends DifficultChange{

    public Day3(Core instance, int ableDay) {
        super(instance,ableDay);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (instance.isOn(dayAble)) {
            Entity damager = e.getDamager();
            if (damager instanceof Zombie) {
                e.setDamage( e.getDamage() * 0.30);
            }
        }
    }

    @EventHandler
    public void onMonsterSpawns(CreatureSpawnEvent e){
        if (instance.isOn(dayAble)){
            Entity spawned = e.getEntity();
            if (spawned instanceof Skeleton && instance.getGame().getDay() < 5){
                Skeleton sk = (Skeleton) spawned;
                sk.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 6010 * 20,0));
                sk.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6010 * 20,0));
                sk.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6010 * 20,0));
            }else if (spawned instanceof Creeper){
                Creeper cr = (Creeper) spawned;
                cr.setSilent(true);
                cr.setFuseTicks(20);
            }
        }
    }



}
