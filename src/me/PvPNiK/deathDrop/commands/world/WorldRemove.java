package me.PvPNiK.deathDrop.commands.world;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.commands.Cmd;
import me.PvPNiK.deathDrop.utils.Messages;
import me.PvPNiK.deathDrop.worlds.WorldManager;
import org.bukkit.command.CommandSender;

public class WorldRemove extends Cmd {

    // /dd world <world name> list/remove | 3

    public WorldRemove() {
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


        if (!wm.delete(worldName)) {
            sender.sendMessage(Messages.PREFIX + "Something went wrong," + worldName);
            return false;
        }

        sender.sendMessage(Messages.worldRemoved(worldName));

        return true;
    }

}
