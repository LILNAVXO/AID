package me.ophiuchus.commands;

import me.ophiuchus.AID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class warnCommand implements CommandExecutor {
    private final AID plugin;

    public warnCommand(AID plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player staff = (Player) commandSender;
        String reasonWord = args[1];
        String warn = this.plugin.getConfig().getString("warn-message");
        String warntitle = this.plugin.getConfig().getString("warn-title");
        {
            if (staff.hasPermission("aid.staff")) {
                if (args.length == 1) {
                    String playername = args[0];
                    Player target = Bukkit.getPlayerExact(playername);
                    staff.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "AID" + ChatColor.WHITE + "] " + ChatColor.AQUA + "Command Incomplete");
                    staff.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "AID" + ChatColor.WHITE + "] " + ChatColor.AQUA + "Example : /warn <playername> <reason>");
                } else if (args.length == 2) {
                    String playername = args[0];
                    Player target = Bukkit.getPlayerExact(playername);
                    warn = warn.replace("$target_name$",target.getDisplayName());
                    warn = warn.replace("$player_name$",staff.getDisplayName());
                    Collection<? extends Player> onlineplayers = Bukkit.getOnlinePlayers();
                    for(Player player : onlineplayers)
                        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "AID" + ChatColor.WHITE + "] " + ChatColor.translateAlternateColorCodes('&', warn) + reasonWord);
                        target.sendTitle(ChatColor.translateAlternateColorCodes('&',warntitle),ChatColor.WHITE+"By "+staff.getDisplayName());
                } else {
                    String playername = args[0];
                    Player target = Bukkit.getPlayerExact(playername);
                    warn = warn.replace("$target_name$",target.getDisplayName());
                    warn = warn.replace("$player_name$",staff.getDisplayName());
                    Collection<? extends Player> onlineplayers = Bukkit.getOnlinePlayers();
                    for(Player player : onlineplayers) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]);
                            builder.append(" ");
                        }
                        String reason = builder.toString();
                        reason = reason.stripTrailing();
                        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "AID" + ChatColor.WHITE + "] " + ChatColor.translateAlternateColorCodes('&', warn) + reason);
                        target.sendTitle(ChatColor.translateAlternateColorCodes('&',warntitle),ChatColor.WHITE+"By "+staff.getDisplayName());
                    }
                }
            }else{
                staff.sendMessage("you do not have permission to use this command");
            }
        }
        return true;
        }



   }

