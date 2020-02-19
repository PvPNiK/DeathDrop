package me.PvPNiK.deathDrop.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Cmd {

    private String perm = "";
    private boolean opUse = true;
    private boolean consoleUse = true;

    public abstract boolean execute(CommandSender sender, String[] args);

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public boolean isOpUse() {
        return opUse;
    }

    public void setOpUse(boolean opUse) {
        this.opUse = opUse;
    }

    public boolean isConsoleUse() {
        return consoleUse;
    }

    public void setConsoleUse(boolean consoleUse) {
        this.consoleUse = consoleUse;
    }

    public boolean canExecuteCmd(CommandSender sender) {
        if ((!(sender instanceof Player)) && isConsoleUse()) {
            return true;
        }

        if (sender.isOp() && isOpUse()) {
            return true;
        }

        if (!perm.isEmpty()) {
            if (sender.hasPermission(perm)) {
                return true;
            }
        }

        return false;
    }


}
