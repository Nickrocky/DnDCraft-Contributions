package com.dndcraft.prometheus.api;

import com.dndcraft.prometheus.api.manager.ProItemUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PrometheusAPI extends JavaPlugin {

    @Getter protected static PrometheusAPI api;

    @Getter @Setter protected ProItemUtil proItemUtil;
}
