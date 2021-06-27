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
import org.bukkit.inventory.PlayerInventory;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/*
 * MineGuisExecutor class
 * > Description:
 * -> ;
*/
public class MineGuisExecut implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if ((objSender instanceof Player) == false) {
            MineGuis.get().doLog("this is not a player command!");
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
        if (strArgs.length == 2) {
            if (strArgs[0].equalsIgnoreCase("menu")) {
                MineGuis.get().doLog("attemp to open a menu");
                if (MineGuis.get().vetMenu(strArgs[1]) == false) {
                    MineGuis.get().doLog("menu is not found!");
                    return false;
                }
                if (MineGuis.get().getMenu(strArgs[1]).doShow(objPlayer) == false) {
                    MineGuis.get().doLog("menu is not shown!");
                    return false;
                }
                return true;
            }
            if (strArgs[0].equalsIgnoreCase("book")) {
                MineGuis.get().doLog("attemp to open a book");
                if (MineGuis.get().vetBook(strArgs[1]) == false) {
                    MineGuis.get().doLog("book is not found!");
                    return false;
                }
                if (MineGuis.get().getBook(strArgs[1]).doShow(objPlayer) == false) {
                    MineGuis.get().doLog("book is not shown!");
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
/* end_of_file */