/* [nikodim]: 2021/05/00; */
/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItem;
import nikochir.execut.MineGuisExecut;
/* javkit */
import java.util.List;
import java.util.ArrayList;
/* bukkit */
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
/* typedef */
/* MineGuisItemCall
 * > Description:
 * -> calls the given command whenever it's clicked;
 * -> just define a command text and this will be called;
 * -> the item name is command label;
 * -> the item lore is argument list;
*/
public class MineGuisItemCall extends MineGuisItem {
    /* members */
    /* codetor */
    public MineGuisItemCall(Material valMaterial, String strName, String[] strArgs) {
        super(valMaterial, strName, strArgs);
    }
    public MineGuisItemCall(Material valMaterial, String strName, String strArgs) {
        this(valMaterial, strName, strArgs.split(" "));
    }
    public MineGuisItemCall(String strName, String[] strArgs) {
        this(Material.COMMAND_BLOCK, strName, strArgs);
    }
    public MineGuisItemCall(String strName, String strArgs) {
        this(Material.COMMAND_BLOCK, strName, strArgs.split(" "));
    }
    public MineGuisItemCall(String strName) {
        this (strName, "");
    }
    /* getters */
    public Integer getArgNumb()      { return this.getLore().size(); }
    public String getArg(int numIdx) { return this.getLore().get(numIdx); }
    public String getArgBeg()        { return this.getLore().get(0); }
    public String getArgEnd()        { return this.getLore().get(this.getArgNumb()); }
    public String[] getArgs()        { return this.getLore().toString().replace("[", "").replace("]", "").split(" "); }
    public Command getCommand()      { return MineGuis.get().getCommand(this.getName()); }
    /* setters */
    /* vetters */
    /* actions */
    /* handles */
    @Override
    public void onClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            MineGuis.get().doLog("this is not a player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        if (this.getCommand().execute(objPlayer, this.getName(), this.getArgs()) == false) {
            MineGuis.get().doLog(String.format("failed to execute the command! name: %s; args: %s", this.getName(), this.getArgs()[0]));
            return;
        }
    }
}
/* end_of_file */