/* package */
package nikochir.kernel;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisUnit;
import nikochir.kernel.MineGuisItem;
import nikochir.kernel.MineGuisMenu;
/* javkit */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String strExec;
    private ItemStack objItem;
    /* codetor */
    public MineGuisItem(String strName, String[] arrStrLore, Material valIcon, String strExec) {
        super(String.format("%s|%s|%s", strName, valIcon.toString(), strExec));
        this.strExec = strExec;
        this.objItem = new ItemStack(valIcon);
        ItemMeta objMeta = this.objItem.getItemMeta();
        objMeta.displayName(Component.text(strName));
        if (arrStrLore != null) {
            List<Component> arrLore = new ArrayList<Component>(arrStrLore.length);
            for (String strLore : arrStrLore) { arrLore.add(Component.text(strLore)); }
            objMeta.lore(arrLore);
        }
        this.objItem.setItemMeta(objMeta);
    }
    public MineGuisItem(String strName, String[] arrStrLore, String strIcon, String strExec) {
        this(strName, arrStrLore, Material.matchMaterial(strIcon), strExec);
    }
    public MineGuisItem(String strName, String[] arrStrLore, String strExec) {
        this(strName, arrStrLore, Material.COMMAND_BLOCK, strExec);
    }
    public MineGuisItem(String strName, List<String> arrStrLore, Material valIcon, String strExec) {
        super(String.format("%s|%s|%s", strName, valIcon.toString(), strExec));
        this.strExec = strExec;
        this.objItem = new ItemStack(valIcon);
        ItemMeta objMeta = this.objItem.getItemMeta();
        objMeta.displayName(Component.text(strName));
        if (arrStrLore != null) {
            List<Component> arrLore = new ArrayList<Component>(arrStrLore.size());
            for (String strLore : arrStrLore) { arrLore.add(Component.text(strLore)); }
            objMeta.lore(arrLore);
        }
        this.objItem.setItemMeta(objMeta);
    }
    public MineGuisItem(String strName, List<String> arrStrLore, String strIcon, String strExec) {
        this(strName, arrStrLore, Material.matchMaterial(strIcon), strExec);
    }
    public MineGuisItem(String strName, List<String> arrStrLore, String strExec) {
        this(strName, arrStrLore, Material.COMMAND_BLOCK, strExec);
    }
    public MineGuisItem(String strName, String strLore, Material valIcon, String strExec) {
        super(String.format("%s|%s|%s", strName, valIcon.toString(), strExec));
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
    public MineGuisItem(String strName, String strLore, String strIcon, String strExec) {
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
    public String getName()       { return this.objItem.getItemMeta().getDisplayName(); }
    public List<String> getLore() { return this.objItem.getLore(); }
    public String getIcon()       { return this.objItem.getType().toString(); }
    public String getExec()       { return this.strExec; }
    public ItemStack getItem()    { return this.objItem; }
    public ItemMeta getMeta()     { return this.objItem.getItemMeta(); }
    /* setters */
    public boolean setName(String strName)          { this.objItem.getItemMeta().setDisplayName(strName); return true; }
    public boolean setLore(String strLore)          { return this.setLore(new String[]{ strLore }); }
    public boolean setLore(String[] arrStrLore)     { return this.setLore(Arrays.asList(arrStrLore)); }
    public boolean setLore(List<String> arrStrLore) { this.objItem.setLore(arrStrLore); return true; }
    public boolean setIcon(Material valIcon)        { this.objItem.setType(valIcon); return true; }
    public boolean setIcon(String strIcon)          { return this.setIcon(Material.matchMaterial(strIcon)); }
    public boolean setExec(String strExec)          { this.strExec = strExec; return true; }
    /* vetters */
    public boolean vetName(String strName)          { return this.getName().equals(strName); }
    public boolean vetLore(List<String> arrStrLore) { return this.getLore().equals(arrStrLore); }
    public boolean vetLore(String[] arrStrLore)     { return this.vetLore(Arrays.asList(arrStrLore)); }
    public boolean vetLore(String strLore)          { return this.vetLore(new String[]{ strLore }); }
    public boolean vetIcon(Material valIcon)        { return this.getIcon().equals(valIcon.toString()); }
    public boolean vetIcon(String strIcon)          { return this.getIcon().equals(strIcon); }
    public boolean vetExec(String strExec)          { return this.getExec().equals(strExec); }
    public boolean vetItem(ItemStack objItem)       { return this.getItem().equals(objItem); }
    public boolean vetItem(MineGuisItem objItem)    { return this.vetItem(objItem.getItem()); }
    /* actions */
    public boolean doExec(Player objPlayer) {
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