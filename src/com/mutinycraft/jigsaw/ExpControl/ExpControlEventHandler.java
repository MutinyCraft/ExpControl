package com.mutinycraft.jigsaw.ExpControl;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;

/**
 * User: Jigsaw
 */
public class ExpControlEventHandler implements Listener {

	private ExpControl plugin;

	public ExpControlEventHandler(ExpControl p) {
		this.plugin = p;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeath(EntityDeathEvent e) {
		String worldName = e.getEntity().getWorld().getName();
		if (plugin.isBlockedWorld(worldName)) {
			e.setDroppedExp(0);
		} else if (worldName.equals(plugin.getNetherName())) {
			switch (e.getEntityType()) {
			case CREEPER:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case GHAST:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case PIG_ZOMBIE:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case ENDERMAN:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case BLAZE:
				e.setDroppedExp(0);
				break;
			case MAGMA_CUBE:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case WITHER:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case PLAYER:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			default:
				e.setDroppedExp(0);
				break;
			}
		} else {
			switch (e.getEntityType()) {
			case CREEPER:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case GHAST:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case PIG_ZOMBIE:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case ENDERMAN:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case BLAZE:
				e.setDroppedExp(0);
				break;
			case MAGMA_CUBE:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case WITHER:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			case PLAYER:
				e.setDroppedExp(e.getDroppedExp() * 2);
				break;
			default:
				break;
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityPortalExit(EntityPortalExitEvent e) {
		if(e.getEntityType() == EntityType.PIG_ZOMBIE){
			e.getEntity().remove();		
		}
	}

}
