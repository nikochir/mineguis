/* package */
package nikochir.unit;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisItem;
/* javkit */
import java.util.List;
import java.util.ArrayList;
/* bukkit */
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
/* typedef */
/* MineGuisMenu class
* > Description:
* -> counts cells [from 1 to 9];
*/
public class MineGuisMenu extends MineGuisUnit {
    /* members */
    private Inventory objPack;
    private List<MineGuisItem> tabItems;
    /* codetor */
    public MineGuisMenu(String strTitle, Integer numSizeInLines) {
        super(strTitle);
        Integer numLinesMin = MineGuis.get().getConfigInt("sizeof_minm");
        Integer numLinesMax = MineGuis.get().getConfigInt("sizeof_maxm");
        if (numSizeInLines <= 0) {
            MineGuis.get().doLog("invalid number of lines!");
            return;
        } else if (numSizeInLines < numLinesMin) {
            MineGuis.get().doLog("too few lines!");
            numSizeInLines = numLinesMin;
        } else if (numSizeInLines > numLinesMax) {
            MineGuis.get().doLog("too many lines!");
            numSizeInLines = numLinesMax;
        }
        this.objPack = Bukkit.createInventory(null, numSizeInLines * 9, getSign());
        this.tabItems = new ArrayList<MineGuisItem>(numSizeInLines * 9);
        for (int itr = 0; itr < this.getSizeInSlots(); itr++) {
            this.tabItems.add(MineGuis.get().<MineGuisItem>getUnit("void"));
            this.objPack.setItem(itr, this.tabItems.get(itr).getItem());
        }
    }
    /* getters */
    public int getSizeOfSlots() { return 1; }
    public int getSizeOfLines() { return 9; }
    public int getSizeInSlots() { return this.objPack.getSize(); }
    public int getSizeInLines() { return this.getSizeInSlots(); }
    public MineGuisItem getItem(int numSlot)        { return this.tabItems.get((numSlot - 1) % this.getSizeOfLines()); }
    public MineGuisItem getItem(int numY, int numX) { return this.getItem((numY - 1) * this.getSizeInLines() + numX - 1); }
    /* setters */
    public Boolean setItem(MineGuisItem objItem, int numSlot) {
        if (objItem == null) {
            MineGuis.get().doLog("null argument! setItem(null, numSlot);");
            return false;
        }
        this.tabItems.set((numSlot - 1) % getSizeInSlots(), objItem);
        this.objPack.setItem((numSlot - 1) % getSizeInSlots(), objItem.getItem());
        return true;
    }
    public Boolean setItem(MineGuisItem objItem, int numY, int numX) {
        return this.setItem(objItem, (numY - 1) * getSizeInLines() + numX);
    }
    /* vetters */
    public Boolean vetPack(Inventory objPack)     { return this.objPack == objPack; }
    public Boolean vetView(InventoryView objView) { return vetSign(objView.getTitle()); }
    /* actions */
    public Boolean doShow(Player objPlayer) {
        if (objPlayer == null) {
            MineGuis.get().doLog("null argument! doShow(null);");
            return false;
        }
        if ((objPlayer.getInventory() instanceof PlayerInventory) == true) {
            MineGuis.get().doLog("the inventory is already shown!");
            return false;
        }
        objPlayer.openInventory(this.objPack);
        return true;
    }
    public Boolean doShow(MineGuisUser objUser) {
        if (doShow(objUser.getPlayer()) == false) {
            MineGuis.get().doLog("failed to show the menu!");
            return false;
        }
        objUser.setMenuCurr(this);
        return true;
    }
    public Boolean doHide(Player objPlayer) {
        if (objPlayer == null) {
            MineGuis.get().doLog("null argument! doHide(null);");
            return false;
        }
        if ((objPlayer.getInventory() instanceof PlayerInventory) == false) {
            MineGuis.get().doLog("the inventory is already hidden!");
            return false;
        }
        objPlayer.closeInventory();
        return true;
    }
    public Boolean doHide(MineGuisUser objUser) {
        if (doHide(objUser.getPlayer()) == false) {
            MineGuis.get().doLog("failed to hide the menu!");
            return false;
        }
        objUser.setMenuLast(this);
        return true;
    }
    /* handles */
}
/* endfile */