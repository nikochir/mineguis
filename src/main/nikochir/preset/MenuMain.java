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
public class MenuMain extends Menu {
    /* codetor */
    public MenuMain() {
        super(Main.get().getConfigStr("nameof_main"), Main.get().getConfigInt("sizeof_main"));
    }
}
/* endfile */