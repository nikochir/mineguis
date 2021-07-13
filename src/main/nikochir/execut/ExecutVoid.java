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