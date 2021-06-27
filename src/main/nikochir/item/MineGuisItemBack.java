/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.menu.MineGuisMenu;
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
/* MineGuisItemBack class
 * > Description;
 * -> switch back to the previously viewed panel;
*/
public class MineGuisItemBack extends MineGuisItem {
    /* members */
    MineGuisMenu objMenu;
    MineGuisItemBack objBack;
    /* codetor */
    public MineGuisItemBack(MineGuisMenu objMenu) {
        super(Material.COMPASS, "Back", "Nowhere");
        this.objMenu = objMenu;
        this.objBack = null;
    }
    public MineGuisItemBack(MineGuisMenu objMenu, MineGuisItemBack objBack) {
        super(Material.COMPASS, "Back", objMenu.getTitle() + "\n" + objBack.getLore());
        this.objMenu = objMenu;
        this.objBack = objBack;
    }
    /* getters */
    public MineGuisMenu getMenu()     { return this.objMenu; }
    public MineGuisItemBack getBack() { return this.objBack; }
    /* handles */
    @Override
    public void onClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
            //MineGuis.get().doLog("back has been executed!");
            objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            if (objBack != null) {
                if (getMenu().doHide(objPlayer) == false) {
                    MineGuis.get().doLog("failed menu hide!");
                    objPlayer.closeInventory();
                }
                if (objBack.getMenu().doShow(objPlayer) == false) {
                    MineGuis.get().doLog("failed menu show!");
                    return;
                }
            }
        } else { /* not player click */
            return;
        }
    }
}
/* end_of_file */