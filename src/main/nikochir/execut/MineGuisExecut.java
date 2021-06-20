/* package */
package nikochir.execut;
/* include */
/** bukkit **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
/* typedef */
/* MineGuisCommand class
 * Description:
 * -> ;
*/
public class MineGuisExecut implements CommandExecutor {
    /* onevent */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if (objSender instanceof Player) {
            Player objPlayer = (Player) objSender;
            if (objPlayer.hasPermission("mineguis.access")) {
                return true;
            } else {
                System.out.println("No access permission!");
                return false;
            }
        }
        return false;
    }
}
/* end_of_file */