/* package */
package nikochir.listen;
/* include */
import nikochir.execut.*;
/** bukkit **/
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
/* typedef */
/*
 * MineGuisListener class
 * > Description:
 * -> ;
*/
public class MineGuisListen implements Listener {
    /* onevent */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
        } else { /* not player click */
            return;
        }
    }
}
/* end_of_file */