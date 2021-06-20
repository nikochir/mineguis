/* package */
package nikochir.menu;
/* include */
import nikochir.item.*;
/** javkit **/
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
/* MineGuisMenu class
 * description:
 * > base class for any gui panel/menu;
*/
public class MineGuisMenu implements Listener {
    /* members */
    private Inventory objInven;
    private ArrayList<MineGuisItem> arrItems;
    private final String strTitle;
    /* codetor */
    public MineGuisMenu(Integer numSize, String strTitle) {
        this.arrItems = new ArrayList<MineGuisItem>(numSize);
        this.objInven = Bukkit.createInventory(null, numSize, strTitle);
        this.strTitle = strTitle;
    }
    public MineGuisMenu(InventoryHolder objHolder, Integer numSize, String strTitle) {
        this.arrItems = new ArrayList<MineGuisItem>(numSize);
        this.objInven = Bukkit.createInventory(objHolder, numSize, strTitle);
        this.strTitle = strTitle;
    }
    /* getters */
    public Inventory               getInven()               { return this.objInven; }
    public Integer                 getInvenSize()           { return this.objInven.getSize(); }
    public MineGuisItem            getItem(Integer numSlot) { return this.arrItems.get(numSlot); }
    public ArrayList<MineGuisItem> getItems()               { return this.arrItems; }
    public Integer                 getItemSize()            { return this.arrItems.size(); }
    public String                  getTitle()               { return this.strTitle; }
    /* setters */
    public MineGuisMenu setItem(Integer numSlot, MineGuisItem objItem) {
        arrItems.set(numSlot, objItem);
        objInven.setItem(numSlot, objItem.getItem());
        return this;
    }
    public MineGuisMenu setItems(ArrayList<MineGuisItem> arrItems) {
        for (int itr = 0; itr < arrItems.size(); itr++) {
            setItem(itr, arrItems.get(itr));
        }
        return this;
    }
    /* vetters */
    public Boolean vetInven(Inventory objInven) { return getInven().equals(objInven); }
    public Boolean vetTitle(String strTitle) { return getTitle().equalsIgnoreCase(strTitle); }
    /* command */
    /* onevent */
    @EventHandler
    public void onClick(InventoryClickEvent objEvent) {
        /* init */
        if ((objEvent.getWhoClicked() instanceof Player) == false) { return; }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        if (objEvent.getView().getTitle() == getTitle()) { return; }
        ItemStack objItem = objEvent.getCurrentItem();
        /** get meta data, make sure it exists **/
        if (objItem.hasItemMeta() == false) { return; }
        ItemMeta objMeta = objItem.getItemMeta();
        /** get the name, make sure it exists **/
        if (objMeta.hasDisplayName() == false) { return; }
        String strName = objMeta.getDisplayName();
        /* work */
        objEvent.setCancelled(true);
        for (int itr = 0; itr < getItemSize(); itr++) {
            if (arrItems.get(itr).vetName(strName)) {
                arrItems.get(itr).onClick(objEvent);
            }
        }
        /* quit */
    }
}
/* end_of_file */