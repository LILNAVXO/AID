package me.ophiuchus;

import me.ophiuchus.commands.AIDcommand;
import me.ophiuchus.commands.staffchat;
import me.ophiuchus.commands.vanish;
import me.ophiuchus.commands.warnCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AID extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("[AID has initiated]");
        getCommand("aid").setExecutor(new AIDcommand(this));
        getCommand("vanish").setExecutor(new vanish());
        getCommand("staffchat").setExecutor(new staffchat());
        getCommand("warn").setExecutor(new warnCommand(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        System.out.println("[AID functions have ceased]");
    }
}
