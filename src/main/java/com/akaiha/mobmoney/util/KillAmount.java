package com.akaiha.mobmoney.util;

import org.bukkit.configuration.file.FileConfiguration;

import com.akaiha.mobmoney.MobMoney;

public class KillAmount {
  
	private FileConfiguration config;
	private Boolean group;
	private Double[] friendly = new Double[2];
	private Double[] agressive = new Double[2];
	private Double[] bosses = new Double[2];
	private String friendlyStr;
	private String agressiveStr;
	private String bossesStr;
	
	public KillAmount(MobMoney plugin) {
		this.config = plugin.getConfig();
		this.group = config.getBoolean("Mobs.Group-Setting.Enabled");
		this.friendlyStr = "Mobs.Friendly-Mobs.";
		this.agressiveStr = "Mobs.Agressive-Mobs.";
		this.bossesStr = "Mobs.Bosses.";
		this.friendly[0] = config.getDouble("Mobs.Group-Settings.Friendly-Mobs.min");
		this.agressive[0] = config.getDouble("Mobs.Group-Settings.Agressive-Mobs.min");
		this.bosses[0] = config.getDouble("Mobs.Group-Settings.Bosses.min");
		this.friendly[1] = config.getDouble("Mobs.Group-Settings.Friendly-Mobs.max");
		this.agressive[1] = config.getDouble("Mobs.Group-Settings.Agressive-Mobs.max");
		this.bosses[1] = config.getDouble("Mobs.Group-Settings.Bosses.max");
	}
	
    public Double[] getMoney(String m) {
    	if (group) {
    		if (config.contains(friendlyStr + m)) {
    			return friendly;
    		} else if (config.contains(agressiveStr + m)) {
    			return agressive;
    		} else if (config.contains(bossesStr + m)) {
    			return bosses;
    		}
    	} else {
    		Double[] result = new Double[2];
    		if (config.contains(friendlyStr + m)) {
    			result[0] = config.getDouble(friendlyStr + m + ".min");
    			result[1] = config.getDouble(friendlyStr + m + ".max");
    			return result;
    		} else if (config.contains(agressiveStr + m)) {
    			result[0] = config.getDouble(agressiveStr + m + ".min");
    			result[1] = config.getDouble(agressiveStr + m + ".max");
    			return result;
    		} else if (config.contains(bossesStr + m)) {
    			result[0] = config.getDouble(bossesStr + m + ".min");
    			result[1] = config.getDouble(bossesStr + m + ".max");
    			return result;
    		}
    	}
    	return new Double[] {0D,0D};
    }
    
    public Boolean contains(String m) {
    	if (config.contains(friendlyStr + m) || config.contains(agressiveStr + m) || config.contains(bossesStr + m)) {
			return true;
		}
    	return false;
    }
}