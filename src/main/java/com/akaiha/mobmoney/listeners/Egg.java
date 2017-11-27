package com.akaiha.mobmoney.listeners;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.akaiha.mobmoney.MobMoney;

public class Egg implements Listener {
	
	private HashSet<UUID> mobs;
	
	public Egg(MobMoney plugin) {
		this.mobs = plugin.mobs;
	}
  
	@EventHandler(priority = EventPriority.LOW)
	public void spawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.SPAWNER_EGG
				|| e.getSpawnReason() == SpawnReason.BUILD_SNOWMAN
				|| e.getSpawnReason() == SpawnReason.DISPENSE_EGG) {
			mobs.add(e.getEntity().getUniqueId());
		}
	}
}