package hubmenu.files;

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

import org.bukkit.entity.Player;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
 
public class Cooldowns {
    private static Table<String, String, Long> cooldowns = HashBasedTable.create();
    
    public static long getCooldown(Player player, String key) {
        return calculateRemainder(cooldowns.get(player.getName(), key));
    }
    
    public static long setCooldown(Player player, String key, long delay) {
        return calculateRemainder(
                cooldowns.put(player.getName(), key, System.currentTimeMillis() + delay));
    }
    public static boolean tryCooldown(Player player, String key, long delay) {
        if (getCooldown(player, key) <= 0) {
            setCooldown(player, key, delay);
            return true;
        }
        return false;
    }
    public static void removeCooldowns(Player player) {
        cooldowns.row(player.getName()).clear();
    }
    
    private static long calculateRemainder(Long expireTime) {
        return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;
    }
}