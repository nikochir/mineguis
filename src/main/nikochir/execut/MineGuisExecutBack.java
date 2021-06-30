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
public class MineGuisExecutBack implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if ((objSender instanceof Player) == false) {
            MineGuis.get().doLog("this is not a player call!");
            return false;
        }
        Player objPlayer = (Player) objSender;
        MineGuisUser objUser = MineGuis.get().getUser(objPlayer);
        if (objUser == null) {
            MineGuis.get().doLog("failed to find the user!");
            return false;
        }
        if (strArgs.length == 0) {
            return true;
        } else {
            MineGuis.get().doLog("invalid argument count!");
            return false;
        }
    }
}
/* endfile */