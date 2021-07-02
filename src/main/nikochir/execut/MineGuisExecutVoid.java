/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisMenu;
import nikochir.unit.MineGuisUser;
import nikochir.unit.MineGuisBook;
import nikochir.execut.MineGuisExecut;
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
/* MineGuisExecutorBack class
 * > Description:
 * -> ;
*/
public class MineGuisExecutVoid implements CommandExecutor {
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
            MineGuis.get().doLogO("some argument was passed in the void!");
            return true;
        } else {
            MineGuis.get().doLogO("some arguments were passed in the void!");
            return true;
        }
    }
}
/* endfile */