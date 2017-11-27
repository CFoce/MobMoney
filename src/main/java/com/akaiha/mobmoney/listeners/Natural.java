package com.akaiha.mobmoney.listeners;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.akaiha.mobmoney.MobMoney;

public class Natural implements Listener {
	
	private HashSet<UUID> mobs;
	
	public Natural(MobMoney plugin) {
		this.mobs = plugin.mobs;
	}
  
	@EventHandler(priority = EventPriority.LOW)
	public void spawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.NATURAL 
				|| e.getSpawnReason() == SpawnReason.BUILD_IRONGOLEM
				|| e.getSpawnReason() == SpawnReason.SILVERFISH_BLOCK
				|| e.getSpawnReason() == SpawnReason.CURED
				|| e.getSpawnReason() == SpawnReason.LIGHTNING
				|| e.getSpawnReason() == SpawnReason.JOCKEY
				|| e.getSpawnReason() == SpawnReason.CHUNK_GEN
				|| e.getSpawnReason() == SpawnReason.MOUNT
				|| e.getSpawnReason() == SpawnReason.BUILD_WITHER
				|| e.getSpawnReason() == SpawnReason.REINFORCEMENTS
				|| e.getSpawnReason() == SpawnReason.VILLAGE_DEFENSE
				|| e.getSpawnReason() == SpawnReason.VILLAGE_INVASION) {
			mobs.add(e.getEntity().getUniqueId());
		}
	}
}