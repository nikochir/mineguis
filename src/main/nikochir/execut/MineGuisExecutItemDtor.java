/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisItem;
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
/* MineGuisExecutorItemDestructor class
 * > Description:
 * -> ;
*/
public class MineGuisExecutItemDtor implements CommandExecutor {
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
            return true;
        } else {
            MineGuis.get().doLogO("invalid argument count! MineGuisExecutItemDtor;");
            return false;
        }
    }
}
/* endfile */