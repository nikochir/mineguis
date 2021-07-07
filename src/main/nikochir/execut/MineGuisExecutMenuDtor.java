/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisMenu;
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
/* MineGuisExecutorMenuDestructor class
 * > Description:
 * -> destruct a menu with particular name;
*/
public class MineGuisExecutMenuDtor implements CommandExecutor {
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
                "no arguments provided! MineGuisExecutMenuDtor;"
            ));
            objSender.sendMessage(String.format(
                "no arguments provided!"
            ));
            return false;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (MineGuis.get().vetMenu(strName) == false) {
                MineGuis.get().doLogO(String.format(
                    "the menu \"%s\" is not found! MineGuisExecutMenuDtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the menu \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            MineGuisMenu objMenu = MineGuis.get().getMenu(strName);
            if (MineGuis.get().rmvMenu(objMenu) == false) {
                MineGuis.get().doLogO(String.format(
                    "failed to remove \"%s\" menu! MineGuisExecutMenuDtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to remove \"%s\" menu!",
                    strName
                ));
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO(String.format(
                "invalid arguments count! MineGuisExecutMenuDtor;"
            ));
            objSender.sendMessage(String.format(
                "invalid arguments count!"
            ));
            return false;
        }
    }
}
/* endfile */