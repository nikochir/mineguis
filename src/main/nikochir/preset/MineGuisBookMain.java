/* package */
package nikochir.preset;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisItem;
import nikochir.kernel.MineGuisMenu;
import nikochir.kernel.MineGuisBook;
/* typedef */
/* endfile */
/* MineGuisBookMain
 * > Description:
 * -> main book instance;
 * -> automatically creates a book with main configuration parameters;
    *   *   *   *   *   *   *   *   *
    *   a   b   c   d   e   f   g   *
    *   h   i   j   k   l   m   n   *
    *   o   p   q   r   s   t   u   *
    *   *   *   *   *   *   *   *   *
*/
public class MineGuisBookMain extends MineGuisBook {
    /* codetor */
    public MineGuisBookMain() {
        super(MineGuis.get().getConfigStr("nameof_main"), MineGuis.get().getConfigInt("sizeof_main"));
    }
}
/* endfile */