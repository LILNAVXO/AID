package me.ophiuchus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class vanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player staff = (Player) commandSender;
        if (staff.hasPermission("aid.staff")){
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            for (Player player : onlinePlayers) {
                if(player.canSee(staff)) {
                    player.hidePlayer(staff);
                }else{
                    player.showPlayer(staff);
                }
            }
        }else{
         staff.sendMessage(ChatColor.RED + "You do not have access to this command");
        }

        return true;
    }
}
