package me.PvPNiK.deathDrop.events;

import me.PvPNiK.deathDrop.DeathDrop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.PvPNiK.deathDrop.worlds.DeathDropWorld;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        String worldName = p.getWorld().getName();

        if (!DeathDrop.getInstance().getWorldManager().exist(worldName))
            return;

        DeathDropWorld ddw = DeathDrop.getInstance().getWorldManager().get(worldName);

        // adding items to drop
        for (String key : new HashSet<String>(ddw.getItemsToAdd()))
            if (DeathDrop.getInstance().getItemManager().exist(key))
                e.getDrops().add(DeathDrop.getInstance().getItemManager().get(key));
            else
                ddw.removeItemToAdd(key);

        // removing items from drops
        List<ItemStack> drops = e.getDrops();
        List<ItemStack> newDrops = new ArrayList<>();
        for (String key : new HashSet<String>(ddw.getItemsToRemove())) {
            if (DeathDrop.getInstance().getItemManager().exist(key)) {
                ItemStack is = DeathDrop.getInstance().getItemManager().get(key);
                newDrops = removeItem(drops, is);
                drops = newDrops;
            } else
                ddw.removeItemToRemove(key);
        }
        e.getDrops().clear();
        e.getDrops().addAll(newDrops);

    }

    private List<ItemStack> removeItem(List<ItemStack> drops, ItemStack item) {
        List<ItemStack> newDrops = new ArrayList<>();

        int itemAmount = item.getAmount();

        for (ItemStack is : drops) {
            if (itemAmount <= 0 || !is.isSimilar(item)) {
                newDrops.add(is);
                continue;
            }

            int isAmount = is.getAmount();
            if (itemAmount >= isAmount) {
                itemAmount = itemAmount - isAmount;
                continue;
            }

            is.setAmount(isAmount - itemAmount);
            newDrops.add(is);
            itemAmount = 0;
        }
        return newDrops;
    }

}
