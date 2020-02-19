package me.PvPNiK.deathDrop.commands.item;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import me.PvPNiK.deathDrop.files.Configs;
import me.PvPNiK.deathDrop.commands.Cmd;

public class ItemRemove extends Cmd {

    // /dd item remove <item name>

    public ItemRemove() {
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!this.canExecuteCmd(sender)) {
            sender.sendMessage(Messages.noPermission());
            return false;
        }

        String itemKey = args[2];

        if (!DeathDrop.getInstance().getItemManager().remove(itemKey)) {
            sender.sendMessage(Messages.itemKeyNoExist(itemKey));
            return false;
        }

        YamlConfiguration c = Configs.ITEMS.getConfig();
        c.set(itemKey, null);
        Configs.ITEMS.save(c);

        sender.sendMessage(Messages.itemRemoved(itemKey));
        return true;
    }

}
