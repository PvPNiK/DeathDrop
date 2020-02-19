package me.PvPNiK.deathDrop.commands.item;

import me.PvPNiK.deathDrop.DeathDrop;
import me.PvPNiK.deathDrop.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.PvPNiK.deathDrop.files.Configs;
import me.PvPNiK.deathDrop.commands.Cmd;

public class ItemCreate extends Cmd {

    // /dd item create <item name>

    public ItemCreate() {
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

        if (DeathDrop.getInstance().getItemManager().exist(itemKey)) {
            p.sendMessage(Messages.itemKeyExist(itemKey));
            return false;
        }

        ItemStack is = null;
        if (Bukkit.getVersion().contains("1.8"))
            is = p.getItemInHand();
        else
            is = p.getInventory().getItemInMainHand();

        if (is == null || is.getType() == Material.AIR) {
            p.sendMessage(Messages.invalidItem());
            return false;
        }

        DeathDrop.getInstance().getItemManager().add(itemKey, is);

        YamlConfiguration c = Configs.ITEMS.getConfig();
        c.set(itemKey, is);
        Configs.ITEMS.save(c);

        p.sendMessage(Messages.itemAdded(itemKey));

        return true;
    }

}
