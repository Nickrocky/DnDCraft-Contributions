package com.dndcraft.vulcan.managers.warp;

import com.dndcraft.atlas.Atlas;
import com.dndcraft.vulcan.Vulcan;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Author Commissar_Voop
 */

public class WarpManager {

    private static boolean LOADED;
    public WarpManager(){}

    private HashMap<String,Warp> WarpMap = new HashMap<>();

    /**
     * Loads the WarpManager
     * Loads the warp data from a SQLite or SQL based database
     */
    public void load() {
        if (LOADED) throw new IllegalStateException("Looks like warp manager is loaded!");
        Bukkit.getServer().getPluginManager().registerEvents(new WarpListeners(),Vulcan.get());
        try {
            Atlas.getSQLHandler().database().executeUpdate("CREATE TABLE IF NOT EXISTS vulcan_data_warps(" +
                    "warp_name TEXT UNSIGNED NOT NULL," +
                    "world TEXT UNSIGNED NOT NULL," +
                    "x DOUBLE NOT NULL," +
                    "y DOUBLE NOT NULL," +
                    "z DOUBLE NOT NULL," +
                    "yaw FLOAT NOT NULL," +
                    "pitch FLOAT NOT NULL," +
                    "PRIMARY KEY (warp_name));");
            var SQL_DATA = Atlas.getSQLHandler().database().getResults("SELECT * FROM vulcan_data_warps");
            SQL_DATA.forEach(sql->{
                World world = Bukkit.getWorld(UUID.fromString(sql.getString("world")));
                String name = sql.getString("warp_name");
                if (world!=null) {
                    double x = sql.getDbl("x");
                    double y = sql.getDbl("y");
                    double z = sql.getDbl("z");
                    float yaw = sql.getFloat("yaw");
                    float pitch = sql.getFloat("pitch");
                    this.WarpMap.put(name,new Warp(name,world,x,y,z,yaw,pitch));
                } else Vulcan.get().getLogger().warning("Warp ("+name+") will not be loaded due to the world is not present on the server!");
            });
        } catch (SQLException e) {e.printStackTrace();}
        LOADED = true;
    }

    /**
     * Get Warp from a string value
     * @param warp_name Warp Name
     * @return
     */
    public Warp getWarp(String warp_name){
        return this.WarpMap.get(warp_name);
    }

    /**
     * Get current all of the warp's
     * @return List of warps in a Array listing
     */
    public List<Warp> getWarps(){
        List<Warp> res = new ArrayList<>();
        this.WarpMap.values().forEach(w->{if (!res.contains(w)) res.add(w);});
        return res;
    }

    /**
     * A getter for a target player accessable warps
     * @param p Player
     * @return get Player's allowed warp
     */
    public List<Warp> getWarpsFromPlayer(Player p) {
        List<Warp> res = new ArrayList<>();
        this.WarpMap.values().forEach(w->{if(p.hasPermission(w.getPermission()))res.add(w);});
        return res;
    }

    /**
     * A check if the warp is currently loaded or present
     * @param warp_name warp name
     * @return if the warp exists
     */
    public boolean doesWarpExist(String warp_name) {
        return this.WarpMap.containsKey(warp_name);
    }

    /**
     * Add the warp
     * @param name Warp Name
     * @param location Location of the warp
     */
    public void addWarp(String name, Location location) {
        if (this.doesWarpExist(name)) return;
        Atlas.getSQLHandler().database().executeUpdateAsync("REPLACE INTO vulcan_data_warps (warp_name,world,x,y,z,yaw,pitch) VALUES(?,?,?,?,?,?,?)",
                name,location.getWorld().getUID().toString(),location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
        this.WarpMap.put(name,new Warp(name,location.getWorld(),location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch()));
    }

    /**
     * Deletes the warp
     * @param warp_name Warp Name
     */
    public void removeWarp(String warp_name) {
        if (!this.doesWarpExist(warp_name)) return;
        Atlas.getSQLHandler().database().executeUpdateAsync("DELETE FROM vulcan_data_warps WHERE warp_name = ?",warp_name);
        this.WarpMap.remove(warp_name);
    }

    /**
     * This is only used for {@link WarpListeners}.
     * This is used only when the world gets loaded, so it can loads its warps as well.
     * @param world Target World
     */
    void eventExecuteOnLoad(World world) {
        try {
            var SQL_DATA = Atlas.getSQLHandler().database().getResults("SELECT * FROM vulcan_data_warps WHERE world = ?",world.getUID().toString());
            SQL_DATA.forEach(sql->{
                String name = sql.getString("warp_name");
                double x = sql.getDbl("x");
                double y = sql.getDbl("y");
                double z = sql.getDbl("z");
                float yaw = sql.getFloat("yaw");
                float pitch = sql.getFloat("pitch");
                this.WarpMap.put(name,new Warp(name,world,x,y,z,yaw,pitch));
            });
        } catch (SQLException e) {e.printStackTrace();}
    }

    /**
     * This is only used for {@link WarpListeners}.
     * This is used for if the world get unloaded so it unload the warps as well, this prevents from unwanted or unexpected behavior to accrue!
     * @param world Target World
     */
    void eventExecuteOnUnLoad(World world) {
        List<String> toRemove = new ArrayList<>();
        try {
            var SQL_DATA = Atlas.getSQLHandler().database().getResults("SELECT warp_name FROM vulcan_data_warps WHERE world = ?",world.getUID().toString());
            SQL_DATA.forEach(sql->{
                String name = sql.getString("warp_name");
                toRemove.add(name);
            });
        } catch (SQLException e) {e.printStackTrace();}
        toRemove.forEach(w->this.WarpMap.remove(w));
    }

    /**
     * Main Warp Data Structure, that is used for storing data.
     */
    @RequiredArgsConstructor
    @FieldDefaults(level= AccessLevel.PRIVATE)
    @EqualsAndHashCode
    @Getter
    public final class Warp {
        private final String name;
        private final World world;
        private final Double x;
        private final Double y;
        private final Double z;
        private final Float yaw;
        private final Float pitch;
        public Location toLocation(){return new Location(world,x,y,z,yaw,pitch);}
        public String getPermission(){return "vulcan.warp."+this.name;}
    }

}
