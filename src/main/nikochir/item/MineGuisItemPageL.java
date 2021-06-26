/* [nikodim]: 2021/05/06; */
/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItemPage;
import nikochir.menu.MineGuisMenuPage;
/* bukkit */
import org.bukkit.Material;
import org.bukkit.entity.Player;
/* typedef */
/*
* MineGuisItemPageLeft
* > Description:
* -> element for negative page switch;
*/
public class MineGuisItemPageL extends MineGuisItemPage {
    /* members */
    /* codetor */
    public MineGuisItemPageL(MineGuisMenuPage objPage) {
        super(objPage, Material.BOOK, "Left", "Display the previous page");
    }
    /* getters */
    /* setters */
    /* vetters */
    /* actions */
    @Override
    public Boolean doSwitch(Player objPlayer) {
        if (this.getPage().doSwitchPrev(objPlayer)) { return false; }
        return true;
    }
    /* handles */
}
/* end_of_file */