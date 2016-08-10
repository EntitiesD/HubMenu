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

import hubmenu.creditsSTG.CreditsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import hubmenu.files.Cooldowns;

public class JoinEvent {
	
	int reward = 5;
	int cooltime = 86400;
	
	@EventHandler 
	public void onJoin(PlayerJoinEvent e){
		final Player p = e.getPlayer();
		if(Cooldowns.tryCooldown(p, "GiveCredits", cooltime)){
			CreditsAPI.getManager().addCredits(p.getName(), reward);
			p.sendMessage(ChatColor.GREEN + "You get " + ChatColor.RED + reward + ChatColor.GREEN  + " creditsSTG for joining the server.");
		}else{
			p.sendMessage(ChatColor.RED + "Wait 24 hours before getting a daily reward: " + ChatColor.GOLD + reward + ChatColor.RED + " creditsSTG.");
		}
		
		if(e.getPlayer().hasPlayedBefore()) {
			Bukkit.broadcastMessage("Welcome Back");
		} else {
			int count = Bukkit.getServer().getOfflinePlayers().length + 1;
		      Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3" + e.getPlayer().getName() + "&5Is User # " + count + " &9That has Logged On To The Server!"));
		}
		
		p.getInventory().clear();
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 21470));
		
		giveItems(p);
	}
	
public static void giveItems(Player p) {
		//Actual Compass Item
		ItemStack ServerCompass = new ItemStack(Material.COMPASS, 1);
		ItemMeta ServerCompassmeta = ServerCompass.getItemMeta();
		ServerCompassmeta.setDisplayName("§bServer Selector");
		ServerCompass.setItemMeta(ServerCompassmeta);
		p.getInventory().setItem(5, ServerCompass);
		
		ItemStack ServerAccessories = new ItemStack(Material.SLIME_BALL, 1);
		ItemMeta ServerAccessoriesmeta = ServerAccessories.getItemMeta();
		ServerAccessoriesmeta.setDisplayName("§bAccessories");
		ServerAccessories.setItemMeta(ServerAccessoriesmeta);
		p.getInventory().setItem(1, ServerAccessories);
		
		ItemStack ServerViewer = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		ItemMeta ServerViewermeta = ServerViewer.getItemMeta();
		ServerViewermeta.setDisplayName("§bPlayer Viewer");
		ServerViewer.setItemMeta(ServerViewermeta);
		p.getInventory().setItem(9, ServerViewer);
	}
	
	public ItemStack createItem(Material m, String name, int amt) {
		ItemStack item = new ItemStack(m, amt);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		
		return item;
	}
}
