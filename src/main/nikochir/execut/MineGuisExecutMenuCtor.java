/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisMenu;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisExecutorMenuConstructor class
 * > Description:
 * -> ;
*/
public class MineGuisExecutMenuCtor implements CommandExecutor {
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
            if (MineGuis.get().vetMenu(strName)) {
                MineGuis.get().doLogO(objSender,
                    "menu \"%s\" has already been created!",
                    strName
                );
                return false;
            }
            MineGuisMenu objMenu = new MineGuisMenu(strName);
            if (MineGuis.get().addMenu(objMenu) == false) {
                MineGuis.get().doLogO(objSender,
                    "failed to create \"%s\" menu!",
                    strName
                );
                return false;
            }
            return true;
        } else if (strArgs.length == 2) {
            String strName = strArgs[0];
            Integer numSize = null;
            try {
                numSize = Integer.parseInt(strArgs[1]);
            } catch(NumberFormatException exc) {
                MineGuis.get().doLogO(objSender,
                    "invalid argument is passed: \"%s\"!",
                    strArgs[1]
                );
                return false;
            }
            if (MineGuis.get().vetMenu(strName)) {
                MineGuis.get().doLogO(objSender,
                    "menu \"%s\" has already been created!",
                    strName
                );
                return false;
            }
            MineGuisMenu objMenu = new MineGuisMenu(strName, numSize);
            if (MineGuis.get().addMenu(objMenu) == false) {
                MineGuis.get().doLogO(objSender,
                    "failed to create \"%s\" menu!",
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