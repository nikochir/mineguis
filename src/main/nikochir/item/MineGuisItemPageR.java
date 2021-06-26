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
public class MineGuisItemPageR extends MineGuisItemPage {
    /* members */
    /* codetor */
    public MineGuisItemPageR(MineGuisMenuPage objPage) {
        super(objPage, Material.BOOK, "Right", "Display the next page");
    }
    /* getters */
    /* setters */
    /* vetters */
    /* actions */
    @Override
    public Boolean doSwitch(Player objPlayer) {
        if (this.getPage().doSwitchNext(objPlayer)) { return false; }
        return true;
    }
    /* handles */
}
/* end_of_file */