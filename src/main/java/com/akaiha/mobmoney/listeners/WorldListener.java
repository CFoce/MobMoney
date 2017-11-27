package com.akaiha.mobmoney.listeners;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.akaiha.mobmoney.MobMoney;

public class WorldListener implements Listener {
	
	private HashSet<UUID> mobs;
	private List<String> worlds;
  
	public WorldListener(MobMoney plugin) {
		this.mobs = plugin.mobs;
		this.worlds = plugin.getConfig().getStringList("Worlds");
	}
  
  
	@EventHandler(priority = EventPriority.NORMAL)
	public void spawnEvent(CreatureSpawnEvent e) {
		World w = e.getLocation().getWorld();
		if (worlds.contains(w.getName())) {
			mobs.remove(e.getEntity().getUniqueId());
		}
	}
}