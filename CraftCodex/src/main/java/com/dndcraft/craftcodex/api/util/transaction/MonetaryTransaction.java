package com.dndcraft.craftcodex.api.util.transaction;

import com.dndcraft.craftcodex.api.util.interactions.MonetaryInteractions;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class MonetaryTransaction implements Transaction {

    private final JavaPlugin pluginName;
    @Getter private final MonetaryInteractions monetaryInteraction;
    @Getter private final double value;

    public MonetaryTransaction(JavaPlugin pluginName, MonetaryInteractions monetaryInteraction, double value){
        this.pluginName = pluginName;
        this.monetaryInteraction = monetaryInteraction;
        if(monetaryInteraction.equals(MonetaryInteractions.SPENT) || monetaryInteraction.equals(MonetaryInteractions.LOST) || monetaryInteraction.equals(MonetaryInteractions.DEPOSITED)){
            this.value = Math.abs(value)*-1;
        }else{
            this.value = Math.abs(value);
        }
    }

    /**
     * Who is asking for this transaction
     */
    @Override
    public JavaPlugin getOriginator() {
        return pluginName;
    }

    /**
     * Get the reason for the transaction
     */
    @Override
    public String getReason() {
        return monetaryInteraction.name().toLowerCase();
    }
}
