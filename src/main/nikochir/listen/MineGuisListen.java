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
/* MineGuisListener class
 * description:
 * >;
*/
public class MineGuisListen implements Listener {
    /* codetor */
    /* getters */
    /* setters */
    /* vetters */
    /* command */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
            if (objEvent.getView().title().insertion().equalsIgnoreCase("mineguis")) {
                objEvent.setCancelled(true);
                System.out.println("mineguis is really powerful;");
            }
        } else {
            /* */
        }
    }
}
/* end_of_file */