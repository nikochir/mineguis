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
/* MineGuisExecutorItemContructor class
 * > Description:
 * -> construct a menu with given arguments;
*/
public class MineGuisExecutItemCtor implements CommandExecutor {
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
                "no args provided! MineGuisExecutItemCtor;"
            ));
            objSender.sendMessage(String.format(
                "no arguments provided!"
            ));
            return false;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (MineGuis.get().vetItem(strName)) {
                MineGuis.get().doLogO(String.format(
                    "the item \"%s\" already exists! MineGuisExecutorItemCtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the item \"%s\" already exists!",
                    strName
                ));
                return false;
            }
            MineGuisItem objItem = new MineGuisItem(strName);
            if (MineGuis.get().addItem(objItem) == false) {
                MineGuis.get().doLogO(String.format(
                    "failed to add \"%s\" item! MineGuisExecutorItemCtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to add \"%s\" item!",
                    strName
                ));
                return false;
            }
            return true;
        } else if (strArgs.length == 2) {
            String strName = strArgs[0];
            String strIcon = strArgs[1];
            if (MineGuis.get().vetItem(strName)) {
                MineGuis.get().doLogO(String.format(
                    "the item \"%s\" already exists! MineGuisExecutorItemCtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the item \"%s\" already exists!",
                    strName
                ));
                return false;
            }
            MineGuisItem objItem = new MineGuisItem(strName, strIcon);
            if (MineGuis.get().addItem(objItem) == false) {
                MineGuis.get().doLogO(String.format(
                    "failed to add \"%s\" item! MineGuisExecutorItemCtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to add \"%s\" item!",
                    strName
                ));
                return false;
            }
            return true;
        } else if (strArgs.length == 3) {
            String strName = strArgs[0];
            String strIcon = strArgs[1];
            String strExec = strArgs[2];
            if (MineGuis.get().vetItem(strName)) {
                MineGuis.get().doLogO(String.format(
                    "the item \"%s\" already exists! MineGuisExecutorItemCtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the item \"%s\" already exists!",
                    strName
                ));
                return false;
            }
            MineGuisItem objItem = new MineGuisItem(strName, strIcon, strExec);
            if (MineGuis.get().addItem(objItem) == false) {
                MineGuis.get().doLogO(String.format(
                    "failed to add \"%s\" item! MineGuisExecutorItemCtor;",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "failed to add \"%s\" item!",
                    strName
                ));
                return false;
            }
            return true;
        } else {
            MineGuis.get().doLogO(String.format(
                "invalid argument count: %d! MineGuisExecutItemCtor;",
                strArgs.length
            ));
            objSender.sendMessage(String.format(
                "invalid argument count %d!",
                strArgs.length
            ));
            return false;
        }
    }
}
/* endfile */