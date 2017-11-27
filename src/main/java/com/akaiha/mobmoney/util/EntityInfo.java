package com.akaiha.mobmoney.util;

import java.util.EnumMap;

import org.bukkit.entity.EntityType;

import com.akaiha.mobmoney.MobMoney;

public class EntityInfo {
	
	private EnumMap<EntityType,Object[]> entity;
	private KillAmount ka;
	
	public EntityInfo(MobMoney plugin) {
		this.ka = new KillAmount(plugin);
		this.entity = new EnumMap<EntityType,Object[]>(EntityType.class);
		entity.put(EntityType.BAT, init("Bat"));
		entity.put(EntityType.BLAZE, init("Blaze"));
		entity.put(EntityType.CAVE_SPIDER, init("Cave Spider"));
		entity.put(EntityType.CHICKEN, init("Chicken"));
		entity.put(EntityType.COW, init("Cow"));
		entity.put(EntityType.CREEPER, init("Creeper"));
		entity.put(EntityType.ENDER_DRAGON, init("Ender Dragon"));
		entity.put(EntityType.ENDERMAN, init("Enderman"));
		entity.put(EntityType.ENDERMITE, init("Endermite"));
		entity.put(EntityType.GHAST, init("Ghast"));
		entity.put(EntityType.GIANT, init("Giant"));
		entity.put(EntityType.GUARDIAN, init("Guardian"));
		entity.put(EntityType.HORSE, init("Horse"));
		entity.put(EntityType.IRON_GOLEM, init("Iron Golem"));
		entity.put(EntityType.MAGMA_CUBE, init("Magma Cube"));
		entity.put(EntityType.MUSHROOM_COW, init("Mushroom Cow"));
		entity.put(EntityType.OCELOT, init("Ocelot"));
		entity.put(EntityType.PIG, init("Pig"));
		entity.put(EntityType.PIG_ZOMBIE, init("Zombie Pigman"));
		entity.put(EntityType.RABBIT, init("Rabbit"));
		entity.put(EntityType.SHEEP, init("Sheep"));
		entity.put(EntityType.SILVERFISH, init("Silver Fish"));
		entity.put(EntityType.SKELETON, init("Skeleton"));
		entity.put(EntityType.SLIME, init("Slime"));
		entity.put(EntityType.SNOWMAN, init("Snowman"));
		entity.put(EntityType.SPIDER, init("Spider"));
		entity.put(EntityType.SQUID, init("Squid"));
		entity.put(EntityType.VILLAGER, init("Villager"));
		entity.put(EntityType.WITCH, init("Witch"));
		entity.put(EntityType.WITHER, init("Wither"));
		entity.put(EntityType.WOLF, init("Wolf"));
		entity.put(EntityType.ZOMBIE, init("Zombie"));
	}
	
	public Object[] getInfo(EntityType e) {
		if (entity.containsKey(e)) {
			return entity.get(e);
		}
		return null;
	}
	
	private Object[] init(String m) {
		Object[] obj = new Object[3];
		Double[] arr = ka.getMoney(m);
		obj[0] = m;
		obj[1] = arr[0];
		obj[2] = arr[1];
		return obj;
	}
}