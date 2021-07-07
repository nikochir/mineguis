/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisMenu;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisExecutorMain class
 * > Description:
 * -> open the main menu;
*/
public class MineGuisExecutMain implements CommandExecutor {
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
            MineGuisMenu objMenu = MineGuis.get().getMenuMain();
            if (objMenu.doShow(objPlayer) == false) {
                MineGuis.get().doLogO("failed to show the menu!");
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO("invalid argument count!");
            return true;
        }
    }
}
/* endfile */