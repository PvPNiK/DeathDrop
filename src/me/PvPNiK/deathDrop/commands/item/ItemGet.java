package me.PvPNiK.deathDrop.commands.item;

import java.util.HashMap;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.PvPNiK.deathDrop.commands.Cmd;

public class ItemGet extends Cmd {

    // /dd item get <item name>

    public ItemGet() {
        this.setConsoleUse(false);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!this.canExecuteCmd(sender)) {
            sender.sendMessage(Messages.noPermission());
            return false;
        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player p = (Player) sender;

        String itemKey = args[2];

        if (!DeathDrop.getInstance().getItemManager().exist(itemKey)) {
            p.sendMessage(Messages.itemKeyNoExist(itemKey));
            return false;
        }

        HashMap<Integer, ItemStack> unStored = p.getInventory().addItem(DeathDrop.getInstance().getItemManager().get(itemKey));

        if (unStored.isEmpty()) {
            p.sendMessage(Messages.itemAddedToInv(itemKey));
            return true;
        }

        p.sendMessage(Messages.inventoryFull(itemKey));

        return true;
    }

}
