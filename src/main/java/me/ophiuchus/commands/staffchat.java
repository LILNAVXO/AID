package me.ophiuchus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.Length;

import java.util.Collection;

public class staffchat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player staff = (Player) commandSender;
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if(staff.hasPermission("aid.staff")){
                if (player.hasPermission("aid.staff")){
                    if(args.length == 0){
                        staff.sendMessage(ChatColor.WHITE+"["+ChatColor.GOLD+"AID"+ChatColor.WHITE+"] "+ChatColor.AQUA+"Command incomplete");
                        staff.sendMessage(ChatColor.WHITE+"["+ChatColor.GOLD+"AID"+ChatColor.WHITE+"] "+ChatColor.AQUA+"Example : /staffchat <message>");
                    } else if (args.length == 1){
                        String word = args[0];
                        player.sendMessage(ChatColor.WHITE+"["+ChatColor.GOLD+"AID"+ChatColor.WHITE+"] "+ChatColor.RED+staff.getDisplayName()+ChatColor.RED+" : "+ChatColor.RED+word);
                    }else{
                        StringBuilder builder = new StringBuilder();
                        for(int i = 0; i < args.length; i++){
                            builder.append(args[i]);
                            builder.append(" ");
                        }
                        String sentence = builder.toString();
                        sentence = sentence.stripTrailing();
                        player.sendMessage(ChatColor.WHITE+"["+ChatColor.GOLD+"AID"+ChatColor.WHITE+"] "+ChatColor.RED+staff.getDisplayName()+ChatColor.RED+" : "+ChatColor.RED+sentence);
                    }
                }
            }else{
                staff.sendMessage(ChatColor.WHITE+"["+ChatColor.GOLD+"AID"+ChatColor.WHITE+"] "+"You do not have permission to use this command");
            }
        }
        return true;
    }
}
