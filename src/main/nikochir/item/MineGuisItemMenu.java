/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItem;
import nikochir.menu.MineGuisMenu;
/** javkit **/
import java.util.List;
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.Sound;
/* typedef */
/*
 * MineGuisItemMenu class
 * > Description;
 * -> Switches to another menu;
 * -> Has it's own reference to another menu;
*/
public class MineGuisItemMenu extends MineGuisItem {
    /* members */
    private String strMenuTitle;
    /* codetor */
    public MineGuisItemMenu() { this(MineGuis.get().getConfigStr("nameof_main")); }
    public MineGuisItemMenu(MineGuisMenu objMenu) { this(objMenu.getTitle()); }
    public MineGuisItemMenu(String strMenuTitle) {
        super(Material.FILLED_MAP, strMenuTitle, "switch to the " + strMenuTitle + " menu");
        this.strMenuTitle = strMenuTitle;
    }
    /* getters */
    public String getMenuTitle()  { return this.strMenuTitle; }
    public MineGuisMenu getMenu() { return MineGuis.get().getMenu(this.getMenuTitle()); }
    /* onevent */
    @EventHandler @Override
    public void onClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
            MineGuis.get().doLog("switch to " + getMenuTitle() + "has been executed!");
            objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            objPlayer.closeInventory();
            if (this.getMenu().doShow(objPlayer) == false) {
                MineGuis.get().doLog("failed to show the menu!"); return;
            }
        } else { /* not player click */
            return;
        }
    }
}
/* end_of_file */