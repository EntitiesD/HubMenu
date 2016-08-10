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

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreditsCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 if (sender instanceof Player) {
	            if (cmd.getName().equalsIgnoreCase("creditsSTG")) {
	                Player player = (Player) sender;
	                if (player.hasPermission("hub.creditsSTG.command")) {
	                    if (args.length == 0) {
	                        player.sendMessage("/creditsSTG - " + "Displays this screen");
	                        player.sendMessage("/creditsSTG add [player] [amount] - " + "Give creditsSTG");
	                        player.sendMessage("/creditsSTG set [player] [amount] - " +  "Sets specific amount of creditsSTG");
	                        player.sendMessage("/creditsSTG remove [player] [amount] - " + "Removes creditsSTG");
	                        player.sendMessage("/creditsSTG reset [player] [amount] - " + "Resets creditsSTG");
	                    } else if (args.length == 3) {
	                        if (args[0].equalsIgnoreCase("add")) {
	                            Player target = Bukkit.getPlayerExact(args[1]);
	                            if (target == null) {
	                                player.sendMessage("§4" + args[1] + " §7is offline!");
	                            } else {
	                                if (NumberUtils.isNumber(args[2])) {
	                                    int amount = Integer.parseInt(args[2]);
	                                    CreditsAPI.getManager().addCredits(target.getName(), amount);
	                                    player.sendMessage("§7You have added §2" + args[2] + "§7 creditsSTG to §2" + args[1] + "'s §7account.");
	                                } else {
	                                    player.sendMessage("§4" + args[2] + " §7is not a number!");
	                                }
	                            }
	                        }
	                    if(args[0].equalsIgnoreCase("remove")){
	                    		 Player target1 = Bukkit.getPlayerExact(args[1]);
		                            if (target1 == null) {
		                                player.sendMessage("§4" + args[1] + " §7is offline!");
		                            } else {
		                                if (NumberUtils.isNumber(args[2])) {
		                                    int amount = Integer.parseInt(args[2]);
		                                    CreditsAPI.getManager().removeCredits(target1.getName(), amount);
		                                    player.sendMessage("§7You have removed §2" + args[2] + "§7 creditsSTG from §2" + args[1] + "'s §7account.");
		                                } else {
		                                    player.sendMessage("§4" + args[2] + " §7is not a number!");
		                                }
		                            }
		                       }
	                    if(args[0].equalsIgnoreCase("set")){
	                    	Player target2 = Bukkit.getPlayerExact(args[1]);
	                    	if(NumberUtils.isNumber(args[2])){
	                    		int amount = Integer.parseInt(args[2]);
	                    		CreditsAPI.getManager().setCredits(target2.getName(), amount);
                                 player.sendMessage("§7You have set §2" + args[2] + "§7 creditsSTG to §2" + args[1] + "'s §7account.");
                             } else {
                                 player.sendMessage("§4" + args[2] + " §7is not a number!");
	                    	}
	                    }
	                    	 if(args[0].equalsIgnoreCase("reset")){
	 	                    	Player target3 = Bukkit.getPlayerExact(args[1]);
	 	                    		int amount = 0;
	 	                    		CreditsAPI.getManager().setCredits(target3.getName(), amount);
	                                  player.sendMessage("§7You have reset " + "%PLAYER%" + " creditsSTG");
	                              }
	                    }
	                }
	                else {
	                	
	                	  player.sendMessage("You do not have permission to use this command!");
	                }
	            }
		 }
		return false;
	}
}
