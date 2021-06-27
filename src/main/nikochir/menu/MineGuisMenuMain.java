/* [nikodim]: 2021/05/05; */
/* package */
package nikochir.menu;
/* include */
import nikochir.MineGuis;
import nikochir.menu.MineGuisMenu;
import nikochir.item.MineGuisItem;
/* typedef */
/* MineGuisMenuMain
* > Description:
 * -> same as a simple menu but uses main configurations;
 */
public class MineGuisMenuMain extends MineGuisMenu {
    /* members */
    /* codetor */
    public MineGuisMenuMain() {
        super(MineGuis.get().getConfigStr("nameof_main"), MineGuis.get().getConfigInt("sizeof_main"));
    }
    /* getters */
    /* setters */
    /* vetters */
    /* actions */
    /* handles */
}
/* end_of_file */