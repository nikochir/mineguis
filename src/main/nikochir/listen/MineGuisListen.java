/* package */
package nikochir.listen;
/* include */
import nikochir.MineGuis;
import nikochir.execut.*;
/** bukkit **/
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;
/* typedef */
/*
 * MineGuisListener class
 * > Description:
 * -> ;
*/
public class MineGuisListen implements Listener {
    /* handles */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
        } else { /* not player click */
            return;
        }
    }
    @EventHandler
    public void onInventoryShow(InventoryOpenEvent objEvent) {
    }
    @EventHandler
    public void onInventoryHide(InventoryCloseEvent objEvent) {
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {
        MineGuis.get().setUser(objEvent.getPlayer());
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {
        MineGuis.get().setUser(null);
    }
}
/* end_of_file */