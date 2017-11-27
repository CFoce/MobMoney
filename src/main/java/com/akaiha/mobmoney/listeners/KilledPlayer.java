package com.akaiha.mobmoney.listeners;

import java.util.SplittableRandom;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.akaiha.mobmoney.MobMoney;
import com.akaiha.mobmoney.util.*;

import net.milkbowl.vault.economy.Economy;

public class KilledPlayer implements Listener {
	
	private SplittableRandom rand;
	private FileConfiguration fc;
	private Format format;
	private Economy economy;
	private boolean kMessageEnabled;
	private boolean vMessageEnabled;
	private double kMax;
	private double kMin;
	private double vMax;
	private double vMin;
	private String vMessage;
	private String kMessage;
	private boolean minor;
	
	public KilledPlayer(MobMoney plugin) {
		this.fc = plugin.getConfig();
		this.format = new Format();
		this.economy = plugin.economy;
		this.rand = new SplittableRandom();
		this.kMessageEnabled = fc.getBoolean("Mobs.Player.Killer.Enabled");
		this.vMessageEnabled = fc.getBoolean("Mobs.Player.Victim.Enabled");
		this.kMax = fc.getDouble("Mobs.Player.Killer.max");
		this.kMin = fc.getDouble("Mobs.Player.Killer.min");
		this.kMax = fc.getDouble("Mobs.Player.Victim.max");
		this.kMin = fc.getDouble("Mobs.Player.Victim.min");
		this.kMessage = fc.getString("Mobs.Player.Killer.Message");
		this.vMessage = fc.getString("Mobs.Player.Victim.Message");
		this.minor = fc.getBoolean("Minor");
	}
  
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		Player k = e.getEntity().getKiller();
		if (k != null) {
			Player v = e.getEntity();
			double kMoney = 0.0;
			if (kMax != 0.0) {
				kMoney = rand.nextDouble(kMin, kMax);
			}
			double vMoney = 0.0;
			if (vMax != 0.0) {
				vMoney = rand.nextDouble(vMin, vMax);
			}
			double vHas = economy.getBalance(v);
			
			if (kMoney != 0.0) {
				economy.depositPlayer(k, kMoney);
			}
			if (vMoney != 0.0) {
				if (vMoney <= vHas) {
					economy.depositPlayer(v, vMoney);
				} else {
					vMoney = vHas;
					economy.depositPlayer(v, vMoney);
				}
			}
			
			if (kMessageEnabled) {
				String message = new String(kMessage);
				k.sendMessage(format.color(messageFormat(message, kMoney, k, v)));
			}
			if (vMessageEnabled) {
				String message = new String(vMessage);
				v.sendMessage(format.color(messageFormat(message, vMoney, k, v)));
			}
    	}
	}
	
	private String messageFormat(String message, double money, Player k, Player v) {
		message = format.replace(message, "%v%", v.getDisplayName());
    	message = format.replace(message, "%k%", k.getDisplayName());
    	if (minor) {
    		String[] arr = String.valueOf(Math.round(money * 100.0) / 100.0).split("\\.");
    		message = format.replace(message, "%M%", arr[0]);
    		message = format.replace(message, "%m%", arr[1]);
    	} else {
    		message = format.replace(message, "%M%", String.valueOf(Math.round(money * 100.0) / 100.0));
    	}
		return message;
	}
}
