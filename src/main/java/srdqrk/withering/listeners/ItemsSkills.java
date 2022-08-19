package srdqrk.withering.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import srdqrk.withering.Core;

import java.util.Objects;

public class ItemsSkills implements Listener {

    Core instance;
    public ItemsSkills(Core instance){
        this.instance = instance;
    }


    @EventHandler
    public void SpecialShoots(EntityShootBowEvent e) {
        if (e.getProjectile() == null)
            return;
        if (Objects.requireNonNull(Objects.requireNonNull(e.getBow()).getItemMeta()).hasCustomModelData()) {
            int model = Objects.requireNonNull(e.getBow().getItemMeta()).getCustomModelData();
            Entity proj = e.getProjectile();
            switch (model) {
                case 132:
                    proj.setMetadata("explosive_arrow", new FixedMetadataValue(instance, true));
                    break;
                case 133:
                    proj.setMetadata("explosiveLow_arrow", new FixedMetadataValue(instance, true));
                    break;
                case 113:
                    var skull = e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation().add(0, 1, 0),
                            EntityType.WITHER_SKULL);
                    e.setProjectile(skull);
                    break;

            }
        }
    }
    @EventHandler
    public void hit(ProjectileHitEvent e) {
        if (e.getEntity() == null)
            return;
        Location loc = e.getEntity().getLocation();
        World world = loc.getWorld();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        if (e.getEntity().hasMetadata("explosive_arrow")) {
            e.getEntity().remove();
            world.createExplosion(x, y, z, 9, false, false);
        } else if (e.getEntity().hasMetadata("explosiveLow_arrow")) {
            e.getEntity().remove();
            if (instance.getGame().getDay() < 15) {
                world.createExplosion(x, y, z, 4, false, false);
            } else {
                world.createExplosion(x, y, z, 5, false, false);
            }
        }
    }
}



