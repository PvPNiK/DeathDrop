package me.PvPNiK.deathDrop.utils;

import org.bukkit.ChatColor;

public class Messages {

    private Messages() {
    }

    public static final String PREFIX = ChatColor.RED + "[" + ChatColor.WHITE + "DeathDrop" + ChatColor.RED + "] " + ChatColor.RESET;

    public static String noPermission() {
        return PREFIX + "Not enough permissions!";
    }

    public static String itemKeyExist(String itemKey) {
        return PREFIX + itemKey + " already exist!";
    }

    public static String itemKeyNoExist(String itemKey) {
        return PREFIX + "Could not find, " + itemKey;
    }

    public static String itemAdded(String itemKey) {
        return PREFIX + itemKey + " added!";
    }

    public static String itemRemoved(String itemKey) {
        return PREFIX + itemKey + " removed!";
    }

    public static String itemAddedToInv(String itemKey) {
        return PREFIX + itemKey + " added to your inventory!";
    }

    public static String inventoryFull(String itemKey) {
        return PREFIX + "Couldn't add, " + itemKey + ". your inventory is full.";
    }

    public static String invalidItem() {
        return PREFIX + "Invalid item.";
    }

    public static String worldNoExist(String worldName) {
        return PREFIX + "Could not find, " + worldName;
    }

    public static String worldRemoved(String worldName) {
        return PREFIX + worldName + " removed!";
    }

}
