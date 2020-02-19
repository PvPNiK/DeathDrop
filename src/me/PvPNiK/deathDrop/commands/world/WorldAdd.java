package me.PvPNiK.deathDrop.commands.world;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.commands.Cmd;
import me.PvPNiK.deathDrop.utils.Messages;
import me.PvPNiK.deathDrop.worlds.DeathDropWorld;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class WorldAdd extends Cmd {

    //  /dd world <world name> undrop/drop add <item name> | 5

    public WorldAdd() {
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!this.canExecuteCmd(sender)) {
            sender.sendMessage(Messages.noPermission());
            return false;
        }

        String worldName = args[1];

        try {
            Bukkit.getWorld(worldName).getSpawnLocation();
        } catch (Exception e) {
            sender.sendMessage(Messages.worldNoExist(worldName));
            return false;
        }

        String itemKey = args[4];

        if (!DeathDrop.getInstance().getItemManager().exist(itemKey)) {
            sender.sendMessage(Messages.itemKeyNoExist(itemKey));
            return false;
        }

        String action = args[2];

        if (!action.equalsIgnoreCase("undrop") && !action.equalsIgnoreCase("drop")) {
            sender.sendMessage(Messages.PREFIX + "'undrop' or 'drop' only");
            return false;
        }

        if (!DeathDrop.getInstance().getWorldManager().exist(worldName)) {
            DeathDrop.getInstance().getWorldManager().add(new DeathDropWorld(worldName));
        }

        if (action.equalsIgnoreCase("undrop")) {

            DeathDrop.getInstance().getWorldManager().get(worldName).addItemToRemove(itemKey);
            sender.sendMessage(Messages.PREFIX + "Item: " + itemKey + " has beed ADDED to *UNdrop* list in world: " + worldName);
            return true;

        } else if (action.equalsIgnoreCase("drop")) {

            DeathDrop.getInstance().getWorldManager().get(worldName).addItemToAdd(itemKey);
            sender.sendMessage(Messages.PREFIX + "Item: " + itemKey + " has beed ADDED to *DROP* list in world: " + worldName);
            return true;

        }
        return false;
    }
}
