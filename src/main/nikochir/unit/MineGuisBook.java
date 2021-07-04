/* package */
package nikochir.unit;
/* import */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
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
    private Integer numCurr;
    /* codetor */
    public MineGuisBook(String strTitle, Integer numSizeInPages, Integer numSizeOfPages) {
        super(strTitle);
        Integer numPagesMin = MineGuis.get().getConfigInt("sizeof_minb");
        Integer numPagesMax = MineGuis.get().getConfigInt("sizeof_maxb");
        if (numSizeInPages <= 0) {
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
        Integer numSizeInSlots = numSizeOfPages * 9;
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
            if (MineGuis.get().vetMenu(itrObjPage) == false) { MineGuis.get().addMenu(itrObjPage); }
            if (MineGuis.get().vetItem(itrObjItemPrev) == false) { MineGuis.get().addItem(itrObjItemPrev); }
            if (MineGuis.get().vetItem(itrObjItemNext) == false) { MineGuis.get().addItem(itrObjItemNext); }
        }
        this.numCurr = 1;
    }
    public MineGuisBook(String strTitle, Integer numSizeInPages) { this(strTitle, numSizeInPages, 6); }
    /* getters */
    public Integer getSizeOfPages()   { return this.tabPages.get(0).getSizeInLines(); }
    public Integer getSizeInPages()   { return this.tabPages.size(); }
    public Integer getSizeOfLines()   { return this.tabPages.get(0).getSizeOfLines(); }
    public Integer getSizeInLines()   { return this.getSizeInPages() * this.getSizeOfPages(); }
    public Integer getSizeOfSlots()   { return this.tabPages.get(0).getSizeOfSlots(); }
    public Integer getSizeInSlots()   { return this.getSizeInPages() * this.getSizeOfPages() * this.getSizeOfLines(); }
    public Integer getNumbCurr()      { return numCurr; }
    public MineGuisMenu getPage()     { return this.tabPages.get(numCurr - 1); }
    public MineGuisMenu getPage(Integer numPage) { return this.tabPages.get(numPage - 1); }
    /* setters */
    public Boolean setNumbCurr(Integer numPage) {
        if (vetSize(numPage) == false) {
            MineGuis.get().doLogO("cannot access this page! setNumbCurr(numPage)");
        }
        this.numCurr = numPage;
        return true;
    }
    public Boolean setItem(MineGuisItem objItem, Integer numSlot) {
        return this.getPage().setItem(objItem, numSlot);
    }
    public Boolean setItem(MineGuisItem objItem, Integer numY, Integer numX) {
        return this.getPage().setItem(objItem, numY, numX);
    }
    public Boolean setItem(MineGuisItem objItem, Integer numP, Integer numY, Integer numX) {
        return this.getPage(numP).setItem(objItem, numY, numX);
    }
    /* vetters */
    public Boolean vetSize(Integer numNumb) { return this.getSizeInPages() >= numNumb; }
    public Boolean vetPage(Integer numPage) { return this.getSizeInPages() >= numPage; }
    /* actions */
    public Boolean doShow(Player objPlayer) { return getPage().doShow(objPlayer); }
    public Boolean doHide(Player objPlayer) { return getPage().doHide(objPlayer); }
    /* handles */
}
/* endfile */