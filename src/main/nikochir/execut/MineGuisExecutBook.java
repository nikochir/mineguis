/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisUser;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
import nikochir.unit.MineGuisBook;
import nikochir.execut.MineGuisExecut;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisExecutorBook class
 * > Description:
 * -> ;
*/
public class MineGuisExecutBook implements CommandExecutor {
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
            MineGuis.get().doLog("book name is not specified!");
            return false;
        } else if (strArgs.length == 1) {
            MineGuisBook objBook = MineGuis.get().getBook(strArgs[1]);
            if (objBook == null) {
                MineGuis.get().doLog("failed to find the book!");
                return false;
            }
            if (objUser.vetMenuCurr() == true) {
                if (objUser.getMenuCurr().doHide(objUser.getPlayer()) == false) {
                    MineGuis.get().doLog("failed to hide the menu!");
                    return false;
                }
            }
            if (objBook.doShow(objUser.getPlayer()) == false) {
                MineGuis.get().doLog("failed to show the book!");
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