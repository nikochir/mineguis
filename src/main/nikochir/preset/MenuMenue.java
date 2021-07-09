/* package */
package nikochir.preset;
/* include */
import nikochir.Main;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
/* typedef */
/* endfile */
/* MenuMain
 * > Description:
 * -> main menu instance;
 * -> automatically creates a menu with main configuration parameters;
    *   *   *   *   *   *   *   *   *
    *   a   b   c   d   e   f   g   *
    *   h   i   j   k   l   m   n   *
    *   o   p   q   r   s   t   u   *
    *   *   *   *   *   *   *   *   *
*/
public class MenuMenue extends Menu {
    /* codetor */
    public MenuMenue() {
        super("Menue", 5);
        this.doInitItems();
        this.doInitMenus();
    }
    /* actions */
    private boolean doInitItems() {
        /* 2nd row */
        /* 3rd row */
        /* 4th row */
        /* quit */
        return true;
    }
    private boolean doInitMenus() {
        return true;
    }
}
/* endfile */