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
 * MineGuisItemCell class
 * > Description;
 * -> Does nothing, just fills empty space;
*/
public class MineGuisItemCell extends MineGuisItem {
    /* members */
    /* codetor */
    public MineGuisItemCell() {
        super(Material.BLACK_STAINED_GLASS_PANE, "", "");
    }
    /* onevent */
    @EventHandler @Override
    public void onClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
            MineGuis.get().doLog("cell has been clicked!");
            objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        } else { /* not player click */
            return;
        }
    }
}
/* end_of_file */