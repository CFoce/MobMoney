package com.akaiha.mobmoney;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.akaiha.mobmoney.listeners.RegisterListeners;
import com.akaiha.mobmoney.util.ConfigVersion;
import com.akaiha.mobmoney.util.EntityInfo;

import net.milkbowl.vault.economy.Economy;

public class MobMoney extends JavaPlugin {
	
	public Economy economy;
	private int version = 1;
	public HashSet<UUID> mobs;
	public EntityInfo ei;
	  

	public void onEnable() {
		try {
			getLogger().info("Enabling!");
			mobs = new HashSet<UUID>();
	        new ConfigVersion().configVersionCheck(this, version);
	        setupEconomy();
	        ei = new EntityInfo(this);
	        new RegisterListeners(this);
	        getConfig().options().copyDefaults(true);
	        saveDefaultConfig();
	    } catch (NullPointerException e) {
	        getLogger().warning("Something Went Wrong!");
	        getServer().getPluginManager().disablePlugin(this);
	        getLogger().warning("Disabling!");
	    }
	}
	  
	public void onDisable() {
		  
	}
	  

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
			if (economy != null) {
				getLogger().info("Vault Economy Available.");
				return true;
			}
		}
		getLogger().warning("Vault Economy Unavailable.");
		getServer().getPluginManager().disablePlugin(this);
		return false;
	}
}