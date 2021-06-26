/* [nikodim]: 2021/05/06; */
/* package */
package nikochir.menu;
/* include */
import nikochir.MineGuis;
import nikochir.menu.MineGuisMenu;
import nikochir.menu.MineGuisMenuBook;
import nikochir.item.MineGuisItem;
import nikochir.item.MineGuisItemQuit;
import nikochir.item.MineGuisItemPage;
import nikochir.item.MineGuisItemPageL;
import nikochir.item.MineGuisItemPageR;
/* bukkit */
import org.bukkit.entity.Player;
/* typedef */
/* MineGuisMenuPage
 * > Description:
 * -> represents a menu linked with a collection of menus;
 */
public class MineGuisMenuPage extends MineGuisMenu {
    /* members */
    private final MineGuisMenuBook objBook;
    private final Integer numNumb;
    /* codetor */
    public MineGuisMenuPage(MineGuisMenuBook objBook, Integer numNumb, Integer numRows) {
        super(String.format("%s: %d/%d", objBook.getTitle(), numNumb, objBook.getSizeInPages() ), numRows);
        this.objBook = objBook;
        this.numNumb = numNumb;
        this.setItem(this.getSizeInItems() - 2, new MineGuisItemPageL(objBook));
        this.setItem(this.getSizeInItems() - 1, new MineGuisItemQuit());
        this.setItem(this.getSizeInItems() - 0, new MineGuisItemPageR(objBook));
    }
    /* getters */
    public MineGuisMenuBook getBook()     { return this.objBook; }
    public Integer getBookSize()          { return this.objBook.getSizeInPages(); }
    public Integer getNumb()              { return this.numNumb; }
    public Integer getNumbPrev()          { return (this.numNumb - 1) % this.getBookSize(); }
    public Integer getNumbNext()          { return (this.numNumb + 1) % this.getBookSize(); }
    public MineGuisMenuPage getPagePrev() { return this.objBook.getPage(this.getNumbPrev()); }
    public MineGuisMenuPage getPageNext() { return this.objBook.getPage(this.getNumbNext()); }
    /* setters */
    /* vetters */
    public Boolean vetNumb(Integer numNumb) { return this.numNumb == numNumb; }
    public Boolean vetNumbCurr()            { return vetNumb(this.getBook().getCurrNumb()); }
    /* actions */
    public Boolean doSwitchNext(Player objPlayer) {
        if (this.vetNumbCurr() == false) { /* this is not the current page */ return false; }
        if (this.doHide(objPlayer) == false) { /* failed to this this */ return false; }
        this.getBook().setCurrPage(this.getPageNext());
        return true;
    }
    public Boolean doSwitchPrev(Player objPlayer) {
        if (this.vetNumbCurr() == false) { /* this is not the current page */ return false; }
        if (this.doHide(objPlayer) == false) { /* failed to this this */ return false; }
        this.getBook().setCurrPage(this.getPagePrev());
        return true;
    }
    /* handles */
}
/* end_of_file */