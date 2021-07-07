/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisMenu;
import nikochir.kernel.MineGuisBook;
/** javkit **/
import java.lang.Integer;
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
 * -> find a global book;
 * -> open the book on the given page;
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
        if (strArgs.length == 0) {
            if (MineGuis.get().getBookMain().doShow(objPlayer) == false) {
                MineGuis.get().doLogO("failed to show the main book! MineGuisExecutBook;");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (MineGuis.get().vetBook(strName) == false) {
                MineGuis.get().doLogO(String.format(
                    "the book \"%s\" is not found! MineGuisExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the book \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            MineGuisBook objBook = MineGuis.get().getBook(strName);
            if (objBook.doShow(objPlayer) == false) {
                MineGuis.get().doLogO(String.format(
                    "the book \"%s\" is not shown! MineGuisExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to show the book \"%s\"!",
                    strName
                ));
                return false;
            }
            return true;
        } else if (strArgs.length == 2) {
            String strName = strArgs[0];
            if (MineGuis.get().vetBook(strName) == false) {
                MineGuis.get().doLogO(String.format(
                    "the book \"%s\" is not found! MineGuisExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the book \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            MineGuisBook objBook = MineGuis.get().getBook(strName);
            Integer numPage = null;
            try {
                numPage = Integer.parseInt(strArgs[1]);
            } catch (NumberFormatException exc) {
                MineGuis.get().doLogO(String.format(
                    "invalid argument: %s! MineGuisExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "invalid argument: %s!",
                    strName
                ));
                return false;
            }
            if (objBook.vetPage(numPage) == false) {
                MineGuis.get().doLogO(String.format(
                    "the book \"%s\" does not have page \"%d\"! MineGuisExecutBook;",
                    strName, numPage
                ));
                objSender.sendMessage(String.format(
                    "the book \"%s\" does not have page \"%d\"!",
                    strName, numPage
                ));
                return false;
            }
            MineGuisMenu objPage = objBook.getPage(numPage);
            if (objPage.doShow(objPlayer) == false) {
                MineGuis.get().doLogO(String.format(
                    "the book \"%s\" is not shown! MineGuisExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to show the book \"%s\"!",
                    strName
                ));
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