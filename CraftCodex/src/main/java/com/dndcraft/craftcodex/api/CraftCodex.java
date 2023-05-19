package com.dndcraft.craftcodex.api;

import com.dndcraft.craftcodex.api.manager.AccountManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftCodex extends JavaPlugin {

    @Getter
    protected static CraftCodex api;

    @Getter @Setter
    protected static AccountManager accountManager;


}
