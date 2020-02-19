package me.PvPNiK.deathDrop.commands;

import me.PvPNiK.deathDrop.commands.item.ItemCreate;
import me.PvPNiK.deathDrop.commands.item.ItemGet;
import me.PvPNiK.deathDrop.commands.item.ItemList;
import me.PvPNiK.deathDrop.commands.item.ItemRemove;
import me.PvPNiK.deathDrop.commands.world.*;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CmdManager implements CommandExecutor {

    enum Cmds {
        ITEM_CREATE, ITEM_GET, ITEM_REMOVE, ITEM_LIST, WORLD_LIST, WORLD_ITEM_LIST, WORLD_ADD, WORLD_ITEM_REMOVE, WORLD_REMOVE
    }

    private HashMap<Cmds, Cmd> commands;

    public CmdManager() {
        commands = new HashMap<>();
        commands.put(Cmds.ITEM_CREATE, new ItemCreate());
        commands.put(Cmds.ITEM_GET, new ItemGet());
        commands.put(Cmds.ITEM_REMOVE, new ItemRemove());
        commands.put(Cmds.ITEM_LIST, new ItemList());
        commands.put(Cmds.WORLD_ADD, new WorldAdd());
        commands.put(Cmds.WORLD_ITEM_REMOVE, new WorldItemRemove());
        commands.put(Cmds.WORLD_LIST, new WorldList());
        commands.put(Cmds.WORLD_ITEM_LIST, new WorldItemList());
        commands.put(Cmds.WORLD_REMOVE, new WorldRemove());
    }

    /*
     * /dd item create/remove/get <item name> | 3
     * /dd world <world name> undrop/drop add/remove <item name> | 5
     *
     * /dd world/item list | 2
     * /dd world <world name> list/remove | 3
     *
     */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("deathdrop"))
            return false;

        if (args.length == 0) {
            help(sender);
            return false;
        }

        String option = args[0];
        // /dd world/item list | 2
        if (args.length == 2 && args[1].equalsIgnoreCase("list")) {
            switch (option.toLowerCase()) {
                case "world":
                    commands.get(Cmds.WORLD_LIST).execute(sender, args);
                    return true;
                case "item":
                    commands.get(Cmds.ITEM_LIST).execute(sender, args);
                    return true;
                default:
                    sender.sendMessage(Messages.PREFIX + "Usage: /dd world/item list");
                    return false;
            }
        }
        // /dd world <world name> list/remove | 3  // /dd item create/remove/get <item name> | 3
        if (args.length == 3) {
            if (option.equalsIgnoreCase("world")) {
                if (args[2].equalsIgnoreCase("remove")) {
                    commands.get(Cmds.WORLD_REMOVE).execute(sender, args);
                    return true;
                } else if (args[2].equalsIgnoreCase("list")) {
                    commands.get(Cmds.WORLD_ITEM_LIST).execute(sender, args);
                    return true;
                }
                sender.sendMessage(Messages.PREFIX + "Is: " + args[2] + " sort of a satanic command?!?");
                sender.sendMessage(Messages.PREFIX + "Usage: /dd world " + args[1] + " list/remove");
                return false;
            } else if (option.equalsIgnoreCase("item")) {
                switch (args[1].toLowerCase()) {
                    case "create":
                        commands.get(Cmds.ITEM_CREATE).execute(sender, args);
                        return true;
                    case "remove":
                        commands.get(Cmds.ITEM_REMOVE).execute(sender, args);
                        return true;
                    case "get":
                        commands.get(Cmds.ITEM_GET).execute(sender, args);
                        return true;
                    default:
                        sender.sendMessage(Messages.PREFIX + "What is: " + args[1] + " sort of a satanic command?!?");
                        sender.sendMessage(Messages.PREFIX + "Usage: /dd item create/remove/get " + args[2]);
                        return false;
                }
            } else {
                help(sender);
                return false;
            }
        }
        // /dd world <world name> undrop/drop add/remove <item name> | 5
        if (option.equalsIgnoreCase("world") && args.length == 5) {
            switch (args[3].toLowerCase()) {
                case "add":
                    commands.get(Cmds.WORLD_ADD).execute(sender, args);
                    return true;
                case "remove":
                    commands.get(Cmds.WORLD_ITEM_REMOVE).execute(sender, args);
                    return true;
                default:
                    sender.sendMessage(Messages.PREFIX + "What is: " + args[3] + " sort of a satanic command?!?");
                    sender.sendMessage(Messages.PREFIX + "Usage: /dd world " + args[1] + " " + args[2] + " add/remove " + args[4]);
                    return false;
            }
        }

        help(sender);
        return false;
    }

    private void help(CommandSender sender) {
        sender.sendMessage(Messages.PREFIX + "Item Commands:");
        sender.sendMessage(cmdMsg("/dd item list", "List of all the items"));
        sender.sendMessage(cmdMsg("/dd item create <item name>", "Creates a new item"));
        sender.sendMessage(cmdMsg("/dd item get <item name>", "Get the item"));
        sender.sendMessage(cmdMsg("/dd item remove <item name>", "Removes the item"));
        sender.sendMessage(Messages.PREFIX + "World Commands:");
        sender.sendMessage(cmdMsg("/dd world <world name> list", "Prints all the items on Drop & UNdrop list of the world."));
        sender.sendMessage(cmdMsg("/dd world <world name> remove", "Removes the world. (plugin will not work in this world anymore)"));
        sender.sendMessage(cmdMsg("/dd world <world name> drop add/remove <item name>", "Add or Remove the item, from the DROP list of the world"));
        sender.sendMessage(cmdMsg("/dd world <world name> undrop add/remove <item name>", "Add or Remove the item, from the UNdrop list of the world"));
    }

    private String cmdMsg(String cmd, String des) {
        return ChatColor.YELLOW + cmd + ChatColor.RESET + " - " + ChatColor.AQUA + des;
    }

}
