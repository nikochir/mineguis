/* package */
package nikochir.kernel;
/* import */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
/** javkit **/
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
/* typedef */
/* Book class
 * > Description:
 * -> counts pages [from 1 to the_number_of_pages];
 * -> coordinates are considered from greater to lesser:
 * --> [z, y, x] is used because this is how we use counting systems;
 * --> page is z, row is y, column is x;
 * --> 100 = z, 10 = y, x = 1;
*/
public class Book extends Unit {
    /* members */
    static private HashMap<String, Book> tab;
    private List<Menu> arrPages;
    /* codetor */
    protected Book(String strTitle, int numSizeInPages, int numSizeOfPages) {
        super(strTitle);
        int numPagesMin = Main.get().getConfigInt("sizeof_minb");
        int numPagesMax = Main.get().getConfigInt("sizeof_maxb");
        if (numSizeInPages <= 0) {
            Main.get().doLogO("invalid number of pages!");
            return;
        } else if (numSizeInPages < numPagesMin) {
            Main.get().doLogO("too few pages!");
            numSizeInPages = numPagesMin;
        } else if (numSizeInPages > numPagesMax) {
            Main.get().doLogO("too many pages!");
            numSizeInPages = numPagesMax;
        }
        this.arrPages = new ArrayList<Menu>(numSizeInPages);
        int numSizeInSlots = numSizeOfPages * 9;
        for (int itrNumPage = 1; itrNumPage <= numSizeInPages; itrNumPage++) {
            String itrStrPage = String.format("%s|%d/%d|", this.getSign(), itrNumPage, numSizeInPages);
            if (Menu.vetMenu(itrStrPage) == false) { Menu.setMenu(itrStrPage, numSizeOfPages); }
            Menu itrObjPage = Menu.getMenu(itrStrPage);
            String itrStrPagePrev = String.format("%s|%d/%d|", strTitle, itrNumPage > 1 ? itrNumPage - 1 : numSizeInPages, numSizeInPages);
            String itrStrPageNext = String.format("%s|%d/%d|", strTitle, itrNumPage < numSizeInPages ? itrNumPage + 1 : 1, numSizeInPages);
            if (Item.vetItem(itrStrPagePrev) == false) { Item.setItem(itrStrPagePrev, "PAPER", "switch to the previous page", "mguimenu " + itrStrPagePrev); }
            if (Item.vetItem(itrStrPageNext) == false) { Item.setItem(itrStrPageNext, "PAPER", "switch to the following page", "mguimenu " + itrStrPageNext); }
            Item itrObjItemPrev = Item.getItem(itrStrPagePrev);
            Item itrObjItemNext = Item.getItem(itrStrPageNext);
            Item itrObjItemBack = Item.getItem("back");
            itrObjPage.setItem(itrObjItemPrev, itrObjPage.getSizeInSlots() - 2);
            itrObjPage.setItem(itrObjItemBack, itrObjPage.getSizeInSlots() - 1);
            itrObjPage.setItem(itrObjItemNext, itrObjPage.getSizeInSlots() - 0);
            this.arrPages.add(itrObjPage);
        }
    }
    protected Book(String strTitle, int numSizeInPages) { this(strTitle, numSizeInPages, 6); }
    protected Book(String strTitle) { this(strTitle, Main.get().getConfigInt("sizeof_useb"), Main.get().getConfigInt("sizeof_usem")); }
    /* getters */
    public static HashMap<String, Book> getBookTab() { return tab; }
    static public Book getBook(String strSign) {
        strSign = strSign.replace(" ", "_");
        if (vetBook(strSign)) { return tab.get(strSign); }
        else { Main.get().doLogO("the book \"%s\" is not found!", strSign); return null; }
    }
    public int getSizeOfPages()   { return this.arrPages.get(0).getSizeInLines(); }
    public int getSizeInPages()   { return this.arrPages.size(); }
    public int getSizeOfLines()   { return this.arrPages.get(0).getSizeOfLines(); }
    public int getSizeInLines()   { return this.getSizeInPages() * this.getSizeOfPages(); }
    public int getSizeOfSlots()   { return this.arrPages.get(0).getSizeOfSlots(); }
    public int getSizeInSlots()   { return this.getSizeInPages() * this.getSizeOfPages() * this.getSizeOfLines(); }
    public Menu getPage()            { return this.arrPages.get(0); }
    public Menu getPage(int numPage) { return this.arrPages.get(numPage - 1); }
    /* setters */
    static public boolean setBook(String strTitle, int numSizeInPages, int numSizeInLines) {
        if (vetBook(strTitle)) { Main.get().doLogO("the book \"%s\" has already been set!", strTitle); return false; }
        else { tab.put(strTitle, new Book(strTitle.replace(" ", "_"), numSizeInPages, numSizeInLines)); return true; }
    }
    public boolean setItem(Item objItem, int numSlot) {
        return this.getPage().setItem(objItem, numSlot);
    }
    public boolean setItem(Item objItem, int numY, int numX) {
        return this.getPage().setItem(objItem, numY, numX);
    }
    public boolean setItem(Item objItem, int numP, int numY, int numX) {
        return this.getPage(numP).setItem(objItem, numY, numX);
    }
    public boolean setItem(Item objItem, List<Integer> numSlot) {
        switch (numSlot.size()){
            case 1: { return this.setItem(objItem, numSlot.get(0)); }
            case 2: { return this.setItem(objItem, numSlot.get(0), numSlot.get(1)); }
            case 3: { return this.setItem(objItem, numSlot.get(0), numSlot.get(1), numSlot.get(2)); }
            default: { return false; }
        }
    }
    public boolean setItem(Item objItem, int[] numSlot) {
        switch (numSlot.length){
            case 1: { return this.setItem(objItem, numSlot[0]); }
            case 2: { return this.setItem(objItem, numSlot[0], numSlot[1]); }
            case 3: { return this.setItem(objItem, numSlot[0], numSlot[1], numSlot[2]); }
            default: { return false; }
        }
    }
    /* vetters */
    public static boolean vetBook(String strSign) { return tab.containsKey(strSign.replace(" ", "_")); }
    public static boolean vetBook(Book objBook)   { return tab.containsKey(objBook.getSign()); }
    public boolean vetSize(Integer numNumb)       { return this.getSizeInPages() >= numNumb; }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("the init has already been done!");
            return false;
        }
        tab = new HashMap<String, Book>();
        if (false
            || Book.setBook(Main.get().getConfigStr("nameof_main"), Main.get().getConfigInt("sizeof_useb"), Main.get().getConfigInt("sizeof_usem")) == false
        ) { Main.get().doLogO("failed to initialize default books!"); }
        Main.get().doLogO("========<listof_book>========");
        if (Main.get().vetConfig("listof_book")) {
            ConfigurationSection objSectionListOf = Main.get().getConfigSec("listof_book");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                Main.get().doLogO(
                    "count: %d;",
                    setKeys.size()
                );
            } else {
                Main.get().doLogO(
                    "the config section listof_book is empty!"
                );
                return false;
            }
            for (String itrStrKey : setKeys) {
                ConfigurationSection itrObjSectionBook = objSectionListOf.getConfigurationSection(itrStrKey);
                String itrStrSign = null;
                List<Integer> itrNumSize = null;
                if (itrObjSectionBook.contains("info")) {
                    ConfigurationSection itrObjSectionInfo = itrObjSectionBook.getConfigurationSection("info");
                    itrStrSign = itrObjSectionInfo.getString("sign");
                    itrNumSize = itrObjSectionInfo.getIntegerList("size");
                } else {
                    Main.get().doLogO(
                        "the config section \"%s\" does not have config section\"info\"!",
                        itrStrSign
                    );
                    return false;
                }
                if (Book.setBook(itrStrSign, itrNumSize.get(0), itrNumSize.get(1))) {
                    /* Main.get().doLogO("the book \"%s\" has been added;",
                        itrStrSign
                    ); */
                } else {
                    Main.get().doLogO(
                        "failed to add the book \"%s\";",
                        itrStrSign
                    );
                    return false;
                }
                Book itrObjBook = Book.getBook(itrStrSign);
                if (itrObjSectionBook.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionBook.getConfigurationSection("data");
                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
                    for (String itrStrItem : itrSetItems) {
                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
                        String itrStrItemSign = itrObjSectionItem.getString("sign");
                        List<Integer> itrItemSlot = itrObjSectionItem.getIntegerList("slot");
                        if (Item.vetItem(itrStrItemSign)) {
                            if (itrObjBook.setItem(Item.getItem(itrStrItemSign), itrItemSlot)) {
                                /*Main.get().doLogO(
                                    "the book item \"%s\" has been added;",
                                    itrStrItemSign
                                );*/
                            } else {
                                Main.get().doLogO(
                                    "failed to add the book item: \"%s\";",
                                    itrStrItemSign
                                );
                                return false;
                            }
                        } else {
                            Main.get().doLogO(
                                "the book item \"%s\" is not found;",
                                itrStrItemSign
                            );
                            return false;
                        }
                    }
                } else {
                    Main.get().doLogO(
                        "config section \"%s\" does not have config section \"data\"!",
                        itrStrSign
                    );
                    return false;
                }
            }
        } else {
            Main.get().doLogO(
                "the config section listof_book is not found!"
            );
            return false;
        }
        return true;
    }
    static public boolean doQuit() {
        if (tab == null) {
            Main.get().doLogO("the quit has already been done!");
            return false;
        }
        tab.clear();
        tab = null;
        return true;
    }
    public boolean doShow(Player objPlayer) { return getPage().doShow(objPlayer); }
    public boolean doHide(Player objPlayer) { return getPage().doHide(objPlayer); }
    /* handles */
}
/* endfile */