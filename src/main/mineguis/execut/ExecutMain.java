/* package */

package main.mineguis.execut;

/* include */

import main.mineguis.Main;
import main.mineguis.kernel.Menu;

/** bukkit - command interface **/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

/* typedef */

/* ExecutorMain class
 * > Description:
 * -> ;
*/
public class ExecutMain implements CommandExecutor {

    /* handles */

    @Override
    public boolean onCommand(
        CommandSender objSender,
        Command objCommand,
        String strLabel,
        String[] strArgs
    ) {

        if ((objSender instanceof Player) == false) {
            Main.get().doLogO("this is not a player call!");
            return false;
        }
        Player objPlayer = (Player) objSender;

        if (strArgs.length == 0) {

            String strMain = null;
            strMain = "§7Menue";
            // strMain = Main.get().getConfigStr("nameof_main");
            if (Menu.vetMenu(strMain) == false) {
                Main.get().doLogO(objSender, "failed to find the main menu!");
                return false;
            }
            if (Menu.getMenu(strMain).doShow(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to show the main menu!");
                return false;
            }

            return true;

        } else {
            Main.get().doLogO(objSender, "invalid argument count: %d!", strArgs.length);
            return true;
        }

    }

}

/* endfile */