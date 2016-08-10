package hubmenu.creditsSTG;

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

import java.util.HashMap;

import org.bukkit.scheduler.BukkitRunnable;

import hubmenu.Main;

public class CreditsAPI {
	
	private static CreditsAPI Credits = new CreditsAPI();
	
	
	public static CreditsAPI getManager(){
		return Credits;
	}
	
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	 
	 
	 public int getCredits(String name){
		if (map.containsKey(name)) {
            return map.get(name);
        } else {
            return 0;
        }
		
	 }
	 
	 public void addCredits(String name, int amount_to_add) {
	        if (map.containsKey(name)) {
	            map.put(name, map.get(name) + amount_to_add);
	            Main.getPlugin().getConfig().set(name, getCredits(name));
	        } else {
	            map.put(name, amount_to_add);
	            Main.getPlugin().getConfig().set(name, getCredits(name));
	        }
	      
	 }
	 
	 public void setCredits(String name, int amount) {
		 map.put(name,  amount);
		 Main.getPlugin().getConfig().set(name, getCredits(name));
		 
	} 
	 
	 public boolean hasCredits(String name) {
        return map.containsKey(name);
    }

    public void removeCredits(String name, int cost) {
        map.put(name, getCredits(name) - cost);
        Main.getPlugin().getConfig().set(name, getCredits(name));
        
    }
    
	 
	public void loadConfig() {
		for(String keys : Main.getPlugin().getConfig().getKeys(false)){
            int amount = Main.getPlugin().getConfig().getInt(keys);
            map.put(keys, amount);
        }
		System.out.print("[Main] Loaded Credits Configuration File!");
	
	}

    public void startSaveTask(){
        new BukkitRunnable(){
            public void run(){
            	Main.getPlugin().saveConfig();
            }
        }.runTaskTimer(Main.getPlugin(), 20*5, 20);
    }
	



	

}