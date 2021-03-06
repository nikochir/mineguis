/* package */

package main.mineguis.execut;

/* include */

import main.mineguis.Main;
import main.mineguis.kernel.User;
import main.mineguis.kernel.Menu;

/** bukkit - command interface **/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

/* typedef */

/* ExecutorMenu class
 * > Description:
 * -> ;
*/
public class ExecutMenu implements CommandExecutor {
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
        if (User.vetUser(objPlayer) == false) {
            Main.get().doLogO("failed to find the user!");
            return false;
        }
        User objUser = User.getUser(objPlayer);
        if (objUser == null) {
            Main.get().doLogO("failed to find the user!");
            return false;
        }
        if (strArgs.length == 0) {
            if (Menu.vetMenu(Main.get().getConfigStr("nameof_main")) == false) {
                Main.get().doLogO(objSender, "failed to find the main menu!");
                return false;
            }
            if (Menu.getMenu(Main.get().getConfigStr("nameof_main")).doShow(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to show the main menu!");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (Menu.vetMenu(strArgs[0]) == false) {
                Main.get().doLogO(objSender, "failed to find the \"%s\" menu!", strArgs[0]);
                return false;
            }
            if (Menu.getMenu(strArgs[0]).doShow(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to show the \"%s\" menu!", strArgs[0]);
                return false;
            }

            return true;

        } else {
            Main.get().doLogO(objSender, "invalid argument count: %d!", strArgs.length);
            return false;
        }

    }

}

/* endfile */