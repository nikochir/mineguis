/* package */
package nikocher;
/* include */
import org.bukkit.plugin.java.JavaPlugin;
/* typedef */
/* PlayTime class
 * Description:
 * -> ;
*/
public final class PlayTime extends JavaPlugin {
    /* codetor */
    /* getters */
    /* setters */
    /* vetters */
    /* command */
    /* onevent */
    @Override
    public void onEnable() {
        for (int itr = 0; itr < 10; itr++) {
            System.out.println("Greetings, Native World!");
        }
    }
    @Override
    public void onDisable() {
        for (int itr = 0; itr < 10; itr++) {
            System.out.println("Goodbye, Native World!");
        }
    }
}