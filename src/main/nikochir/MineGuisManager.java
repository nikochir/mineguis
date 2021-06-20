/* package */
package nikochir;
/* include */
import nikochir.menu.*;
import nikochir.item.*;
/** javkit **/
import java.util.Map;
/** bukkit **/
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
/* typedef */
/* MenuManager class
 * description:
 * >
*/
public class MineGuisManager {
    /* members */
    Map<String, MineGuisMenu> mapMenus;
    /* codetor */
    public MineGuisManager(Plugin objPlugin, Map<String, MineGuisMenu> mapMenus) {
        this.mapMenus = mapMenus;
        registerListeners(objPlugin);
    }
    /* getters */
    public MineGuisMenu getMenu(String objKey) { return mapMenus.get(objKey);}
    /* vetters */
    public Boolean vetMenu(Inventory objInven) {
        return true;
    }
    /* command */
    private void registerListeners(Plugin objPlugin) {
    }
}
/* end_of_file */