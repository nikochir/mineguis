/* package */
package nikochir.execut;
/* include */
import nikochir.Main;
/** javkit **/
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorVoid class
 * > Description:
 * -> just a dummy placeholder for items without a command;
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