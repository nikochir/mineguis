/* [nikodim]: 2021/05/06; */
/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItem;
import nikochir.menu.MineGuisMenuPage;
import nikochir.menu.MineGuisMenuBook;
/* bukkit */
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.Sound;
/* typedef */
/* MineGuisItemPage
* > Description:
* -> element for page switch;
*/
public abstract class MineGuisItemPage extends MineGuisItem {
    /* members */
    private final MineGuisMenuBook objBook;
    /* codetor */
    public MineGuisItemPage(MineGuisMenuBook objBook, Material valMaterial, String strName, String strLore) {
        super(valMaterial, strName, strLore);
        this.objBook = objBook;
    }
    /* getters */
    public MineGuisMenuBook getBook() { return this.objBook; }
    /* setters */
    /* vetters */
    /* actions */
    public abstract Boolean doSwitch(Player objPlayer);
    /* handles */
    
    @Override
    public void onClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            MineGuis.get().doLog("this is not a player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        if (this.doSwitch(objPlayer) == false) { 
            MineGuis.get().doLog("failed book switch!");
            return;
        }
    }
}
/* end_of_file */