/* [nikodim]: 2021/05/06; */
/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItemPage;
import nikochir.menu.MineGuisMenuBook;
/* bukkit */
import org.bukkit.Material;
import org.bukkit.entity.Player;
/* typedef */
/* MineGuisItemPageRight
* > Description:
* -> element for negative page switch;
*/
public class MineGuisItemPageR extends MineGuisItemPage {
    /* members */
    /* codetor */
    public MineGuisItemPageR(MineGuisMenuBook objBook) {
        super(objBook, Material.BOOK, "Right", "Display the next page");
    }
    /* getters */
    /* setters */
    /* vetters */
    /* actions */
    @Override
    public Boolean doSwitch(Player objPlayer) {
        if (this.getBook().doSwitchNext(objPlayer) == false) {
            MineGuis.get().doLog("failed right book switch!");
            return false;
        }
        return true;
    }
    /* handles */
}
/* end_of_file */