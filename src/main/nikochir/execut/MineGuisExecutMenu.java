/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.menu.MineGuisMenu;
import nikochir.menu.MineGuisMenuBook;
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
/*
 * MineGuisExecutor class
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
        if (strArgs.length == 0) { /* default widgets */
            MineGuis.get().doLog("default mineguis was called");
            String strMenu = MineGuis.get().getConfig().getString("nameof_main");
            if (MineGuis.get().vetMenu(strMenu) == false) {
                MineGuis.get().doLog("failed to find the menu!");
                return false;
            }
            if (MineGuis.get().getMenu(strMenu).doShow(objPlayer) == false) {
                MineGuis.get().doLog("failed to show the menu!");
                return false;
            }
            return true;
        }
        if (strArgs.length == 1) {
            if (MineGuis.get().vetMenu(strArgs[0]) == false) {
                MineGuis.get().doLog("menu is not found!");
                return false;
            }
            if (MineGuis.get().getMenu(strArgs[0]).doShow(objPlayer) == false) {
                MineGuis.get().doLog("menu is not shown!");
                return false;
            }
            return true;
        }
        return false;
    }
}
/* end_of_file */