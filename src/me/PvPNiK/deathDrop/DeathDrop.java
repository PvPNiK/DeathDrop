package me.PvPNiK.deathDrop;

import lombok.Getter;
import me.PvPNiK.deathDrop.files.Configs;
import me.PvPNiK.deathDrop.worlds.DeathDropWorld;
import me.PvPNiK.deathDrop.worlds.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.PvPNiK.deathDrop.commands.CmdManager;
import me.PvPNiK.deathDrop.events.DeathListener;
import me.PvPNiK.deathDrop.items.ItemManager;

import java.util.HashSet;
import java.util.Set;

public class DeathDrop extends JavaPlugin {

    @Getter
    private static DeathDrop instance;
    @Getter
    private WorldManager worldManager;
    @Getter
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        instance = this;
        worldManager = new WorldManager();
        itemManager = new ItemManager();

        for (Configs fi : Configs.values())
            fi.createFile();

        getCommand("deathdrop").setExecutor(new CmdManager());

        Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemManager(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WorldManager(), this);

        itemManager.load();
        worldManager.load();
    }

    @Override
    public void onDisable() {
        Set<String> worldNames = new HashSet<String>(worldManager.getWorldsName());
        for (String worldName : worldNames) {
            DeathDropWorld ddw = worldManager.get(worldName);
            if (ddw != null)
                worldManager.save(ddw);
        }
    }
}
