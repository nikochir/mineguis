/* package */
package src.main.nikochir.execut;
/* include */
import src.main.nikochir.Main;
import src.main.nikochir.kernel.Unit;
import src.main.nikochir.kernel.Menu;
import src.main.nikochir.kernel.User;
import src.main.nikochir.kernel.Book;
import src.main.nikochir.execut.Execut;
/** javkit **/
import java.util.ArrayList;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorBack class
 * > Description:
 * -> ;
*/
public class ExecutBack implements CommandExecutor {
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
            if (User.vetUser(objPlayer) == false) {
                Main.get().doLogO("failed to find the user!");
                return false;
            }
            User objUser = User.getUser(objPlayer);
            if (objUser.doMenuBack() == false) {
                Main.get().doLogO(objSender, "back menu is not found!");
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