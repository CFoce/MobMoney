package com.akaiha.mobmoney.listeners;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.akaiha.mobmoney.MobMoney;

public class SlimeSplit implements Listener {
	
	private HashSet<UUID> mobs;
	
	public SlimeSplit(MobMoney plugin) {
		this.mobs = plugin.mobs;
	}
  
	@EventHandler(priority = EventPriority.LOW)
	public void spawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT) {
			mobs.add(e.getEntity().getUniqueId());
		}
	}
}