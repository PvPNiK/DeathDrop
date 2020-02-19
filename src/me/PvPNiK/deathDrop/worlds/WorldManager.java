package me.PvPNiK.deathDrop.worlds;

import com.google.gson.Gson;
import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.*;
import java.util.*;

public class WorldManager implements Listener {

    private HashMap<String, DeathDropWorld> worlds;

    public WorldManager() {
        worlds = new HashMap<>();
    }

    public Set<String> getWorldsName() {
        return worlds.keySet();
    }

    public boolean exist(String worldName) {
        return worlds.containsKey(worldName);
    }

    public DeathDropWorld get(String worldName) {
        if (!exist(worldName))
            return null;

        return worlds.get(worldName);
    }

    public boolean add(DeathDropWorld ddw) {
        if (ddw == null)
            return false;

        String worldName = ddw.getWorldName();

        if (exist(worldName))
            return false;

        worlds.put(worldName, ddw);
        return true;
    }

    private boolean remove(String worldName) {
        if (!exist(worldName))
            return false;

        worlds.remove(worldName);
        return true;
    }

    public void save(DeathDropWorld deathDropWorld) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(pathname(deathDropWorld.getWorldName()))) {
            gson.toJson(deathDropWorld, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(String worldName) {
        remove(worldName);

        if (!hasFile(worldName))
            return false;

        File f = new File(pathname(worldName));
        if (!f.delete()) {
            Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "Could not delete file: " + worldName + ".json");
            return false;
        }
        return true;
    }

    private String pathname(String worldName) {
        return DeathDrop.getInstance().getDataFolder().getPath() + File.separator + "Worlds" + File.separator
                + worldName + ".json";
    }

    public boolean hasFile(String worldName) {
        return new File(pathname(worldName)).exists();
    }

    private void createFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void load() {
        String path = DeathDrop.getInstance().getDataFolder().getPath() + File.separator + "Worlds";

        createFolder(path);

        List<String> results = new ArrayList<>();
        File[] files = new File(path).listFiles();

        if (files == null)
            return;

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                results.add(file.getName());
            }
        }

        results.forEach((w) -> add(load(w)));
    }

    private DeathDropWorld load(String worldName) {
        worldName = worldName.endsWith(".json") ? worldName.replace(".json", "") : worldName;
        Gson gson = new Gson();
        if (!hasFile(worldName)) {
            Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "DeathDrop could not find world's file: " + worldName);
            return null;
        }
        try (Reader reader = new FileReader(pathname(worldName))) {
            DeathDropWorld ddw = gson.fromJson(reader, DeathDropWorld.class);
            return ddw;
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "Could not load world data: " + worldName);
            e.printStackTrace();
        }
        return null;
    }

}
