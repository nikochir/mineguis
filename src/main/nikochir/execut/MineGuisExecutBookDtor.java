/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisBook;
/** javkit **/
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisExecutorBookDestructor class
 * > Description:
 * -> destruct a book with given name;
*/
public class MineGuisExecutBookDtor implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if (strArgs.length == 0) {
            MineGuis.get().doLogO(String.format(
                "no arguments provided! MineGuisExecutBookDtor;"
            ));
            objSender.sendMessage(String.format(
                "no arguments provided!"
            ));
            return false;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (MineGuis.get().vetBook(strName) == false) {
                MineGuis.get().doLogO(String.format(
                    "the book \"%s\" is not found! MineGuisExecutBookDtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the book \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            MineGuisBook objBook = MineGuis.get().getBook(strName);
            if (MineGuis.get().rmvBook(objBook) == false) {
                MineGuis.get().doLogO(String.format(
                    "failed to remove \"%s\" book! MineGuisExecutBookDtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to remove \"%s\" book!",
                    strName
                ));
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO(String.format(
                "invalid arguments count! MineGuisExecutBookDtor;"
            ));
            objSender.sendMessage(String.format(
                "invalid arguments count!"
            ));
            return false;
        }
    }
}
/* endfile */