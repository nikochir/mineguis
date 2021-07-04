/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisUser;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
import nikochir.unit.MineGuisBook;
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
/* MineGuisExecutor class
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
            MineGuis.get().doLogO("this is not a player call!");
            return false;
        }
        Player objPlayer = (Player) objSender;
        if (strArgs.length == 0) {
            if (MineGuis.get().getMenuMain().doShow(objPlayer) == false) {
                MineGuis.get().doLogO("failed to show the main menu!");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (strArgs[0].isEmpty()) {
                MineGuis.get().doLogO("invalid argument!");
                return false;
            } else if (strArgs[0].equalsIgnoreCase("menu")) {
                if (MineGuis.get().getMenuMain().doShow(objPlayer) == false) {
                    MineGuis.get().doLogO("failed to show the main menu!");
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("book")) {
                if (MineGuis.get().getBookMain().doShow(objPlayer) == false) {
                    MineGuis.get().doLogO("failed to show the main book!");
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("debug")) {
                MineGuis.get().doLogO("debug command is called!");
                objSender.sendMessage("you have just called debug command;");
                objSender.sendMessage(MineGuis.get().getConfigStr("mesg_dbug"));
                return true;
            } else {
                if (MineGuis.get().vetMenu(strArgs[0]) == false) {
                    MineGuis.get().doLogO("failed to find the menu!");
                    return false;
                }
                if (MineGuis.get().getMenu(strArgs[0]).doShow(objPlayer) == false) {
                    MineGuis.get().doLogO("failed to show the main book!");
                    return false;
                }
                return true;
            }
        } else if (strArgs.length == 2) {
            if (strArgs[0].isEmpty()) {
                MineGuis.get().doLogO("invalid arguments!");
                return false;
            } else if (strArgs[0].equalsIgnoreCase("menu")) {
                if (MineGuis.get().vetMenu(strArgs[1]) == false) {
                    MineGuis.get().doLogO("failed to find the menu!");
                    return false;
                }
                if (MineGuis.get().getMenu(strArgs[1]).doShow(objPlayer) == false) {
                    MineGuis.get().doLogO("failed to show the menu!");
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("book")) {
                if (strArgs[1].isEmpty()) {
                    MineGuis.get().doLogO("invalid arguments!");
                    return false;
                } else {
                    if (MineGuis.get().vetBook(strArgs[1]) == false) {
                        MineGuis.get().doLogO("failed to find the book!");
                        return false;
                    } else {
                        if (MineGuis.get().getBook(strArgs[1]).doShow(objPlayer) == false) {
                            MineGuis.get().doLogO("failed to show the book!");
                            return false;
                        }
                        return true;
                    }
                }
            } else {
                if (MineGuis.get().vetMenu(strArgs[0]) == false) {
                    MineGuis.get().doLogO("failed to find the menu!");
                    return false;
                }
                if (MineGuis.get().getMenu(strArgs[0]).doShow(objPlayer) == false) {
                    MineGuis.get().doLogO("failed to show the main book!");
                    return false;
                }
                return true;
            }
        } else {
            MineGuis.get().doLogO("too many arguments!");
            return false;
        }
    }
}
/* endfile */