/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisBook;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisExecutorBookConstructor class
 * > Description:
 * -> ;
*/
public class MineGuisExecutBookCtor implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if (strArgs.length == 0) {
            MineGuis.get().doLogO(objSender,
                "no arguments provided!"
            );
            return false;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (MineGuis.get().vetBook(strName)) {
                MineGuis.get().doLogO(objSender,
                    "book \"%s\" has already been created!",
                    strName
                );
                return false;
            }
            MineGuisBook objBook = new MineGuisBook(strName);
            if (MineGuis.get().addBook(objBook) == false) {
                MineGuis.get().doLogO(objSender,
                    "failed to create \"%s\" book!",
                    strName
                );
                return false;
            }
            return true;
        } else if (strArgs.length == 2) {
            String strName = strArgs[0];
            Integer numSizeInPages = null;
            try {
                numSizeInPages = Integer.parseInt(strArgs[1]);
            } catch(NumberFormatException exc) {
                MineGuis.get().doLogO(objSender,
                    "invalid argument is passed: \"%s\"!",
                    strArgs[1]
                );
                return false;
            }
            if (MineGuis.get().vetBook(strName)) {
                MineGuis.get().doLogO(objSender,
                    "book \"%s\" has already been created!",
                    strName
                );
                return false;
            }
            MineGuisBook objBook = new MineGuisBook(strName, numSizeInPages);
            if (MineGuis.get().addBook(objBook) == false) {
                MineGuis.get().doLogO(objSender,
                    "failed to create \"%s\" book!",
                    strName
                );
                return false;
            }
            return true;
        } else if (strArgs.length == 3) {
            String strName = strArgs[0];
            Integer numSizeInPages = null;
            Integer numSizeOfPages = null;
            try {
                numSizeInPages = Integer.parseInt(strArgs[1]);
                numSizeOfPages = Integer.parseInt(strArgs[2]);
            } catch(NumberFormatException exc) {
                MineGuis.get().doLogO(objSender,
                    "invalid arguments were passed: \"%s\" and \"%s\"!",
                    strArgs[1], strArgs[2]
                );
                return false;
            }
            if (MineGuis.get().vetBook(strName)) {
                MineGuis.get().doLogO(objSender,
                    "book \"%s\" has already been created!",
                    strName
                );
                return false;
            }
            MineGuisBook objBook = new MineGuisBook(strName, numSizeInPages, numSizeOfPages);
            if (MineGuis.get().addBook(objBook) == false) {
                MineGuis.get().doLogO(objSender,
                    "failed to create \"%s\" book!",
                    strName
                );
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO(objSender,
                "invalid arguments count: %d!",
                strArgs.length
            );
            return false;
        }
    }
}
/* endfile */