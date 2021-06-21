/* package */
package nikochir;
/* include */
import nikochir.menu.*;
import nikochir.item.*;
import nikochir.execut.*;
import nikochir.listen.*;
import nikochir.permit.*;
/** bukkit **/
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
/* MineGuis class
 * description:
 * >
*/
public class MineGuis extends JavaPlugin {
    /* members */
    private static MineGuis objInstance;
    /* getters */
    public static MineGuis get() { return objInstance; }
    /* setters */
    /* vetters */
    /* command */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        /* work */
        /** conf **/
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        /** regs **/
        this.getServer().getPluginCommand("mineguis").setExecutor(new MineGuisExecut());
        this.getServer().getPluginManager().registerEvents(new MineGuisListen(), this);
        this.getServer().getPluginManager().addPermission(new MineGuisPermit());
        /* quit */
    }
    @Override
    public void onDisable() {
        /* init */
        /* work */
        /* quit */
        objInstance = null;
    }
    /* onevent */
}
/* end_of_file */