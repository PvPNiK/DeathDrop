package me.PvPNiK.deathDrop.files;

import java.io.File;
import java.io.IOException;

import lombok.Getter;
import me.PvPNiK.deathDrop.DeathDrop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Configs {

    ITEMS("Items");

    @Getter
    private String fileName;
    @Getter
    private File file;
    private FileConfiguration config;
    private String path = "";

    Configs(String fileName) {
        this.fileName = fileName;
    }

    private Configs(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public String getPath() {
        return DeathDrop.getInstance().getDataFolder() + File.separator + path;
    }

    public YamlConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(file);
    }

    public void save(FileConfiguration config) {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile() {
        if (!DeathDrop.getInstance().getDataFolder().exists())
            DeathDrop.getInstance().getDataFolder().mkdirs();

        new File(getPath()).mkdirs();
        file = new File(getPath(), fileName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
