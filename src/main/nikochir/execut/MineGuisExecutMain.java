/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisUser;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
import nikochir.execut.MineGuisExecut;
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
 * -> ;
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
            MineGuis.get().doLog("this is not a player call!");
            return false;
        }
        Player objPlayer = (Player) objSender;
        if (strArgs.length == 0) {
            MineGuisMenu objMenu = MineGuis.get().getMenuMain();
            if (objMenu.doShow(objPlayer) == false) {
                MineGuis.get().doLog("failed to show the menu!");
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLog("invalid argument count!");
            return true;
        }
    }
}
/* endfile */