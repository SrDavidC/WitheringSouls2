package srdqrk.withering.skills;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import srdqrk.withering.Core;

import java.util.HashMap;
import java.util.UUID;

public class SkillsManager implements Listener {
    Core instance;
    HashMap<UUID,Skill> mobs;
    static SkeletonStampede skeletonStampede;
    public SkillsManager(Core instance){
        this.instance = instance;
        mobs = new HashMap<>();
        skeletonStampede = new SkeletonStampede(instance);
    }



    //Damage Trigger Skills
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (mobs.get(e.getEntity().getUniqueId()) != null  ){
            mobs.get(e.getEntity().getUniqueId()).trigger(e.getEntity().getWorld(),e.getDamager().getLocation());
        }
    }
    @EventHandler void onCreatureSpawn(CreatureSpawnEvent e){
        Entity spawned = e.getEntity();
        if (spawned instanceof Pillager mob){
            if (mob.getCustomName().equalsIgnoreCase
                    ((ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Pillager Skeleton" ))){
                handleMobs(mob.getUniqueId(),new SkeletonStampede(instance));
            }
        }
    }

    public void handleMobs(UUID uuid, Skill skill){
        if (mobs.containsKey(uuid)){
            return;
        }
        mobs.put(uuid,skill);
    }
    public void removeMob(UUID uuid){
        mobs.remove(uuid);
    }

}
