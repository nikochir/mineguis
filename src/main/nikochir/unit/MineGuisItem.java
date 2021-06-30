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
 * -> ;
*/
public class MineGuisItem extends MineGuisUnit {
    /* members */
    private final String strCommand;
    private final ItemStack objItem;
    /* codetor */
    public MineGuisItem(String strCommand, ItemStack objItem) {
        super(objItem.getI18NDisplayName());
        this.strCommand = strCommand;
        this.objItem = objItem;
    }
    public MineGuisItem(String strCommand, String strItemName, String strItemLore, Material valItemType) {
        super(strItemName);
        this.strCommand = strCommand;
        this.objItem = new ItemStack(valItemType);
        ItemMeta objMeta = this.objItem.getItemMeta();
        objMeta.displayName(Component.text(strItemName));
        List<Component> arrLore = new ArrayList<Component>(1); arrLore.add(Component.text(strItemLore));
        objMeta.lore(arrLore);
        this.objItem.setItemMeta(objMeta);
    }
    /* getters */
    public String getCommand() { return this.strCommand; }
    public ItemStack getItem() { return this.objItem; }
    /* setters */
    /* vetters */
    public Boolean vetCommand(String strCommand)  { return this.getCommand().equals(strCommand); }
    public Boolean vetItem(ItemStack objItem)        { return this.getItem().equals(objItem); }
    public Boolean vetItem(MineGuisItem objItem)     { return this.vetItem(objItem.getItem()); }
    /* actions */
    public Boolean doExecute(Player objPlayer) {
        if (objPlayer == null) {
            MineGuis.get().doLog("null argument is passed!");
            return false;
        }
        if (objPlayer.performCommand(this.strCommand) == false) {
            MineGuis.get().doLog("failed to execute the command!");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */