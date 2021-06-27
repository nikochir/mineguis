/* package */
package nikochir.item;
/* include */
import nikochir.MineGuis;
/** javkit - standard utilities **/
import java.util.List;
import java.util.ArrayList;
/** bukkit - for events, and item stuff **/
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
/** nkyori - for text facilities **/
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
/* typedef */
/* MineGuisItem class
 * > Description:
 * -> Base class for any gui panel/menu;
*/
public abstract class MineGuisItem implements Listener {
    /* members */
    private ItemStack objItem;
    /* codetor */
    public MineGuisItem(Material valMaterial, String strName, String strLore) {
        objItem = new ItemStack(valMaterial);
        ItemMeta objMeta = objItem.getItemMeta();
        objMeta.setDisplayName(strName);
        List<String> arrLore = new ArrayList<String>();
        arrLore.add(strLore);
        objMeta.setLore(arrLore); /* this guy accepts only List<String> */
        objItem.setItemMeta(objMeta);
    }
    public MineGuisItem(Material valMaterial, String strName, String strLore[]) {
        objItem = new ItemStack(valMaterial);
        ItemMeta objMeta = objItem.getItemMeta();
        objMeta.setDisplayName(strName);
        List<String> arrLore = new ArrayList<String>();
        for (int itr = 0; itr < strLore.length; itr++) { arrLore.add(strLore[itr]); }
        objMeta.setLore(arrLore); /* this guy accepts only List<String> */
        objItem.setItemMeta(objMeta);
    }
    /* getters */
    public ItemStack    getItem() { return objItem; }
    public Material     getType() { return objItem.getType(); }
    public ItemMeta     getMeta() { return objItem.getItemMeta(); }
    public String       getName() { return objItem.getItemMeta().getDisplayName(); }
    public List<String> getLore() { return objItem.getItemMeta().getLore(); }
    public List<String> getDesc() { return objItem.getItemMeta().getLore(); }
    /* setters */
    /* vetters */
    public Boolean vetName(String strName)    { return getName().equalsIgnoreCase(strName); }
    public Boolean vetType(Material valMaterial)  { return getType().equals(valMaterial); }
    public Boolean vetMeta(ItemMeta objMeta)  { return getMeta().equals(objMeta); }
    public Boolean vetItem(ItemStack objItem) { return getItem().equals(objItem); }
    /* actions */
    /* handles */
    public abstract void onClick(InventoryClickEvent objEvent);
}
/* end_of_file */