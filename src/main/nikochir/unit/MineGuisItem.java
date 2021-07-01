/* package */
package nikochir.unit;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
/* javkit */
import java.util.List;
import java.util.ArrayList;
/* bukkit */
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
/* nkyori */
import net.kyori.adventure.text.Component;
/* typedef */
/* MineGuisItem class
 * > Description:
 * -> represents an item in the menu;
 * -> stores command execution data;
 * -> the sign is the item name;
 * --> ItemStack.getI18NDisplayName() is invalid;
 * --> use ItemMeta.getDisplayName() instead - this is what we see;
*/
public class MineGuisItem extends MineGuisUnit {
    /* members */
    private final String strCommand;
    private final ItemStack objItem;
    /* codetor */
    public MineGuisItem(String strName, String strLore, Material valType, String strCmd) {
        super(strName);
        this.strCommand = strCmd;
        this.objItem = new ItemStack(valType);
        ItemMeta objMeta = this.objItem.getItemMeta();
        objMeta.displayName(Component.text(strName));
        if (strLore != "") {
            List<Component> arrLore = new ArrayList<Component>(1);
            arrLore.add(Component.text(strLore));
            objMeta.lore(arrLore);
        }
        this.objItem.setItemMeta(objMeta);
    }
    public MineGuisItem(String strName, String strLore, String strCmd)   { this(strName, strLore, Material.COMMAND_BLOCK, strCmd); }
    public MineGuisItem(String strName, Material valType, String strCmd) { this(strName, "",      valType,                strCmd); }
    public MineGuisItem(String strName, String strCmd)                   { this(strName, "",      Material.COMMAND_BLOCK, strCmd); }
    /* getters */
    public String getCommand() { return this.strCommand; }
    public ItemStack getItem() { return this.objItem; }
    /* setters */
    /* vetters */
    public Boolean vetCommand(String strCommand)  { return this.getCommand().equals(strCommand); }
    public Boolean vetItem(ItemStack objItem)     { return this.getItem().equals(objItem); }
    public Boolean vetItem(MineGuisItem objItem)  { return this.vetItem(objItem.getItem()); }
    /* actions */
    public Boolean doExecute(Player objPlayer) {
        if (objPlayer == null) {
            MineGuis.get().doLog("null argument is passed! doExecute(objPlayer);");
            return false;
        }
        if (objPlayer.performCommand(this.getCommand()) == false) {
            MineGuis.get().doLog("failed to execute the command! doExecute(objPlayer);");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */