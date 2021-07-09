/* package */
package nikochir.execut;
/* include */
import nikochir.Main;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorMenu class
 * > Description:
 * -> find some global menu with the given name and open that;
*/
public class ExecutMenu implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if ((objSender instanceof Player) == false) {
            Main.get().doLogO("this is not a player call!");
            return false;
        }
        Player objPlayer = (Player) objSender;
        if (strArgs.length == 0) {
            Menu objMenu = Menu.getMenu(Main.get().getConfigStr("nameof_main"), Main.get().getConfigInt("sizeof_usem"));
            if (objMenu.doShow(objPlayer) == false) {
                Main.get().doLogO("failed to show the main menu! ExecutMenu;");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (Menu.vetMenu(strArgs[0]) == false) {
                Main.get().doLogO("failed to find the menu!");
                return false;
            }
            if (Menu.getMenu(strArgs[0]).doShow(objPlayer) == false) {
                Main.get().doLogO("failed to show the menu!");
                return false;
            }
            return true;
        } else {
            Main.get().doLogO("invalid argument count!");
            return false;
        }
    }
}
/* endfile */