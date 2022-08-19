package srdqrk.withering.skills;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import srdqrk.withering.Core;

import java.util.HashMap;

public class SkillsManager implements Listener {
    Core instance;
    HashMap<Integer,Entity> mobs;
    static SkeletonStampede skeletonStampede;
    public SkillsManager(Core instance){
        this.instance = instance;
        mobs = new HashMap<>();
        skeletonStampede = new SkeletonStampede(instance);
    }



    //Damage Trigger Skills
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        Entity spawned = e.getEntity();
        if (spawned instanceof Pillager mob){
            if (mob.getCustomName().equalsIgnoreCase
                    ((ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Pillager Skeleton" ))){
                skeletonStampede.trigger(e.getEntity().getWorld(), e.getEntity().getLocation());
            }
        }
    }

}
