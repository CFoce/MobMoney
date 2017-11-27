package com.akaiha.mobmoney.listeners;

import java.util.HashSet;
import java.util.SplittableRandom;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.akaiha.mobmoney.MobMoney;
import com.akaiha.mobmoney.util.*;

import net.milkbowl.vault.economy.Economy;


public class MobKill implements Listener {
	
    private Boolean messageEnabled;
    private String message;
    private Format format;
    private HashSet<UUID> mobs;
    private EntityInfo ei;
    private Economy economy;
    private SplittableRandom rand;
    private boolean minor;
    
    public MobKill(MobMoney plugin) {
    	this.rand = new SplittableRandom();
    	this.mobs = plugin.mobs;
    	this.ei = plugin.ei;
    	this.economy = plugin.economy;
    	this.messageEnabled = plugin.getConfig().getBoolean("Message-Enabled");
    	this.message = plugin.getConfig().getString("Message");
    	this.minor = plugin.getConfig().getBoolean("Minor");
    	this.format = new Format();
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void playerKill(EntityDeathEvent event) {
        LivingEntity et = event.getEntity();
        if (mobs.contains(et.getUniqueId())) {
        	mobs.remove(et.getUniqueId());
        	Player p = et.getKiller();
        	if (p == null) {
        		return;
        	}
        	Object[] mobInfo = ei.getInfo(et.getType());
            if (mobInfo != null && (Double) mobInfo[2] != 0.0) {
            	double cash = 0.0;
                cash = rand.nextDouble((Double) mobInfo[1], (Double) mobInfo[2]);
                if (cash != 0.0) {
                	economy.depositPlayer(p,cash);
                    if (messageEnabled) {
                    	String m = new String(message);
                    	m = format.replace(m, "%e%", (String) mobInfo[0]);
                    	m = format.replace(m, "%p%", p.getDisplayName());
                    	if (minor) {
                    		String[] arr = String.valueOf(Math.round(cash * 100.0) / 100.0).split("\\.");
                    		m = format.replace(m, "%M%", arr[0]);
                    		m = format.replace(m, "%m%", arr[1]);
                    	} else {
                    		m = format.replace(m, "%M%", String.valueOf(Math.round(cash * 100.0) / 100.0));
                    	}
                        p.sendMessage(format.color(m));
                    }
                }
            }
        } 
    }
}