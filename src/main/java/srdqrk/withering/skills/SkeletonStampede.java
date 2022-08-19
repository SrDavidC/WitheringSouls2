package srdqrk.withering.skills;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import srdqrk.withering.Core;

public class SkeletonStampede extends Skill{

    SkeletonStampede(Core instance){
        super(0,instance,"Skeleton Stampede",SkillType.MONSTER);
    }
    @Override
    public void trigger(World w, Location location) {
        if (getInstance().getGame().getRand().nextInt(11) < 2) {
            for (Entity ent : w.getNearbyEntities(location, 13, 13, 13)
            ) {
                if (ent instanceof Player) {
                    ((Player) ent).playSound(ent.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 0.3f);
                    (ent).sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Skeleton Pillager: " + ChatColor.GRAY +
                            "¡Adelante esbirros!");
                }
            }
            for (int i = 0; i < 5; i++) {
                spawnExtasisSkeletons(w, location);
            }
        }
        if ((getInstance().getGame().getRand().nextInt(11) <= 2)) {
            for (Entity ent : w.getNearbyEntities(location, 13, 13, 13)
            ) {
                if (ent instanceof Player) {
                    ((Player) ent).playSound(ent.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 0.3f);
                    ((Player)ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 2));
                    ((Player)ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 3 * 20, 2));
                    ((Player)ent).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 3 * 20, 2));
                }
            }
        }
    }

    private void spawnExtasisSkeletons(World w, Location l){
       final Skeleton mob = (Skeleton) w.spawnEntity(l, EntityType.SKELETON);
        mob.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
        mob.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "EXTASIS SKELETON");
        mob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 6010 * 20, 4));
        mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6010 * 20, 4));
        mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6010 * 20, 4));
        mob.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6010 * 20, 4));
    }

}
