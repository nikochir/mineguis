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
/*
 * MineGuisBook
 * > Description:
 * -> this object handles multiple panels;
 * -> each panel has the same size;
 * -> the size of this book is measured in pages;
 * -> same as the menu it uses indexes from 1;
 */
public class MineGuisBook {
    /* members */
    private final String strTitle;
    private Integer numCurr;
    private List<MineGuisMenuPage> arrPages;
    /* codetor */
    public MineGuisBook(String strTitle, Integer numPages, Integer numLines) {
        this.strTitle = strTitle;
        this.numCurr = 1;
        arrPages = new ArrayList<MineGuisMenuPage>(numPages);
        for (int itr = 1; itr <= numPages; itr++) {
            MineGuisMenuPage objPage = new MineGuisMenuPage(this, itr, numLines);
            arrPages.add(objPage);
            MineGuis.get().getServer().getPluginManager().registerEvents(objPage, MineGuis.get());
        }
    }
    /* getters */
    public String getTitle()                         { return this.strTitle; }
    public Integer getCurrNumb()                     { return this.numCurr; }
    public Integer getSizeOfPages()                  { return getPage(1).getSizeInLines(); }
    public Integer getSizeOfLines()                  { return getPage(1).getSizeOfLines(); }
    public Integer getSizeInPages()                  { return this.arrPages.size(); }
    public Integer getSizeInLines()                  { return this.getSizeInPages() * this.getSizeOfPages(); }
    public Integer getSizeInItems()                  { return this.getSizeInPages() * this.getSizeOfPages() * this.getSizeOfLines(); }
    public MineGuisMenuPage getCurrPage()            { return this.getPage(this.numCurr); }
    public MineGuisMenuPage getPage(Integer numPage) { return this.arrPages.get((numPage - 1) % this.getSizeInPages()); }
    /* setters */
    public Boolean setCurrPage(Integer numPage) {
        this.numCurr = numPage;
        return true;
    }
    public Boolean setCurrPage(MineGuisMenuPage objPage) {
        this.numCurr = objPage.getNumb();
        return true;
    }
    /* vetters */
    public Boolean vetSizeInItems(Integer numItems) { return this.getSizeInItems() >= numItems; }
    public Boolean vetSizeInLines(Integer numLines) { return this.getSizeInLines() >= numLines; }
    public Boolean vetSizeInPages(Integer numPages) { return this.getSizeInPages() >= numPages; }
    /* actions */
    public Boolean doShow(Player objPlayer) {
        if (getCurrPage().doShow(objPlayer) == false) { return false; }
        return true;
    }
    public Boolean doHide(Player objPlayer) {
        if (getCurrPage().doHide(objPlayer) == false) { return false; }
        return true;
    }
    /* handles */
}
/* end_of_file */