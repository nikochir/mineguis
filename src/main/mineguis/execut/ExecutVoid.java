/* package */

package main.mineguis.execut;

/* include */
import main.mineguis.Main;

/** bukkit - command interface **/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/* typedef */

/* ExecutorBack class
 * > Description:
 * -> ;
*/
public class ExecutVoid implements CommandExecutor {
    /* handles */
    @Override
    public boolean onCommand(
        CommandSender objSender,
        Command objCommand,
        String strLabel,
        String[] strArgs
    ) {

        switch (strArgs.length) {
            case 0: {
                return true;
            }
            case 1: {
                Main.get().doLogO("some argument was passed in the void!");
                return true;
            }
            default: {
                Main.get().doLogO("some arguments were passed in the void!");
                return true;
            }
        }

    }

}

/* endfile */