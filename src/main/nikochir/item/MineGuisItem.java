/* package */
package nikochir.item;
/* include */
import nikochir.menu.*;
/** javkit **/
import java.util.List;
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
/* MineGuisItem class
 * description:
 * > base class for any gui panel/menu;
*/
public abstract class MineGuisItem implements Listener {
    /* members */
    private ItemStack objItem;
    /* codetor */
    public MineGuisItem(Material valType, String strName, ArrayList<String> strLore) {
        objItem = new ItemStack(valType);
        ItemMeta objMeta = objItem.getItemMeta();
        objMeta.setDisplayName(strName);
        objMeta.setLore(strLore);
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
    public Boolean vetType(Material valType)  { return getType().equals(valType); }
    public Boolean vetMeta(ItemMeta objMeta)  { return getMeta().equals(objMeta); }
    public Boolean vetItem(ItemStack objItem) { return getItem().equals(objItem); }
    /* command */
    /* onevent */
    @EventHandler
    public abstract void onClick(InventoryClickEvent objEvent);
}
/* end_of_file */