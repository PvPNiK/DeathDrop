package me.PvPNiK.deathDrop.commands.world;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.commands.Cmd;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class WorldList extends Cmd {

    // /dd world list

    public WorldList() {
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!this.canExecuteCmd(sender)) {
            sender.sendMessage(Messages.noPermission());
            return false;
        }

        sender.sendMessage(Messages.PREFIX + "Worlds:");

        DeathDrop.getInstance().getWorldManager().getWorldsName().forEach((w) -> sender.sendMessage("> " + ChatColor.YELLOW + w));

        return true;
    }
}
