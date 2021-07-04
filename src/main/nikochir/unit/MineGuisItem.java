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
 * -> Exec is the actual command to execute;
*/
public class MineGuisItem extends MineGuisUnit {
    /* members */
    private final String strExec;
    private final ItemStack objItem;
    /* codetor */
    public MineGuisItem(String strName, String strLore, Material valIcon, String strExec) {
        super(strName);
        this.strExec = strExec;
        this.objItem = new ItemStack(valIcon);
        ItemMeta objMeta = this.objItem.getItemMeta();
        objMeta.displayName(Component.text(strName));
        if (strLore != "") {
            List<Component> arrLore = new ArrayList<Component>(1);
            arrLore.add(Component.text(strLore));
            objMeta.lore(arrLore);
        }
        this.objItem.setItemMeta(objMeta);
    }
    public MineGuisItem(String strName, String strLore,String strIcon, String strExec) {
        this(strName, strLore, Material.matchMaterial(strIcon), strExec);
    }
    public MineGuisItem(String strName, String strLore, String strExec) {
        this(strName, strLore, Material.COMMAND_BLOCK, strExec);
    }
    public MineGuisItem(String strName, Material valIcon, String strExec) {
        this(strName, "", valIcon, strExec);
    }
    public MineGuisItem(String strName, String strExec) {
        this(strName, "", Material.COMMAND_BLOCK, strExec);
    }
    public MineGuisItem(String strName) {
        this(strName, "", Material.COMMAND_BLOCK, "mguivoid");
    }
    /* getters */
    public String getExec()    { return this.strExec; }
    public ItemStack getItem() { return this.objItem; }
    /* setters */
    public Boolean setLore(String strLore) {
        ItemMeta objMeta = this.objItem.getItemMeta();
        List<Component> arrObjLore = new ArrayList<Component>(1);
        arrObjLore.add(Component.text(strLore));
        objMeta.lore(arrObjLore);
        return true;
    }
    public Boolean setLore(List<String> arrStrLore) {
        ItemMeta objMeta = this.objItem.getItemMeta();
        List<Component> arrObjLore = new ArrayList<Component>(arrStrLore.size());
        for (String strLore : arrStrLore) { arrObjLore.add(Component.text(strLore)); }
        objMeta.lore(arrObjLore);
        return true;
    }
    /* vetters */
    public Boolean vetExec(String strExec)       { return this.getExec().equals(strExec); }
    public Boolean vetItem(ItemStack objItem)    { return this.getItem().equals(objItem); }
    public Boolean vetItem(MineGuisItem objItem) { return this.vetItem(objItem.getItem()); }
    /* actions */
    public Boolean doExec(Player objPlayer) {
        if (objPlayer == null) {
            MineGuis.get().doLogO("null argument is passed! doExecute(objPlayer);");
            return false;
        }
        if (objPlayer.performCommand(this.getExec()) == false) {
            MineGuis.get().doLogO("failed to execute the command! doExecute(objPlayer);");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */