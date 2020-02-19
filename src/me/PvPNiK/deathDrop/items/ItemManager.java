package me.PvPNiK.deathDrop.items;

import java.util.HashMap;
import java.util.Set;

import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.PvPNiK.deathDrop.files.Configs;

public class ItemManager implements Listener {

    private HashMap<String, ItemStack> items;

    public ItemManager() {
        items = new HashMap<>();
    }

    public Set<String> getKeyList() {
        return items.keySet();
    }

    public ItemStack get(String key) {
        if (!exist(key))
            return null;

        return items.get(key);
    }

    public boolean exist(String key) {
        return items.containsKey(key);
    }

    public boolean add(String key, ItemStack is) {
        if (exist(key))
            return false;

        items.put(key, is);
        return true;
    }

    public boolean remove(String key) {
        if (!exist(key))
            return false;

        items.remove(key);
        return true;
    }

    public boolean load() {
        YamlConfiguration config = Configs.ITEMS.getConfig();
        Set<String> keys = config.getKeys(false);

        if (keys.isEmpty())
            return false;

        HashMap<String, ItemStack> items = new HashMap<>();
        ItemStack is = null;

        for (String key : keys) {

            try {
                is = config.getItemStack(key);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(Messages.PREFIX + "Error while loading item: " + key);
                e.printStackTrace();
                continue;
            }

            items.put(key, is);
        }

        this.items.putAll(items);
        return true;
    }

}
