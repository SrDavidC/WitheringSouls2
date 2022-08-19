package srdqrk.withering.listeners.dificultades;

import lombok.Data;
import org.bukkit.event.Listener;
import srdqrk.withering.Core;
@Data
public abstract class DifficultChange implements Listener {
     Core instance;
     int dayAble;

    public DifficultChange(Core instance, int dayAble){
        this.instance = instance;
        this.dayAble = dayAble;
    }



}
