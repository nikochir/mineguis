/* package */

package main.mineguis.execut;

/* include */

import main.mineguis.Main;
import main.mineguis.kernel.User;
import main.mineguis.kernel.Item;

/** bukkit - command interface **/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

/* typedef */

/* ExecutorItem class
 * > Description:
 * -> ;
*/
public class ExecutItem implements CommandExecutor {
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

            if (Item.vetItem(Main.get().getConfigStr("nameof_main")) == false) {
                Main.get().doLogO(objSender, "failed to find the main item!");
                return false;
            }
            if (Item.getItem(Main.get().getConfigStr("nameof_main")).doExec(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to exec the main item!");
                return false;
            }
            return true;
        } else if (strArgs.length == 1) {
            if (Item.vetItem(strArgs[0]) == false) {
                Main.get().doLogO(objSender, "failed to find the \"%s\" item!", strArgs[0]);
                return false;
            }
            if (Item.getItem(strArgs[0]).doExec(objPlayer) == false) {
                Main.get().doLogO(objSender, "failed to exec the \"%s\" item!", strArgs[0]);
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