package com.dndcraft.craftcodex.api.util.transaction;

import com.dndcraft.craftcodex.api.util.interactions.HealthInteractions;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class HealthTransaction implements Transaction{

    private final JavaPlugin pluginName;
    @Getter private final HealthInteractions interaction;
    @Getter private final int value;

    /**
     * Do not enter values as positive or negative depending on your interaction type, all valyes are just going to
     * be the absolute value of your input, and corrected sign wise depending on the transaction
     * This is to prevent inevitable headaches down the line.
     * */
    public HealthTransaction(JavaPlugin pluginName, HealthInteractions interaction, int value){
        this.pluginName = pluginName;
        this.interaction = interaction;
        if(interaction.equals(HealthInteractions.BREAK)){
            this.value = 0;
        }
        else{
            if(interaction.equals(HealthInteractions.DAMAGE)){
                this.value = Math.abs(value)*-1;
            }else{
                this.value = Math.abs(value);
            }
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
        return interaction.name().toLowerCase();
    }
}
