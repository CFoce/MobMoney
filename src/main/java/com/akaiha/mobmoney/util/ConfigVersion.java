package com.akaiha.mobmoney.util;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;

import com.akaiha.mobmoney.MobMoney;

public class ConfigVersion {
	
	private File datafolder;
	private File config;
	private File newConfig;
	private Logger logger;
  
	public void configVersionCheck(MobMoney plugin, int version) {
		this.datafolder = plugin.getDataFolder();
		this.config = new File(this.datafolder, "config.yml");
		this.newConfig = new File(this.datafolder, "oldconfig.yml");
		this.logger = plugin.getLogger();
		int currentVersion = plugin.getConfig().getInt("Version");
		if (version != currentVersion) {
			plugin.saveDefaultConfig();
			logger.warning(ChatColor.RED + "Version of your config is: " + currentVersion);
			logger.warning(ChatColor.RED + "This plugin uses config version: " + version);
			logger.warning(ChatColor.RED + "Renaming 'config.yml' to 'oldconfig.yml'");
			if (this.newConfig.exists()) {
				this.newConfig.delete();
			}
			this.config.renameTo(this.newConfig);
			plugin.getConfig().options().copyDefaults();
		}
	}
}