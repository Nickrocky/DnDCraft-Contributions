package com.dndcraft.craftcodex.api.util.transaction;

import org.bukkit.plugin.java.JavaPlugin;

public interface Transaction {
    /**
     * Who is asking for this transaction
     * */
    JavaPlugin getOriginator();

    /**
     * Get the reason for the transaction
     * */
    String getReason();

}
