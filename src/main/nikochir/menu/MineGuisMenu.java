/* package */
package nikochir.menu;
/* include */
import nikochir.MineGuis;
import nikochir.item.*;
/** javkit **/
import java.util.List;
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
/** nkyori **/
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
/*
 * MineGuisMenu class
 * > Description:
 * -> base class for any gui panel/menu;
 * -> althought minecraft counts from 1,
 * programmers are used to start from 0;
*/
public class MineGuisMenu implements Listener {
    /* members */
    private String strTitle;
    private Inventory objPack;
    private ArrayList<MineGuisItem> arrItems;
    private MineGuisItem objMenuPrev;
    /* codetor */
    public MineGuisMenu(Integer numRows, String strTitle) { this(null, numRows, strTitle); }
    public MineGuisMenu(InventoryHolder objHolder, Integer numRows, String strTitle) {
        this.strTitle = strTitle;
        this.arrItems = new ArrayList<MineGuisItem>(numRows * 9);
        for (int itr = 0; itr < (numRows * 9); itr++) { this.arrItems.add(new MineGuisItemCell()); }
        this.objPack = Bukkit.createInventory(objHolder, numRows * 9, Component.text(strTitle));
    }
    /* getters */
    public String       getTitle()                          { return this.strTitle; }
    public Inventory    getPack()                           { return this.objPack; }
    public ItemStack    getPackItem(int numSlot)            { return this.objPack.getItem(numSlot); }
    public ItemStack    getPackItem(int numRow, int numCol) { return this.objPack.getItem(numRow * 9 + numCol % 9); }
    public Integer      getPackSize()                       { return this.objPack.getSize(); }
    public MineGuisItem getItem(int numSlot)                { return this.arrItems.get(numSlot); }
    public MineGuisItem getItem(int numRow, int numCol)     { return this.arrItems.get(numRow * 9 + numCol % 9); }
    public Integer      getItemSize()                       { return this.arrItems.size(); }
    /* setters */
    public MineGuisMenu setItem(Integer numSlot, MineGuisItem objItem) {
        MineGuis.get().doLog("packing an item: " + objItem.getName());
        objPack.setItem(numSlot % getPackSize(), objItem.getItem());
        MineGuis.get().doLog("setting an item: " + objItem.getName());
        arrItems.set(numSlot % getItemSize(), objItem);
        return this;
    }
    public MineGuisMenu setItem(Integer numRow, Integer numCol, MineGuisItem objItem) {
        return this.setItem(numRow * 9 + numCol, objItem);
    }
    public MineGuisMenu setItems(ArrayList<MineGuisItem> arrItems) {
        for (int itr = 0; itr < arrItems.size(); itr++) { setItem(itr, arrItems.get(itr)); }
        return this;
    }
    /* vetters */
    public Boolean vetPack(Inventory objPack) { return getPack().equals(objPack); }
    public Boolean vetTitle(String strTitle)  { return getTitle().equals(strTitle); }
    /* command */
    public Boolean doShow(Player objPlayer) {
        if (objPlayer == null) { return false; }
        if (vetPack(objPlayer.getInventory()) == false) {
            objPlayer.openInventory(getPack());
        } else { /* the current inventory is already shown */
            return false;
        }
        return false;
    }
    public Boolean doHide(Player objPlayer) {
        if (objPlayer == null) { return false; }
        if (vetPack(objPlayer.getInventory()) == true) {
            objPlayer.closeInventory();
            return true;
        } else { /* this is not the current inventory */
            return false;
        }
    }
    /* onevent */
    @EventHandler
    public void onClick(InventoryClickEvent objEvent) {
        if (vetTitle(objEvent.getView().getTitle()) == true) {
            /* we do not need to change this */
            objEvent.setCancelled(true);
            if (objEvent.getWhoClicked() instanceof Player) {
                Player objPlayer = (Player) objEvent.getWhoClicked();
                if (objEvent.getCurrentItem() == null) { return; }
                ItemStack objItem = objEvent.getCurrentItem();
                /* get meta data, make sure it exists */
                if (objItem.hasItemMeta() == false) { return; }
                ItemMeta objMeta = objItem.getItemMeta();
                /* get the name, make sure it exists */
                if (objMeta.hasDisplayName() == false) { return; }
                String strName = objMeta.getDisplayName();
                for (int itr = 0; itr < getItemSize(); itr++) { /* check all items */
                    MineGuisItem itrItem = arrItems.get(itr);
                    if (itrItem == null) { continue; }
                    else if (itrItem.vetName(strName)) { itrItem.onClick(objEvent); }
                }
            } else { /* this is not a player */
            }
        } else { /* this is not "this" menu */
        }
    }
}
/* end_of_file */