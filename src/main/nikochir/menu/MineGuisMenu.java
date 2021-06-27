/* package */
package nikochir.menu;
/* include */
import nikochir.MineGuis;
import nikochir.item.MineGuisItem;
import nikochir.item.MineGuisItemBack;
import nikochir.item.MineGuisItemMenu;
import nikochir.item.MineGuisItemCell;
import nikochir.item.MineGuisItemCall;
/** javkit **/
import java.util.List;
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
/** nkyori **/
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
/* MineGuisMenu class
 * > Description:
 * -> base class for any gui panel/menu;
 * -> althought minecraft counts from 1,
 * programmers are used to start from 0;
 * -> but this class counts from 1;
 * -> if the index is more than the entire size:
 * --> it wraps around using modulus operator %;
*/
public class MineGuisMenu implements Listener {
    /* members */
    private final String strTitle;
    private Inventory objPack;
    private ArrayList<MineGuisItem> arrItems;
    /* codetor */
    public MineGuisMenu(String strTitle, Integer numRows) {
        if (numRows < MineGuis.get().getConfigInt("sizeof_minm")) {
            numRows = MineGuis.get().getConfigInt("sizeof_minm");
        }
        if (numRows > MineGuis.get().getConfigInt("sizeof_maxm")) {
            numRows = MineGuis.get().getConfigInt("sizeof_maxm");
        }
        this.strTitle = strTitle;
        this.objPack = Bukkit.createInventory(null, numRows * 9, Component.text(strTitle));
        this.arrItems = new ArrayList<MineGuisItem>(numRows * 9);
        for (int itr = 1; itr <= (numRows * 9); itr++) {
            MineGuisItem objItem = new MineGuisItemCell();
            this.arrItems.add(objItem);
            this.objPack.setItem((itr) % (numRows * 9), objItem.getItem());
        }
        this.setItem(getSizeInLines(), 1, new MineGuisItemBack());
        MineGuis.get().addMenu(this);
    }
    /* getters */
    public String       getTitle()       { return this.strTitle; }
    public Integer      getSizeOfLines() { return 9; }
    public Integer      getSizeInItems() { return this.objPack.getSize(); }
    public Integer      getSizeInLines() { return getSizeInItems() / getSizeOfLines(); }
    public Inventory    getPack()                           { return this.objPack; }
    public ItemStack    getPackItem(int numSlot)            { return this.objPack.getItem( (numSlot - 1) % this.getSizeInItems()); }
    public ItemStack    getPackItem(int numRow, int numCol) { return this.objPack.getItem( ((numRow - 1) * 9 + numCol - 1) % this.getSizeInItems() ); }
    public MineGuisItem getItem(int numSlot)                { return this.arrItems.get( (numSlot - 1) % this.getSizeInItems() ); }
    public MineGuisItem getItem(int numRow, int numCol)     { return this.arrItems.get( ((numRow - 1) * 9 + numCol - 1) % this.getSizeInItems() ); }
    /* setters */
    public MineGuisMenu setItem(Integer numSlot, MineGuisItem objItem) {
        this.objPack.setItem( (numSlot - 1) % this.getSizeInItems(), objItem.getItem() );
        this.arrItems.set( (numSlot - 1) % this.getSizeInItems(), objItem );
        return this;
    }
    public MineGuisMenu setItem(Integer numRow, Integer numCol, MineGuisItem objItem) {
        this.objPack.setItem( ((numRow - 1) * 9 + numCol - 1) % this.getSizeInItems(), objItem.getItem() );
        this.arrItems.set( ((numRow - 1) * 9 + numCol - 1) % this.getSizeInItems(), objItem );
        return this;
    }
    public MineGuisMenu setItems(ArrayList<MineGuisItem> arrItems) {
        for (int itr = 1; itr <= arrItems.size(); itr++) { setItem(itr, arrItems.get(itr)); }
        return this;
    }
    /* vetters */
    public Boolean vetTitle(String strTitle)        { return this.getTitle().equals(strTitle); }
    public Boolean vetSizeInItems(Integer numItems) { return this.getSizeInItems() >= numItems; }
    public Boolean vetSizeInLines(Integer numLines) { return this.getSizeInLines() >= numLines; }
    public Boolean vetPack(Inventory objPack)       { return this.objPack.equals(objPack); }
    public Boolean vetPackItem(ItemStack objItem)   { return this.objPack.contains(objItem); }
    public Boolean vetItem(MineGuisItem objItem)    { return this.arrItems.contains(objItem); }
    /* actions */
    public Boolean doShow(Player objPlayer) {
        if (objPlayer == null) { return false; }
        if ((objPlayer.getOpenInventory() instanceof PlayerInventory) == true) {
            objPlayer.closeInventory();
        }
        //if (vetPack(objPlayer.getInventory()) == true) {
        //    MineGuis.get().doLog("the inventory is already shown!");
        //    return false;
        //}
        objPlayer.openInventory(getPack());
        return true;
    }
    public Boolean doHide(Player objPlayer) {
        if (objPlayer == null) { return false; }
        //if (vetPack(objPlayer.getInventory()) == false) {
        //    MineGuis.get().doLog("the inventory is already hidden!");
        //    return false;
        //}
        objPlayer.closeInventory();
        return true;
    }
    public Boolean doBack(Player objPlayer) {
        if (this.doHide(objPlayer) == false) {
            MineGuis.get().doLog("cannot switch the menu back! null argument exception!");
            return false;
        }
        if (MineGuis.get().getUser(objPlayer).getMenuLast().doShow(objPlayer) == false) {
            MineGuis.get().doLog("cannot switch the menu back! back menu failed at \"show\"!");
            return false;
        }
        return true;
    }
    /* handles */
    @EventHandler
    public void onClick(InventoryClickEvent objEvent) {
        if (vetTitle(objEvent.getView().getTitle()) == false) {
            //MineGuis.get().doLog("not that menu!");
            return;
        }
        /* we do not need to change this inventory */
        objEvent.setCancelled(true);
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            MineGuis.get().doLog("not a player!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        if (objEvent.getCurrentItem() == null) { return; }
        ItemStack objItem = objEvent.getCurrentItem();
        /* get meta data, make sure it exists */
        if (objItem.hasItemMeta() == false) { return; }
        ItemMeta objMeta = objItem.getItemMeta();
        /* get the name, make sure it exists */
        if (objMeta.hasDisplayName() == false) { return; }
        String strName = objMeta.getDisplayName();
        for (int itr = 1; itr <= getSizeInItems(); itr++) { /* check all items */
            MineGuisItem itrItem = getItem(itr);
            if (itrItem == null) { continue; }
            else if (itrItem.vetName(strName)) { itrItem.onClick(objEvent); }
        }
    }
}
/* end_of_file */