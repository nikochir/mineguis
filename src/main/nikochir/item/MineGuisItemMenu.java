/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItem;
import nikochir.item.MineGuisItemBack;
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
/* MineGuisItemMenu class
 * > Description;
 * -> Switches to another menu;
 * -> Has it's own reference to another menu;
*/
public class MineGuisItemMenu extends MineGuisItem {
    /* members */
    private MineGuisMenu objCurr;
    private MineGuisMenu objNext;
    /* codetor */
    public MineGuisItemMenu(MineGuisMenu objCurr, MineGuisMenu objNext) {
        super(Material.COMPASS, objNext.getTitle(),
            String.format("switch from %s to %s", objCurr.getTitle(), objNext.getTitle())
        );
        this.objCurr = objCurr;
        this.objNext = objNext;
    }
    public MineGuisItemMenu(String strCurr, String strNext) {
        this(MineGuis.get().getMenu(strCurr), MineGuis.get().getMenu(strNext));
    }
    /* getters */
    public String getCurrTitle()  { return this.objCurr.getTitle(); }
    public MineGuisMenu getCurr() { return this.objCurr; }
    public String getNextTitle()  { return this.objNext.getTitle(); }
    public MineGuisMenu getNext() { return this.objNext; }
    /* setters */
    /* vetters */
    /* handles */
    @Override
    public void onClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            MineGuis.get().doLog("this is not a player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        if (this.getCurr().doHide(objPlayer) == false) {
            MineGuis.get().doLog("failed to hide the menu!");
            return;
        }
        if (this.getNext().doShow(objPlayer) == false) {
            MineGuis.get().doLog("failed to show the menu!");
            return;
        }
    }
}
/* end_of_file */