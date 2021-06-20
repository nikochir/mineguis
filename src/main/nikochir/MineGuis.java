/* package */
package nikochir;
/* include */
import nikochir.menu.*;
import nikochir.item.*;
import nikochir.execut.*;
import nikochir.listen.*;
import nikochir.permit.*;
/** bukkit **/
import org.bukkit.plugin.Plugin;
/* MineGuis class
 * description:
 * >
*/
public abstract class MineGuis implements Plugin {
    /* codetor */
    /* getters */
    /* setters */
    /* vetters */
    /* command */
    @Override
    public void onEnable() {
        getServer().getPluginCommand("mineguis").setExecutor(new MineGuisExecut());
        getServer().getPluginManager().registerEvents(new MineGuisListen(), this);
        getServer().getPluginManager().addPermission(new MineGuisPermit());
    }
    @Override
    public void onDisable() {
    }
    /* onevent */
}
/* end_of_file */