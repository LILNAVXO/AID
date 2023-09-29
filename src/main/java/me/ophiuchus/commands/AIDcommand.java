package me.ophiuchus.commands;

import me.ophiuchus.AID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AIDcommand implements CommandExecutor {
    private final AID plugin;
    private final HashMap<UUID, Long> cooldown;

    public AIDcommand(AID plugin){
        this.plugin = plugin;
        this.cooldown = new HashMap<>();
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player sender = (Player) commandSender;
        long timeMillis = System.currentTimeMillis();
        long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
        long timeMinutes = TimeUnit.SECONDS.toMinutes(timeSeconds);
        String title = this.plugin.getConfig().getString("aid-title");
        String subtitle = this.plugin.getConfig().getString("aid-subtitle");
        String message = this.plugin.getConfig().getString("aid-message");
        int cooldownC = this.plugin.getConfig().getInt("aid-cooldown");
        plugin.saveConfig();
        if(!this.cooldown.containsKey(sender.getUniqueId())){
            this.cooldown.put(sender.getUniqueId(), timeMinutes);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            for (Player player : onlinePlayers) {
                title = title.replace("$player_name$",sender.getDisplayName());
                if (!player.hasPermission("aid.staff")) {
                } else {
                    player.sendTitle(ChatColor.translateAlternateColorCodes('&',title),ChatColor.translateAlternateColorCodes('&',subtitle));
                    player.playSound(player.getPlayer(), Sound.BLOCK_ANVIL_PLACE,1,1);
                }
            }
        }else{
            long timeElapsed = timeMinutes - cooldown.get(sender.getUniqueId());
            //Time is in minutes
            if(timeElapsed >= cooldownC){
                this.cooldown.put(sender.getUniqueId(), timeMinutes);

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                for (Player player : onlinePlayers) {
                    title = title.replace("$player_name$",sender.getDisplayName());
                    if (!player.hasPermission("aid.alert")) {
                    } else {
                        player.sendTitle(ChatColor.translateAlternateColorCodes('&',title),ChatColor.translateAlternateColorCodes('&',subtitle));
                        player.playSound(player.getPlayer(), Sound.ENTITY_BLAZE_SHOOT,1,1);
                    }
                }
            }else{
                sender.sendMessage(ChatColor.GOLD + "This command is on cooldown for"+ " " + ( cooldownC - timeElapsed) + " " + "Minutes");
            }
        }
        return true;
    }
}









