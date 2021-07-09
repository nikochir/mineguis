/* package */
package nikochir.execut;
/* include */
import nikochir.Main;
import nikochir.kernel.Menu;
import nikochir.kernel.Book;
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
/* ExecutorBook class
 * > Description:
 * -> find a global book;
 * -> open the book on the given page;
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
        if (strArgs.length == 0) {
            Book objBook = Book.getBook(
                Main.get().getConfigStr("nameof_main"),
                Main.get().getConfigInt("sizeof_useb"),
                Main.get().getConfigInt("sizeof_usem")
            );
            if (objBook.doShow(objPlayer) == false) {
                Main.get().doLogO("failed to show the main book! ExecutBook;");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (Book.vetBook(strName) == false) {
                Main.get().doLogO(String.format(
                    "the book \"%s\" is not found! ExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the book \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            Book objBook = Book.getBook(strName);
            if (objBook.doShow(objPlayer) == false) {
                Main.get().doLogO(String.format(
                    "the book \"%s\" is not shown! ExecutBook;",
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
            if (Book.vetBook(strName) == false) {
                Main.get().doLogO(String.format(
                    "the book \"%s\" is not found! ExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the book \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            Book objBook = Book.getBook(strName);
            Integer numPage = null;
            try {
                numPage = Integer.parseInt(strArgs[1]);
            } catch (NumberFormatException exc) {
                Main.get().doLogO(String.format(
                    "invalid argument: %s! ExecutBook;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "invalid argument: %s!",
                    strName
                ));
                return false;
            }
            if (objBook.vetPage(numPage) == false) {
                Main.get().doLogO(String.format(
                    "the book \"%s\" does not have page \"%d\"! ExecutBook;",
                    strName, numPage
                ));
                objSender.sendMessage(String.format(
                    "the book \"%s\" does not have page \"%d\"!",
                    strName, numPage
                ));
                return false;
            }
            Menu objPage = objBook.getPage(numPage);
            if (objPage.doShow(objPlayer) == false) {
                Main.get().doLogO(String.format(
                    "the book \"%s\" is not shown! ExecutBook;",
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
            Main.get().doLogO("invalid argument count!");
            return false;
        }
    }
}
/* endfile */