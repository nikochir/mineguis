/* package */
package nikochir.execut;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Menu;
import nikochir.kernel.User;
import nikochir.kernel.Book;
import nikochir.execut.Execut;
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
public class ExecutVoid implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if (strArgs.length == 0) {
            return true;
        } else if (strArgs.length == 1) {
            Main.get().doLogO("some argument was passed in the void!");
            return true;
        } else {
            Main.get().doLogO("some arguments were passed in the void!");
            return true;
        }
    }
}
/* endfile */