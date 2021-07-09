/* package */
package nikochir.preset;
/* include */
import nikochir.Main;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
import nikochir.kernel.Book;
/* typedef */
/* endfile */
/* BookMain
 * > Description:
 * -> main book instance;
 * -> automatically creates a book with main configuration parameters;
    *   *   *   *   *   *   *   *   *
    *   a   b   c   d   e   f   g   *
    *   h   i   j   k   l   m   n   *
    *   o   p   q   r   s   t   u   *
    *   *   *   *   *   *   *   *   *
*/
public class BookMain extends Book {
    /* codetor */
    public BookMain() {
        super(Main.get().getConfigStr("nameof_main"), Main.get().getConfigInt("sizeof_main"));
    }
}
/* endfile */