/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.MineGuisUser;
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
    /* codetor */
    public MineGuisItemBack() { super(Material.COMPASS, "Back", "Return to the most recent menu"); }
    /* getters */
    public MineGuisMenu getMenu(Player objPlayer)     { return MineGuis.get().getUser(objPlayer).getMenuLast(); }
    public MineGuisMenu getMenu(MineGuisUser objUser) { return objUser.getMenuLast(); }
    /* vetters */
    public Boolean vetMenu(Player objPlayer)     { return MineGuis.get().getUser(objPlayer).vetMenuLast(); }
    public Boolean vetMenu(MineGuisUser objUser) { return objUser.vetMenuLast(); }
    /* handles */
    @Override
    public void onClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            MineGuis.get().doLog("not player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        if (this.vetMenu(objPlayer) == null) {
            MineGuis.get().doLog("back menu is null!");
            return;
        }
        if (this.getMenu(objPlayer).doShow(objPlayer) == false) {
            MineGuis.get().doLog("failed menu show!");
            return;
        }
    }
}
/* end_of_file */