/* package */
package nikocher.commands;
/* include */
import nikocher.PlayTime;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import co.aikar.timings.TimingHistory;
/* typedef */
/* PlayTimeCommand class
* Description:
* -> ;
*/
public class PlayTimeCommand implements CommandExecutor {
    /* codetor */
    /* getters */
    /* setters */
    /* vetters */
    /* command */
    /* onevent */
    @Override
    public boolean onCommand(
        @NotNull CommandSender sender,
        @NotNull Command command,
        @NotNull String label,
        @NotNull String[] args
        ) {
        // TimingHistory objTimeHistory;
        //System.out.println(command.timings.toString());
        return true;
    }
}