/* package */

package main.mineguis.listen;

/* include */

import main.mineguis.Main;
import main.mineguis.kernel.User;
import main.mineguis.kernel.Item;
import main.mineguis.kernel.Menu;

/** bukkit **/

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

import org.bukkit.Sound;

/* typedef */
/* ListenPlayer class
 * > Description:
 * -> ;
*/
public class ListenPlayer implements Listener {

    /* handles */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {

        if (User.setUser(objEvent.getPlayer())) { Main.get().doLogO("the user has been added!"); }
        else { Main.get().doLogO("failed to create the user!"); return; }

    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {

        //User objUser = User.getUser(objEvent.getPlayer());

    }
    
    @EventHandler
    public void onPlayerWork(PlayerInteractEvent objEvent) {
        
        if (User.vetUser(objEvent.getPlayer()) == false) {
            Main.get().doLogO("failed to find the user!");
            return;
        }

        //User objUser = User.getUser(objEvent.getPlayer());

    }
}
/* endfile */