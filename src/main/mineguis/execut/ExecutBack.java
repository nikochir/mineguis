/* package */

package main.mineguis.execut;

/* include */

import main.mineguis.Main;
import main.mineguis.kernel.User;

/** bukkit - command interface **/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

/* typedef */

/* ExecutorBack class
 * > Description:
 * -> ;
*/
public class ExecutBack implements CommandExecutor {
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

            if (User.vetUser(objPlayer) == false) {
                Main.get().doLogO("failed to find the user!");
                return false;
            }
            User objUser = User.getUser(objPlayer);
            if (objUser.doMenuBack() == false) {
                Main.get().doLogO(objSender, "back menu is not found!");
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