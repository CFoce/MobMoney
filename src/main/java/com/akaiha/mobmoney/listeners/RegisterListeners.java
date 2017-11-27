package com.akaiha.mobmoney.listeners;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;

import com.akaiha.mobmoney.MobMoney;

public class RegisterListeners {
	
	public RegisterListeners(MobMoney plugin) {
		PluginManager manager = plugin.getServer().getPluginManager();
		FileConfiguration config = plugin.getConfig();
		Logger log = plugin.getLogger();
        
        if (config.getBoolean("Enable.Egg")) {
        	log.info("Egg Spawning Enabled!");
        	manager.registerEvents(new Egg(plugin), plugin);
        }
        if (config.getBoolean("Enable.Breeding")) {
        	log.info("Breeding Enabled!");
        	manager.registerEvents(new Breeding(plugin), plugin);
        }
        if (config.getBoolean("Enable.Spawner")) {
        	log.info("Spawner Spawning Enabled!");
        	manager.registerEvents(new Spawner(plugin), plugin);
        }
        if (config.getBoolean("Enable.Slime-Split")) {
        	log.info("Slime Splitting Enabled!");
        	manager.registerEvents(new SlimeSplit(plugin), plugin);
        }
        if (config.getBoolean("Enable.Natural")) {
        	log.info("Natural Spawning Enabled!");
        	manager.registerEvents(new Natural(plugin), plugin);
        }
        if (config.getBoolean("Enable.Custom")) {
        	log.info("Custom Spawning Enabled!");
        	manager.registerEvents(new Custom(plugin), plugin);
        }
        if (config.getBoolean("Worlds-Disable")) {
        	log.info("World Disabler Enabled!");
        	manager.registerEvents(new WorldListener(plugin), plugin);
        }
        if (config.getBoolean("Enable.Mobs")) {
        	log.info("Mob Killing Enabled!");
        	manager.registerEvents(new MobKill(plugin), plugin);
        }
        if (config.getBoolean("Enable.Player")) {
        	log.info("Player Killing Enabled!");
        	manager.registerEvents(new KilledPlayer(plugin), plugin);
        }
	}
}