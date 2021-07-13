/* package */
package src.main.nikochir.execut;
/* include */
import src.main.nikochir.Main;
import src.main.nikochir.kernel.Unit;
import src.main.nikochir.kernel.User;
import src.main.nikochir.kernel.Item;
import src.main.nikochir.kernel.Menu;
import src.main.nikochir.kernel.Book;
import src.main.nikochir.execut.Execut;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorBook class
 * > Description:
 * -> set of menu objects;
 * -> every menu is a page;
 * -> automatically sets enumerated names for all pages;
*/
public class ExecutBook implements CommandExecutor {
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
        User objUser = User.getUser(objPlayer);
        if (objUser == null) {
            Main.get().doLogO("failed to find the user!");
            return false;
        }
        if (strArgs.length == 0) {
            if (Book.vetBook(Main.get().getConfigStr("nameof_main")) == false) {
                Main.get().doLogO(objSender, "failed to find the main book!");
                return false;
            }
            if (Book.getBook(Main.get().getConfigStr("nameof_main")).doShow(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to show the main book!");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (Book.vetBook(strArgs[0]) == false) {
                Main.get().doLogO(objSender, "the book \"%s\" is not found!", strArgs[0]);
                return false;
            }
            if (Book.getBook(strArgs[0]).doShow(objPlayer) == false) {
                Main.get().doLogO(objSender, "the book \"%s\" is not shown!", strArgs[0]);
                return false;
            }
            return true;
        } else {
            Main.get().doLogO(objSender, "invalid argument count: %d!", strArgs.length);
            return false;
        }
    }
}
/* endfile */