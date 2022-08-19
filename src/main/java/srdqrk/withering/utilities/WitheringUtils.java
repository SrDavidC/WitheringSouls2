package srdqrk.withering.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public class WitheringUtils {
    public static String prefix = ChatColor.DARK_AQUA + "[ABYSM] ";
    public static String noPerms = ChatColor.DARK_GRAY + "Usted no tiene permisos para este comando. Si cree que es un error "  +
                "contacte en Discord con @DavidAS#2830 (SrDqrk)\"";

     public static boolean isNether(Location l){
        Biome biome = l.getWorld().getBiome(l);
         if (biome == Biome.BASALT_DELTAS || biome == Biome.CRIMSON_FOREST || biome == Biome.NETHER_WASTES ||
         biome == Biome.WARPED_FOREST || biome == Biome.SOUL_SAND_VALLEY){
             return true;
         }
         return false;
    }
    public static boolean isEnd(Location l){
        Biome biome = l.getWorld().getBiome(l);
        if (biome == Biome.END_BARRENS || biome == Biome.END_HIGHLANDS|| biome == Biome.END_MIDLANDS ||
                biome == Biome.THE_END || biome == Biome.SMALL_END_ISLANDS){
            return true;
        }
        return false;
    }
    public static boolean checkPermission(Player player){
        if (!player.hasPermission("abysm.daysset")){
            Bukkit.broadcastMessage(prefix + noPerms);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM,1.0F,0);
            return false;
        }
        return true;
    }


}
