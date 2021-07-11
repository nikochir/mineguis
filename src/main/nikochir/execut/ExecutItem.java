/* package */
package nikochir.execut;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.User;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
import nikochir.execut.Execut;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorItem class
 * > Description:
 * -> ;
*/
public class ExecutItem implements CommandExecutor {
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
        if (User.vetUser(objPlayer) == false) {
            Main.get().doLogO("failed to find the user!");
            return false;
        }
        User objUser = User.getUser(objPlayer);
        if (objUser == null) {
            Main.get().doLogO("failed to find the user!");
            return false;
        }
        if (strArgs.length == 0) {
            if (Item.vetItem(Main.get().getConfigStr("nameof_main")) == false) {
                Main.get().doLogO(objSender, "failed to find the main item!");
                return false;
            }
            if (Item.getItem(Main.get().getConfigStr("nameof_main")).doExec(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to exec the main item!");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (Item.vetItem(strArgs[0]) == false) {
                Main.get().doLogO(objSender, "failed to find the \"%s\" item!", strArgs[0]);
                return false;
            }
            if (Item.getItem(strArgs[0]).doExec(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to exec the \"%s\" item!", strArgs[0]);
                return false;
            }
            return true;
        } else {
            Main.get().doLogO(objSender, "invalid argument count: %d!", strArgs.length);
            return false;
        }
    }
}
/* endfile */