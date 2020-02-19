package me.PvPNiK.deathDrop.commands.item;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.commands.Cmd;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ItemList extends Cmd {

    // /dd item list

    public ItemList() {
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!this.canExecuteCmd(sender)) {
            sender.sendMessage(Messages.noPermission());
            return false;
        }

        sender.sendMessage(Messages.PREFIX + "Items:");
        DeathDrop.getInstance().getItemManager().getKeyList().forEach((i) -> sender.sendMessage("> " + ChatColor.YELLOW + i));

        return true;
    }
}
