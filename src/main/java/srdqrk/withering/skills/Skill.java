package srdqrk.withering.skills;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.World;
import srdqrk.withering.Core;
@Data
public abstract class Skill {
    private int cooldown;
    private Core instance;
    private String name;
    private SkillType type;

    public Skill(int cooldown, Core instance, String name, SkillType type) {
        this.cooldown = cooldown;
        this.instance = instance;
        this.name = name;
        this.type = type;
    }

    enum SkillType{
        MONSTER, RUNE;
    }


    public abstract void trigger(World w, Location location);


}
