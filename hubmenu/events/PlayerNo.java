package hubmenu.events;

/*
 * The MIT License
 *
 * Copyright 2016 BadEntities.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerNo implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onMove(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (!p.hasPermission("hub.admin")) {
			if (event.getInventory().getHolder() == p) {
				event.setCancelled(true);
				event.setCursor(new ItemStack(Material.AIR));
				event.getWhoClicked().closeInventory();
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event){
		Player p = event.getPlayer();
		if(!p.hasPermission("hub.admin")){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPickUp(PlayerPickupItemEvent event){
		Player p = event.getPlayer();
		if(!p.hasPermission("hub.admin")){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e){
		if(e.getSpawnReason() == SpawnReason.CUSTOM)
			return;
		
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e){
		e.setFoodLevel(20);
		e.setCancelled(true);
	}
	
}