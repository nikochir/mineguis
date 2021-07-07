/* package */
package nikochir.preset;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisItem;
import nikochir.kernel.MineGuisMenu;
/* typedef */
/* endfile */
/* MineGuisMenuMain
 * > Description:
 * -> main menu instance;
 * -> automatically creates a menu with main configuration parameters;
    *   *   *   *   *   *   *   *   *
    *   a   b   c   d   e   f   g   *
    *   h   i   j   k   l   m   n   *
    *   o   p   q   r   s   t   u   *
    *   *   *   *   *   *   *   *   *
*/
public class MineGuisMenuMain extends MineGuisMenu {
    /* codetor */
    public MineGuisMenuMain() {
        super("Menue", 5);
        this.doInitItems();
        this.doInitMenus();
    }
    /* actions */
    private boolean doInitItems() {
        /* 2nd row */
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 2, 2);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 2, 3);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 2, 4);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 2, 5);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 2, 6);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 2, 7);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 2, 8);
        /* 3rd row */
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 3, 3);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 3, 4);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 3, 5);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 3, 6);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 3, 7);
        this.setItem(MineGuis.get().getItem("void", new String[]{ "____" }, "BLACK_STAINED_GLASS_PANE", "mguivoid"), 3, 8);
        /* 4th row */
        this.setItem(MineGuis.get().getItem("back", new String[] { "switch to the last menu" }, "COMPASS", "mguiback"), 4, 2);
        this.setItem(MineGuis.get().getItem("back", new String[] { "switch to the last menu" }, "COMPASS", "mguiback"), 4, 3);
        this.setItem(MineGuis.get().getItem("back", new String[] { "switch to the last menu" }, "COMPASS", "mguiback"), 4, 4);
        this.setItem(MineGuis.get().getItem("back", new String[] { "switch to the last menu" }, "COMPASS", "mguiback"), 4, 5);
        this.setItem(MineGuis.get().getItem("back", new String[] { "switch to the last menu" }, "COMPASS", "mguiback"), 4, 6);
        this.setItem(MineGuis.get().getItem("back", new String[] { "switch to the last menu" }, "COMPASS", "mguiback"), 4, 7);
        this.setItem(MineGuis.get().getItem("back", new String[] { "switch to the last menu" }, "COMPASS", "mguiback"), 4, 8);
        /* quit */
        return true;
    }
    private boolean doInitMenus() {
        return true;
    }
}
/* endfile */