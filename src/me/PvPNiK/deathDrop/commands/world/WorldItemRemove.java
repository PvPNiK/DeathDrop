package me.PvPNiK.deathDrop.commands.world;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.commands.Cmd;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class WorldItemRemove extends Cmd {

    //  /dd world <world name> -/+ remove <item name> | 5

    public WorldItemRemove() {
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!this.canExecuteCmd(sender)) {
            sender.sendMessage(Messages.noPermission());
            return false;
        }

        String worldName = args[1];

        try {
            Bukkit.getWorld(worldName);
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

        if (action.equalsIgnoreCase("undrop")) {

            DeathDrop.getInstance().getWorldManager().get(worldName).removeItemToRemove(itemKey);
            sender.sendMessage(Messages.PREFIX + "Item: " + itemKey + " has beed REMOVED from *UNdrop* list in world: " + worldName);
            return true;

        } else if (action.equalsIgnoreCase("drop")) {

            DeathDrop.getInstance().getWorldManager().get(worldName).removeItemToAdd(itemKey);
            sender.sendMessage(Messages.PREFIX + "Item: " + itemKey + " has beed REMOVED from *DROP* list in world: " + worldName);
            return true;

        } else {
            sender.sendMessage(Messages.PREFIX + "'undrop' or 'drop' only");
            return false;
        }

    }
}
