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
 * -> set of menu objects;
 * -> every menu is a page;
 * -> automatically sets enumerated names for all pages;
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
            MineGuis.get().doLogO("this is not a player call!");
            return false;
        }
        Player objPlayer = (Player) objSender;
        MineGuisUser objUser = MineGuis.get().getUser(objPlayer);
        if (objUser == null) {
            MineGuis.get().doLogO("failed to find the user!");
            return false;
        }
        if (strArgs.length == 0) {
            if (MineGuis.get().getBookMain().doShow(objPlayer) == false) {
                MineGuis.get().doLogO("failed to show the main book! MineGuisExecutBook;");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (MineGuis.get().vetBook(strArgs[0]) == false) {
                MineGuis.get().doLogO("the book is not found! MineGuisExecutBook;");
                return false;
            }
            if (MineGuis.get().getBook(strArgs[0]).doShow(objPlayer) == false) {
                MineGuis.get().doLogO("the book is not shown! MineGuisExecutBook;");
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO("invalid argument count!");
            return false;
        }
    }
}
/* endfile */