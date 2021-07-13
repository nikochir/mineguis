/* package */
package src.main.nikochir.execut;
/* include */
import src.main.nikochir.Main;
import src.main.nikochir.kernel.Unit;
import src.main.nikochir.kernel.User;
import src.main.nikochir.kernel.Item;
import src.main.nikochir.kernel.Menu;
import src.main.nikochir.execut.Execut;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorMain class
 * > Description:
 * -> ;
*/
public class ExecutMain implements CommandExecutor {
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
            String strMain = null;
            strMain = "Menue";
            // strMain = Main.get().getConfigStr("nameof_main");
            if (Menu.vetMenu(strMain) == false) {
                Main.get().doLogO(objSender, "failed to find the main menu!");
                return false;
            }
            if (Menu.getMenu(strMain).doShow(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to show the main menu!");
                return false;
            }
            return true;
        } else {
            Main.get().doLogO(objSender, "invalid argument count: %d!", strArgs.length);
            return true;
        }
    }
}
/* endfile */