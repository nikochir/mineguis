/* package */
package nikochir.kernel;
/* import */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisUnit;
import nikochir.kernel.MineGuisItem;
import nikochir.kernel.MineGuisMenu;
/** javkit **/
import java.util.List;
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.Material;
/* typedef */
/* MineGuisBook class
 * > Description:
 * -> set of menu objects;
 * -> every menu is a page;
 * -> automatically sets enumerated names for all pages;
 * -> counts pages [from 1 to the_number_of_pages];
 * -> coordinates are considered from greater to lesser:
 * --> [z, y, x] is used because this is how we use counting systems;
 * --> page is z, row is y, column is x;
 * --> 100 = z, 10 = y, x = 1;
*/
public class MineGuisBook extends MineGuisUnit {
    /* members */
    private List<MineGuisMenu> tabPages;
    /* codetor */
    public MineGuisBook(String strTitle, int numSizeInPages, int numSizeOfPages) {
        super(strTitle);
        int numPagesMin = MineGuis.get().getConfigInt("sizeof_minb");
        int numPagesMax = MineGuis.get().getConfigInt("sizeof_maxb");
        if (numSizeInPages == 0) {
            MineGuis.get().doLogO("invalid number of pages!");
            return;
        } else if (numSizeInPages < numPagesMin) {
            MineGuis.get().doLogO("too few pages!");
            numSizeInPages = numPagesMin;
        } else if (numSizeInPages > numPagesMax) {
            MineGuis.get().doLogO("too many pages!");
            numSizeInPages = numPagesMax;
        }
        this.tabPages = new ArrayList<MineGuisMenu>(numSizeInPages);
        int numSizeInSlots = numSizeOfPages * 9;
        for (int itrNumPage = 1; itrNumPage <= numSizeInPages; itrNumPage++) {
            String itrStrPage = String.format("%s|%d/%d|", strTitle, itrNumPage, numSizeInPages);
            MineGuisMenu itrObjPage = new MineGuisMenu(itrStrPage, numSizeOfPages);
            String itrStrPagePrev = String.format("%s|%d/%d|", strTitle, itrNumPage > 1 ? itrNumPage - 1 : numSizeInPages, numSizeInPages);
            String itrStrPageNext = String.format("%s|%d/%d|", strTitle, itrNumPage < numSizeInPages ? itrNumPage + 1 : 1, numSizeInPages);
            MineGuisItem itrObjItemPrev = new MineGuisItem(itrStrPagePrev, "previous page", Material.COMPASS, "mguimenu " + itrStrPagePrev);
            MineGuisItem itrObjItemNext = new MineGuisItem(itrStrPageNext, "following page", Material.COMPASS, "mguimenu " + itrStrPageNext);
            MineGuisItem itrObjItemBack = MineGuis.get().getItem("back");
            itrObjPage.setItem(itrObjItemPrev, numSizeInSlots - 3);
            itrObjPage.setItem(itrObjItemBack, numSizeInSlots - 2);
            itrObjPage.setItem(itrObjItemNext, numSizeInSlots - 1);
            this.tabPages.add(itrObjPage);
            if (MineGuis.get().vetMenu(itrObjPage) == false)     { MineGuis.get().addMenu(itrObjPage); }
            if (MineGuis.get().vetItem(itrObjItemPrev) == false) { MineGuis.get().addItem(itrObjItemPrev); }
            if (MineGuis.get().vetItem(itrObjItemNext) == false) { MineGuis.get().addItem(itrObjItemNext); }
        }
    }
    public MineGuisBook(String strTitle, int numSizeInPages) { this(strTitle, numSizeInPages, MineGuis.get().getConfigInt("sizeof_usem")); }
    public MineGuisBook(String strTitle) { this(strTitle, MineGuis.get().getConfigInt("sizeof_useb"), MineGuis.get().getConfigInt("sizeof_usem")); }
    /* getters */
    public int getSizeOfPages() { return this.tabPages.get(0).getSizeInLines(); }
    public int getSizeInPages() { return this.tabPages.size(); }
    public int getSizeOfLines() { return this.tabPages.get(0).getSizeOfLines(); }
    public int getSizeInLines() { return this.getSizeInPages() * this.getSizeOfPages(); }
    public int getSizeOfSlots() { return this.tabPages.get(0).getSizeOfSlots(); }
    public int getSizeInSlots() { return this.getSizeInPages() * this.getSizeOfPages() * this.getSizeOfLines(); }
    public MineGuisMenu getPage()            { return this.tabPages.get(0); }
    public MineGuisMenu getPage(int numPage) { return this.tabPages.get(numPage - 1); }
    /* setters */
    public boolean setItem(MineGuisItem objItem, int numSlot) {
        return this.getPage().setItem(objItem, numSlot);
    }
    public boolean setItem(MineGuisItem objItem, int numY, int numX) {
        return this.getPage().setItem(objItem, numY, numX);
    }
    public boolean setItem(MineGuisItem objItem, int numP, int numY, int numX) {
        return this.getPage(numP).setItem(objItem, numY, numX);
    }
    public boolean setItem(MineGuisItem objItem, int[] arrNums) {
        if (arrNums.length == 0) {
            MineGuis.get().doLogO(
                "no coordinate arguments provided! MineGuisBook.setItem(objItem, arrNums);"
            );
            return false;
        } else if (arrNums.length == 1) {
            return this.setItem(objItem, arrNums[0]);
        } else if (arrNums.length == 2) {
            return this.setItem(objItem, arrNums[0], arrNums[1]);
        } else if (arrNums.length == 3) {
            return this.setItem(objItem, arrNums[0], arrNums[1], arrNums[2]);
        } else {
            MineGuis.get().doLogO(
                "invalid argument count: %d! MineGuisBook.setItem(objItem, arrNums);",
                arrNums.length
            );
            return false;
        }
    }
    public boolean setItem(MineGuisItem objItem, List<Integer> arrNums) {
        if (arrNums.size() == 0) {
            MineGuis.get().doLogO(
                "no coordinate arguments provided! MineGuisBook.setItem(objItem, arrNums);"
            );
            return false;
        } else if (arrNums.size() == 1) {
            return this.setItem(objItem, arrNums.get(0));
        } else if (arrNums.size() == 2) {
            return this.setItem(objItem, arrNums.get(0), arrNums.get(1));
        } else if (arrNums.size() == 3) {
            return this.setItem(objItem, arrNums.get(0), arrNums.get(1), arrNums.get(2));
        } else {
            MineGuis.get().doLogO(
                "invalid argument count : %d! MineGuisBook.setItem(objItem, arrNums);",
                arrNums.size()
            );
            return false;
        }
    }
    /* vetters */
    public boolean vetSize(int numNumb) { return this.getSizeInPages() >= numNumb; }
    public boolean vetPage(int numPage) { return this.getSizeInPages() >= numPage; }
    /* actions */
    public boolean doShow(Player objPlayer) { return getPage().doShow(objPlayer); }
    public boolean doHide(Player objPlayer) { return getPage().doHide(objPlayer); }
    /* handles */
}
/* endfile */