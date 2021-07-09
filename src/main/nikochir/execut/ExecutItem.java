/* package */
package nikochir.execut;
/* include */
import nikochir.Main;
import nikochir.kernel.Item;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorItem class
 * > Description:
 * -> find a global item;
 * -> execute the item;
*/
public class ExecutItem implements CommandExecutor {
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
            Main.get().doLogO("no arguments found!");
            return false;
        } else if (strArgs.length == 1) {
            String strName = strArgs[0];
            if (Item.vetItem(strName) == false) {
                Main.get().doLogO(String.format(
                    "the item \"%s\" is not found!",
                    strName
                ));
                objSender.sendMessage(String.format(
                    "the item \"%s\" is not found!",
                    strName
                ));
                return false;
            }
            Item objItem = Item.getItem(strName);
            if (objItem.doExec(objPlayer) == false) {
                Main.get().doLogO(String.format(
                    "the item exec \"%s\" is failed! ExecutItem;",
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
            Main.get().doLogO("invalid argument count! ExecutItem;");
            return false;
        }
    }
}
/* endfile */