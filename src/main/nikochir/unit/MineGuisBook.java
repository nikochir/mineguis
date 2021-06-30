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
 * -> counts pages [from 1 to the_number_of_pages];
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
            MineGuis.get().doLog("invalid number of pages!");
            return;
        } else if (numSizeInPages < numPagesMin) {
            MineGuis.get().doLog("too few pages!");
            numSizeInPages = numPagesMin;
        } else if (numSizeInPages > numPagesMax) {
            MineGuis.get().doLog("too many pages!");
            numSizeInPages = numPagesMax;
        }
        this.tabPages = new ArrayList<MineGuisMenu>(numSizeInPages);
        Integer numSizeInSlots = numSizeOfPages * 9;
        for (int itrNumPage = 0; itrNumPage < numSizeInPages; itrNumPage++) {
            String itrStrPage = String.format("%s|%d/%d|", strTitle, itrNumPage - 0, numSizeInPages);
            MineGuisMenu itrObjPage = new MineGuisMenu(itrStrPage, numSizeOfPages);
            String itrStrPagePrev = String.format("mguimenu %s|%d/%d|", strTitle, itrNumPage - 1, numSizeInPages);
            String itrStrPageNext = String.format("%s|%d/%d|", strTitle, itrNumPage + 1, numSizeInPages);
            MineGuisItem itrObjItemPrev = new MineGuisItem(itrStrPagePrev, "left", "switch to the previous page", Material.COMPASS);
            MineGuisItem itrObjItemNext = new MineGuisItem(itrStrPageNext, "right", "switch to the following page", Material.COMPASS);
            MineGuisItem itrObjItemBack = MineGuis.get().getItem("back");
            itrObjPage.setItem(itrObjItemPrev, numSizeInSlots - 3);
            itrObjPage.setItem(itrObjItemBack, numSizeInSlots - 2);
            itrObjPage.setItem(itrObjItemNext, numSizeInSlots - 1);
            this.tabPages.add(itrObjPage);
        }
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
    public MineGuisMenu getPageCurr() { return this.tabPages.get(numCurr - 1); }
    public MineGuisMenu getPage(Integer numPage) { return this.tabPages.get(numPage - 1); }
    /* setters */
    public Boolean setNumbCurr(Integer numPage) {
        if (vetSize(numPage) == false) {
            MineGuis.get().doLog("cannot access this page! setNumbCurr(numPage)");
        }
        this.numCurr = numPage;
        return true;
    }
    /* vetters */
    public Boolean vetSize(Integer numNumb) { return this.getSizeInPages() >= numNumb; }
    /* actions */
    public Boolean doShow(Player objPlayer) { return getPageCurr().doShow(objPlayer); }
    public Boolean doHide(Player objPlayer) { return getPageCurr().doHide(objPlayer); }
    /* handles */
}
/* endfile */