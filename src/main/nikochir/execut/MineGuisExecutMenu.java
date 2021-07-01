/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisUser;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
import nikochir.execut.MineGuisExecut;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisExecutorMenu class
 * > Description:
 * -> ;
*/
public class MineGuisExecutMenu implements CommandExecutor {
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
        if (MineGuis.get().vetUser(objPlayer) == false) {
            MineGuis.get().doLog("failed to find the user!");
            return false;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objPlayer);
        if (objUser == null) {
            MineGuis.get().doLog("failed to find the user!");
            return false;
        }
        if (strArgs.length == 0) {
            if (MineGuis.get().getMenuMain().doShow(objPlayer) == false) {
                MineGuis.get().doLog("failed to show the main menu! MineGuisExecutMenu;");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (MineGuis.get().vetMenu(strArgs[0]) == false) {
                MineGuis.get().doLog("failed to find the menu!");
                return false;
            }
            if (MineGuis.get().getMenu(strArgs[0]).doShow(objPlayer) == false) {
                MineGuis.get().doLog("failed to show the menu!");
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLog("invalid argument count!");
            return false;
        }
    }
}
/* endfile */