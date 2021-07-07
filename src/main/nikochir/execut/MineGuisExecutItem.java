/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisItem;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisExecutorItem class
 * > Description:
 * -> find a global item;
 * -> execute the item;
*/
public class MineGuisExecutItem implements CommandExecutor {
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
            if (MineGuis.get().getItemMain().doExec(objPlayer) == false) {
                MineGuis.get().doLogO("failed to exec the main item! MineGuisExecutItem;");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (MineGuis.get().vetItem(strName) == false) {
                MineGuis.get().doLogO(String.format(
                    "the item \"%s\" is not found!",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the item \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            MineGuisItem objItem = MineGuis.get().getItem(strName);
            if (objItem.doExec(objPlayer) == false) {
                MineGuis.get().doLogO(String.format(
                    "the item exec \"%s\" is failed! MineGuisExecutItem;",
                    objItem.getExec()
                ));
                objSender.sendMessage(String.format(
                    "the item exec \"%s\" is failed!",
                    objItem.getExec()
                ));
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO("invalid argument count! MineGuisExecutItem;");
            return false;
        }
    }
}
/* endfile */