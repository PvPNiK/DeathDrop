package me.PvPNiK.deathDrop.worlds;

import java.util.HashSet;
import java.util.Set;

public class DeathDropWorld {

    private String worldName;
    private Set<String> itemsToAdd;
    private Set<String> itemsToRemove;

    public DeathDropWorld(String worldName, Set<String> itemsToAdd, Set<String> itemsToRemove) {
        setWorldName(worldName);
        setItemsToAdd(itemsToAdd);
        setItemsToRemove(itemsToRemove);
    }

    public DeathDropWorld(String worldName) {
        setWorldName(worldName);
        this.itemsToAdd = new HashSet<String>();
        this.itemsToRemove = new HashSet<String>();
    }

    public void addItemToRemove(String itemKey) {
        itemsToRemove.add(itemKey);
    }

    public void removeItemToRemove(String itemKey) {
        itemsToRemove.remove(itemKey);
    }

    public void addItemToAdd(String itemKey) {
        itemsToAdd.add(itemKey);
    }

    public void removeItemToAdd(String itemKey) {
        itemsToAdd.remove(itemKey);
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public Set<String> getItemsToAdd() {
        return new HashSet<>(itemsToAdd);
    }

    public void setItemsToAdd(Set<String> itemsToAdd) {
        this.itemsToAdd = new HashSet<>(itemsToAdd);
    }

    public Set<String> getItemsToRemove() {
        return new HashSet<>(itemsToRemove);
    }

    public void setItemsToRemove(Set<String> itemsToRemove) {
        this.itemsToRemove = new HashSet<>(itemsToRemove);
    }

}
