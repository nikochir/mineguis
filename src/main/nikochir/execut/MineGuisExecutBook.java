/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.menu.MineGuisMenu;
import nikochir.menu.MineGuisMenuBook;
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
/*
 * MineGuisExecutor class
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
        if (strArgs.length == 0) { /* default widgets */
            MineGuis.get().doLog("default mineguis was called");
            String strBook = MineGuis.get().getConfig().getString("nameof_main");
            if (MineGuis.get().vetBook(strBook) == false) {
                MineGuis.get().doLog("failed to find the book!");
                return false;
            }
            if (MineGuis.get().getBook(strBook).doShow(objPlayer) == false) {
                MineGuis.get().doLog("failed to show the book!");
                return false;
            }
            return true;
        }
        if (strArgs.length == 1) {
            if (MineGuis.get().vetBook(strArgs[0]) == false) {
                MineGuis.get().doLog("book is not found!");
                return false;
            }
            if (MineGuis.get().getBook(strArgs[0]).doShow(objPlayer) == false) {
                MineGuis.get().doLog("book is not shown!");
                return false;
            }
            return true;
        }
        return false;
    }
}
/* end_of_file */