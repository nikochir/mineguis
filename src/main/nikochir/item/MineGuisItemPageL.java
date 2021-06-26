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
/* MineGuisItemPageLeft
* > Description:
* -> element for negative page switch;
*/
public class MineGuisItemPageL extends MineGuisItemPage {
    /* members */
    /* codetor */
    public MineGuisItemPageL(MineGuisMenuBook objBook) {
        super(objBook, Material.BOOK, "Left", "Display the previous page");
    }
    /* getters */
    /* setters */
    /* vetters */
    /* actions */
    @Override
    public Boolean doSwitch(Player objPlayer) {
        if (this.getBook().doSwitchPrev(objPlayer) == false) {
            MineGuis.get().doLog("failed left book switch!");
            return false;
        }
        return true;
    }
    /* handles */
}
/* end_of_file */