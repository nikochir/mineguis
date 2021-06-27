/* [nikodim]: 2021/05/06; */
/* package */
package nikochir.menu;
/* include */
import nikochir.MineGuis;
import nikochir.menu.MineGuisMenu;
import nikochir.menu.MineGuisMenuPage;
import nikochir.item.MineGuisItem;
import nikochir.item.MineGuisItemPage;
/* javkit */
import java.util.List;
import java.util.ArrayList;
/* bukkit */
import org.bukkit.entity.Player;
/* typedef */
/* MineGuisMenuBook
 * > Description:
 * -> this object handles multiple panels;
 * -> each panel has the same size;
 * -> the size of this book is measured in pages;
 * -> same as the menu it uses indexes from 1;
 */
public class MineGuisMenuBook {
    /* members */
    private final String strTitle;
    private Integer numCurr;
    private List<MineGuisMenuPage> arrPages;
    /* codetor */
    public MineGuisMenuBook(String strTitle, Integer numPages, Integer numLines) {
        this.strTitle = strTitle;
        this.numCurr = 1;
        arrPages = new ArrayList<MineGuisMenuPage>(numPages);
        for (int itr = 0; itr < numPages; itr++) { arrPages.add(null); }
        for (int itr = 1; itr <= numPages; itr++) {
            MineGuisMenuPage objPage = new MineGuisMenuPage(this, itr, numLines);
            MineGuis.get().addMenu(objPage);
            arrPages.set(itr - 1, objPage);
        }
    }
    /* getters */
    public String  getTitle()                        { return this.strTitle; }
    public Integer getCurrNumb()                     { return this.numCurr; }
    public Integer getSizeOfPages()                  { return this.getPage(1).getSizeInLines(); }
    public Integer getSizeOfLines()                  { return this.getPage(1).getSizeOfLines(); }
    public Integer getSizeInPages()                  { return this.arrPages.size(); }
    public Integer getSizeInLines()                  { return this.getSizeInPages() * this.getSizeOfPages(); }
    public Integer getSizeInItems()                  { return this.getSizeInPages() * this.getSizeOfPages() * this.getSizeOfLines(); }
    public MineGuisMenuPage getCurrPage()            { return this.getPage(this.numCurr); }
    public MineGuisMenuPage getPage(Integer numPage) { return this.arrPages.get((numPage - 1) % this.getSizeInPages()); }
    /* setters */
    public MineGuisMenuBook setCurrPage(Integer numPage)          { this.numCurr = numPage; return this; }
    public MineGuisMenuBook setCurrPage(MineGuisMenuPage objPage) {
        for (int itr = 0; itr < getSizeInPages(); itr++) {
            if (objPage == getPage(itr)) { this.numCurr = itr + 1; }
            this.numCurr = objPage.getNumb();
        }
        return this;
    }
    /* vetters */
    public Boolean vetSizeInItems(Integer numItems) { return this.getSizeInItems() >= numItems; }
    public Boolean vetSizeInLines(Integer numLines) { return this.getSizeInLines() >= numLines; }
    public Boolean vetSizeInPages(Integer numPages) { return this.getSizeInPages() >= numPages; }
    /* actions */
    public Boolean doShow(Player objPlayer) {
        if (this.getCurrPage().doShow(objPlayer) == false) {
            MineGuis.get().doLog("failed to show the current page!");
            return false;
        }
        return true;
    }
    public Boolean doHide(Player objPlayer) {
        if (this.getCurrPage().doHide(objPlayer) == false) {
            MineGuis.get().doLog("failed to hide the current page!");
            return false;
        }
        return true;
    }
    public Boolean doSwitchNext(Player objPlayer) {
        if (this.getCurrPage().doHide(objPlayer) == false) {
            MineGuis.get().doLog("failed to hide the current page!");
            return false;
        }
        if (this.numCurr == getSizeInPages()) { this.numCurr = 1; }
        else { this.numCurr += 1; }
        if (this.getCurrPage().doShow(objPlayer) == false) {
            MineGuis.get().doLog("failed to show the next page!");
            return false;
        }
        return true;
    }
    public Boolean doSwitchPrev(Player objPlayer) {
        if (this.getCurrPage().doHide(objPlayer) == false) {
            MineGuis.get().doLog("failed to hide the current page!");
            return false;
        }
        if (this.numCurr == 1) { this.numCurr = getSizeInPages(); }
        else { this.numCurr -= 1; }
        if (this.getCurrPage().doShow(objPlayer) == false) {
            MineGuis.get().doLog("failed to show the previous page!");
            return false;
        }
        return true;
    }
    /* handles */
}
/* end_of_file */