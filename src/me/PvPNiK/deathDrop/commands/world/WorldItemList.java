package me.PvPNiK.deathDrop.commands.world;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.commands.Cmd;
import me.PvPNiK.deathDrop.utils.Messages;
import me.PvPNiK.deathDrop.worlds.DeathDropWorld;
import me.PvPNiK.deathDrop.worlds.WorldManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class WorldItemList extends Cmd {
    // /dd world <world name> list

    public WorldItemList() {
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!this.canExecuteCmd(sender)) {
            sender.sendMessage(Messages.noPermission());
            return false;
        }

        String worldName = args[1];

        WorldManager wm = DeathDrop.getInstance().getWorldManager();

        if (!wm.exist(worldName)) {
            sender.sendMessage(Messages.worldNoExist(worldName));
            return false;
        }

        sender.sendMessage(worldName + ":");

        DeathDropWorld ddw = wm.get(worldName);

        sender.sendMessage(Messages.PREFIX + "Drop list:");
        ddw.getItemsToAdd().forEach((i) -> sender.sendMessage("> " + ChatColor.YELLOW + i));

        sender.sendMessage(" ");

        sender.sendMessage(Messages.PREFIX + "Undrop list:");
        ddw.getItemsToRemove().forEach((i) -> sender.sendMessage("> " + ChatColor.YELLOW + i));

        return true;
    }
}
