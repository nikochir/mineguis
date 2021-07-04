/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisItem;
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
/* MineGuisExecutorItemDestructor class
 * > Description:
 * -> ;
*/
public class MineGuisExecutItemDtor implements CommandExecutor {
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
                "no arguments provided! MineGuisExecutItemDtor;"
            ));
            objSender.sendMessage(String.format(
                "no arguments provided!"
            ));
            return false;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (MineGuis.get().vetItem(strName) == false) {
                MineGuis.get().doLogO(String.format(
                    "the item \"%s\" is not found! MineGuisExecutItemDtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the item \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            MineGuisItem objItem = MineGuis.get().getItem(strName);
            if (MineGuis.get().rmvItem(objItem) == false) {
                MineGuis.get().doLogO(String.format(
                    "failed to remove \"%s\" item! MineGuisExecutItemDtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to remove \"%s\" item!",
                    strName
                ));
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO(String.format(
                "invalid arguments count! MineGuisExecutItemDtor;"
            ));
            objSender.sendMessage(String.format(
                "invalid arguments count!"
            ));
            return false;
        }
    }
}
/* endfile */