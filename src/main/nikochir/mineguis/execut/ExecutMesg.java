/* package */
package nikochir.mineguis.execut;
/* include */
import nikochir.mineguis.Main;
import nikochir.mineguis.kernel.User;

/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/* typedef */
/** ExecutMesg class
* > Description:
 * -> ;
 */
public class ExecutMesg implements CommandExecutor {
    @Override
    public boolean onCommand(
        CommandSender objSender,
        Command objCommand,
        String strLabel,
        String strArgs[]
    ) {
        switch (strArgs.length) {
            case 0: {
                Main.get().doLogO(objSender, "invalid argument count: %d;", strArgs.length);
                return false;
            }
            default: {
                String strMesg = "";
                for (String itrStr : strArgs) { strMesg += " " + itrStr; }
                objSender.sendMessage(strMesg);
                break;
            }
        }
        return true;
    }
}
/* endfile */