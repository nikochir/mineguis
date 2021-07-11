/* package */
package nikochir.execut;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.User;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
import nikochir.kernel.Book;
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
/* Executor class
 * > Description:
 * -> ;
*/
public class Execut implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if ((objSender instanceof Player) == false) {
            Main.get().doLogO("this is not a player call!");
            return false;
        }
        Player objPlayer = (Player) objSender;
        if (strArgs.length == 0) {
            if (Menu.vetMenu(Main.get().getConfigStr("nameof_main")) == false) {
                Main.get().doLogO(objSender, "failed to find the main menu!");
                return false;
            }
            if (Menu.getMenu(Main.get().getConfigStr("nameof_main")).doShow(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to show the main menu!");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (strArgs[0].isEmpty()) {
                Main.get().doLogO(objSender, "invalid argument!");
                return false;
            } else if (strArgs[0].equalsIgnoreCase("item")) {
                if (Item.vetItem(Main.get().getConfigStr("nameof_main")) == false) {
                    Main.get().doLogO(objSender, "failed to find the main item!");
                    return false;
                }
                if (Item.getItem(Main.get().getConfigStr("nameof_main")).doExec(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to exec the main item!");
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("menu")) {
                if (Menu.vetMenu(Main.get().getConfigStr("nameof_main")) == false) {
                    Main.get().doLogO(objSender, "failed to find the main menu!");
                    return false;
                }
                if (Menu.getMenu(Main.get().getConfigStr("nameof_main")).doShow(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to show the main menu!");
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("book")) {
                if (Book.vetBook(Main.get().getConfigStr("nameof_main")) == false) {
                    Main.get().doLogO(objSender, "failed to find the main book!");
                    return false;
                }
                if (Book.getBook(Main.get().getConfigStr("nameof_main")).doShow(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to show the main book!");
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("debug")) {
                Main.get().doLogO("debug command is called!");
                objPlayer.sendMessage("you have just called debug command;");
                objPlayer.sendMessage(Main.get().getConfigStr("mesg_dbug"));
                return true;
            } else {
                if (Menu.vetMenu(strArgs[0]) == false) {
                    Main.get().doLogO(objSender, "failed to find the \"%s\" menu!", strArgs[0]);
                    return false;
                }
                if (Menu.getMenu(strArgs[0]).doShow(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to show the \"%s\" menu!", strArgs[0]);
                    return false;
                }
                return true;
            }
        } else if (strArgs.length == 2) {
            if (strArgs[0].isEmpty()) {
                Main.get().doLogO(objSender, "invalid argument!");
                return false;
            } else if (strArgs[0].equalsIgnoreCase("item")) {
                if (Item.vetItem(strArgs[1]) == false) {
                    Main.get().doLogO(objSender, "failed to find the \"%s\" item!", strArgs[1]);
                    return false;
                }
                if (Item.getItem(strArgs[1]).doExec(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to show the \"%s\" item!", strArgs[1]);
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("menu")) {
                if (Menu.vetMenu(strArgs[1]) == false) {
                    Main.get().doLogO(objSender, "failed to find the \"%s\" menu!", strArgs[1]);
                    return false;
                }
                if (Menu.getMenu(strArgs[1]).doShow(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to show the \"%s\" menu!", strArgs[1]);
                    return false;
                }
                return true;
            } else if (strArgs[0].equalsIgnoreCase("book")) {
                if (Book.vetBook(strArgs[1]) == false) {
                    Main.get().doLogO(objSender, "failed to find the \"%s\" book!", strArgs[1]);
                    return false;
                }
                if (Book.getBook(strArgs[1]).doShow(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to show the \"%s\" book!", strArgs[1]);
                    return false;
                }
                return true;
            } else {
                if (Menu.vetMenu(strArgs[0]) == false) {
                    Main.get().doLogO(objSender, "failed to find the \"%s\" menu!", strArgs[0]);
                    return false;
                }
                if (Menu.getMenu(strArgs[0]).doShow(objPlayer) == false) {
                    Main.get().doLogO(objSender, "failed to show the \"%s\" menu!", strArgs[0]);
                    return false;
                }
                return true;
            }
        } else {
            Main.get().doLogO("too many arguments!");
            return false;
        }
    }
}
/* endfile */