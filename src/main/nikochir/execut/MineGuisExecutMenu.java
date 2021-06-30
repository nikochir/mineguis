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
        MineGuisUser objUser = MineGuis.get().getUser(objPlayer);
        if (objUser == null) {
            MineGuis.get().doLog("failed to find the user!");
            return false;
        }
        if (strArgs.length == 0) {
            return MineGuis.get().getCommand("mguimain").execute(objSender, strLabel, strArgs);
        } else if (strArgs.length == 1) {
            MineGuisMenu objMenu = MineGuis.get().getMenu(strArgs[0]);
            if (objMenu == null) {
                MineGuis.get().doLog("failed to find the menu!");
                return false;
            }
            if (objUser.vetMenuCurr() == true) {
                if (objUser.getMenuCurr().doHide(objUser) == false) {
                    MineGuis.get().doLog("failed to hide the menu!");
                    return false;
                }
            }
            if (objMenu.doShow(objUser) == false) {
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