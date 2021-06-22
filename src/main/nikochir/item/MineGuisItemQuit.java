/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItem;
/** javkit **/
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.Sound;
/* typedef */
/*
 * MineGuisItemQuit class
 * > Description;
 * -> ;
*/
public class MineGuisItemQuit extends MineGuisItem {
    /* members */
    /* codetor */
    public MineGuisItemQuit() {
        //super(Material.DARK_OAK_DOOR, "Quit", "Close current menu");
        //super(Material.ENDER_EYE, "Quit", "Close current menu");
        super(Material.CRIMSON_DOOR, "Quit", "Close current menu");
    }
    /* onevent */
    @EventHandler @Override
    public void onClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
            MineGuis.get().doLog("quit has been executed!");
            objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            objPlayer.closeInventory();
        } else { /* not player click */
            return;
        }
    }
}
/* end_of_file */