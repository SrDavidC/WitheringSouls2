package srdqrk.withering.listeners.dificultades;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import srdqrk.withering.Core;

import static srdqrk.withering.utilities.Items.getExplosiveBow;
import static srdqrk.withering.utilities.WitheringUtils.isNether;

public class Day7 extends DifficultChange{


    public Day7(Core instance, int dayAble){
        super(instance,dayAble);
    }

    @EventHandler
    public void onMonsterSpawns(CreatureSpawnEvent e){
        if (instance.isOn(dayAble)) {
            Entity spawned = e.getEntity();
            if (spawned instanceof Skeleton mob) { // Start of base of Skeletons classes
                int random = (int) ((Math.random() * 4) + 1);
                switch (random) {
                    case 1:
                        //Sniper
                        mob.getEquipment().setHelmet(new ItemBuilder(
                                Material.LEATHER_HELMET).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).armorColor(Color.BLACK).build());
                        mob.getEquipment().setChestplate(new ItemBuilder(
                                Material.LEATHER_CHESTPLATE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).armorColor(Color.BLACK).build());
                        mob.getEquipment().setLeggings(new ItemBuilder(
                                Material.LEATHER_LEGGINGS).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).armorColor(Color.BLACK).build());
                        mob.getEquipment().setBoots(new ItemBuilder(
                                Material.LEATHER_BOOTS).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).armorColor(Color.BLACK).build());
                        mob.getEquipment().setItemInMainHand(new ItemBuilder(
                                Material.BOW).enchant(Enchantment.ARROW_DAMAGE, 15).build());
                        mob.setCustomName(ChatColor.BLACK + "" + ChatColor.BOLD + "SNIPER SKELETON");
                        break;
                    case 2:
                        //Tank
                        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
                        mob.setHealth(40D);
                        mob.getEquipment().setHelmet(new ItemBuilder(
                                Material.IRON_HELMET).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).armorColor(Color.BLACK).build());
                        mob.getEquipment().setChestplate(new ItemBuilder(
                                Material.IRON_CHESTPLATE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).armorColor(Color.BLACK).build());
                        mob.getEquipment().setLeggings(new ItemBuilder(
                                Material.IRON_LEGGINGS).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).armorColor(Color.BLACK).build());
                        mob.getEquipment().setBoots(new ItemBuilder(
                                Material.IRON_BOOTS).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).armorColor(Color.BLACK).build());
                        mob.getEquipment().setItemInMainHand(getExplosiveBow());
                        mob.setCustomName(ChatColor.GRAY + "" + ChatColor.BOLD + "TANK SKELETON");
                        break;
                    case 3:
                        // Extasis
                        mob.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
                        mob.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "EXTASIS SKELETON");
                        mob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 6010 * 20, 4));
                        mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6010 * 20, 4));
                        mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6010 * 20, 4));
                        mob.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6010 * 20, 4));
                        break;
                    case 4:
                        // Sans
                        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50D);
                        mob.setHealth(50D);
                        mob.setCustomName(ChatColor.AQUA + "Sans");
                        var bow = new ItemStack(Material.BOW);
                        var meta = bow.getItemMeta();
                        meta.setCustomModelData(113);
                        meta.setDisplayName(ChatColor.BLUE + "Soul Bow");
                        bow.setItemMeta(meta);
                        var skeletonEquipment = mob.getEquipment();
                        skeletonEquipment.setItemInMainHand(bow);

                        break;
                }// end switch
            }// Skeleton
            else if (isNether(spawned.getLocation()) && !(e.getEntity() instanceof Creeper)){
                int random = (int) ((Math.random() * 10) + 1);
                if (random <= 3){ // Nether creeper has 0.3 over 1 to spawns.
                    spawned.remove();
                    final Entity entityNew =  spawned.getWorld().spawnEntity(spawned.getLocation(), EntityType.CREEPER);
                    ((Creeper)entityNew).setPowered(true);
                    entityNew.setCustomName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "NETHER CREEPER");
                }
                else if ((spawned instanceof Evoker || spawned instanceof Ravager)){
                    double health = (( (Raider) spawned).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * 5;
                    ((Raider) spawned).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((health));
                    ((Raider) spawned).setHealth(health);
                }else if (spawned instanceof Silverfish){
                    double health = (( (Silverfish) spawned).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * 2;
                    ((Silverfish) spawned).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((health));
                    ((Silverfish) spawned).setHealth(health);
                    ((Silverfish) spawned).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6010 * 20, 2));
                }else if (spawned instanceof Spider){
                    ((Spider) spawned).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, 2,true,false));
                }
            }
        }//Able

    }//Method end

    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        var entity = e.getEntity();
        if (entity instanceof Skeleton && entity.getCustomName() != null
                && entity.getCustomName().contains("Sans")) {
            for (Entity ent : e.getEntity().getNearbyEntities(13,13,13)){
                if (ent instanceof Player p){
                    p.playSound(p.getLocation(), "sans_talking", 1, 1);
                }
            }
        }
    }
    @EventHandler
    public void skeletonsDamage(EntityDamageEvent e) {
        var entity = e.getEntity();
        if (entity instanceof Skeleton && entity.getCustomName() != null
                && entity.getCustomName().contains("Sans") && (e.getCause() == EntityDamageEvent.DamageCause.WITHER || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void sounds(EntityDamageByEntityEvent e) {
        var damager = e.getDamager();
        var entity = e.getEntity();
        if (entity instanceof Skeleton && entity.getCustomName() != null
                && entity.getCustomName().contains("Sans") && (damager instanceof Player || damager instanceof Arrow)) {
            for (Entity ent : e.getEntity().getNearbyEntities(13, 13, 13)) {
                if (ent instanceof Player p) {
                    p.playSound(p.getLocation(), "sans_song", 1, 1);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (instance.isOn(dayAble)) {
            Entity damager = e.getDamager();
            if (damager instanceof Raider) {
                e.setDamage( e.getDamage() * 0.30);
            }else if( (damager instanceof Spider) && ( e.getEntity() instanceof Player p )  ){
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5 * 20, 2));
            }
        }//Able
    }//Method

    @EventHandler
    public void onMonsterDies(EntityDeathEvent e){
        if (instance.isOn(dayAble)) {
            Entity dead = e.getEntity();
            if (dead instanceof PigZombie){
                e.getDrops().clear();
                dead.getWorld().dropItem(dead.getLocation(), new ItemStack(Material.ROTTEN_FLESH,3));
            }

        }//Able
    }// Method




}
