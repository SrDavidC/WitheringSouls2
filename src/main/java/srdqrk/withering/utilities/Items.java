package srdqrk.withering.utilities;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Items {


    static public ItemStack getExplosiveBow(){
        ItemStack bow = new ItemBuilder(Material.BOW).enchant(Enchantment.ARROW_DAMAGE,10).addLore("BOOM").build();
        var meta = bow.getItemMeta();
        meta.setCustomModelData(133);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.DARK_AQUA + "Soul Bow");
        bow.setItemMeta(meta);
        return bow;
    }
    static public ItemStack getPillagerSkeletonCrossBow(){
        return new ItemBuilder(Material.CROSSBOW).enchant(Enchantment.DURABILITY, 100)
                .enchant(Enchantment.MULTISHOT, 5).enchant(Enchantment.PIERCING, 10)
                .enchant(Enchantment.QUICK_CHARGE, 3).build();
    }
}
