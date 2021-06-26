/* [nikodim]: 2021/05/06; */
/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItem;
import nikochir.menu.MineGuisMenuPage;
/* bukkit */
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.Sound;
/* typedef */
/*
* MineGuisItemPage
* > Description:
* -> element for page switch;
*/
public abstract class MineGuisItemPage extends MineGuisItem {
    /* members */
    private MineGuisMenuPage objPage;
    /* codetor */
    public MineGuisItemPage(MineGuisMenuPage objPage, Material valType, String strName, String strLore) {
        super(valType, strName, strLore);
        this.objPage = objPage;
    }
    /* getters */
    public MineGuisMenuPage getPage() { return this.objPage; }
    /* setters */
    /* vetters */
    /* actions */
    public abstract Boolean doSwitch(Player objPlayer);
    /* handles */
    @EventHandler @Override
    public void onClick(InventoryClickEvent objEvent) {
        if (objEvent.getWhoClicked() instanceof Player) {
            Player objPlayer = (Player) objEvent.getWhoClicked();
            objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            if (this.doSwitch(objPlayer) == false) { /* failed to switch! */ return; }
        } else { /* not player click */ return; }
    }
}
/* end_of_file */