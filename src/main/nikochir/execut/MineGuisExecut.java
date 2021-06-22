/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
import nikochir.menu.MineGuisMenu;
/** javkit **/
import java.util.ArrayList;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/*
 * MineGuisExecutor class
 * > Description:
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
        if (strArgs.length == 0) { /* default panel */
            String strArgsNext[] = { MineGuis.get().getConfig().getString("nameof_root") };
            MineGuis.get().doLog("default mineguis was called");
            return onCommand(objSender, objCommand, strLabel, strArgsNext);
        }
        if (strArgs.length == 1) {
            if (objSender instanceof Player) {
                Player objPlayer = (Player) objSender;
                //if (objPlayer.hasPermission("mineguis.user") == false) { return false; }
                if (MineGuis.get().vetMenu(strArgs[0])) {
                        MineGuis.get().getMenu(strArgs[0]).doShow(objPlayer);
                } else { /* menu is not found */
                    return false;
                }
                    return true;
            } else { /* not player call */
                return false;
            }
        }
        return false;
    }
}
/* end_of_file */