/* package */
package nikochir.kernel;
/* import */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
/** javkit **/
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
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
 * -> set of menu objects;
 * -> every menu is a page;
 * -> automatically sets enumerated names for all pages;
 * -> counts pages [from 1 to the_number_of_pages];
 * -> coordinates are considered from greater to lesser:
 * --> [z, y, x] is used because this is how we use counting systems;
 * --> page is z, row is y, column is x;
 * --> 100 = z, 10 = y, x = 1;
*/
public class Book extends Unit {
    /* members */
    static private HashMap<Sign, Book> tab;
    private List<Menu> tabPages;
    /* codetor */
    protected Book(String strTitle, int numSizeInPages, int numSizeOfPages) {
        super(strTitle, numSizeInPages, numSizeOfPages);
        int numPagesMin = Main.get().getConfigInt("sizeof_minb");
        int numPagesMax = Main.get().getConfigInt("sizeof_maxb");
        if (numSizeInPages == 0) {
            Main.get().doLogO("invalid number of pages!");
            return;
        } else if (numSizeInPages < numPagesMin) {
            Main.get().doLogO("too few pages!");
            numSizeInPages = numPagesMin;
        } else if (numSizeInPages > numPagesMax) {
            Main.get().doLogO("too many pages!");
            numSizeInPages = numPagesMax;
        }
        this.tabPages = new ArrayList<Menu>(numSizeInPages);
        int numSizeInSlots = numSizeOfPages * 9;
        for (int itrNumPage = 1; itrNumPage <= numSizeInPages; itrNumPage++) {
            String itrStrPage = String.format("%s|%d/%d|", strTitle, itrNumPage, numSizeInPages);
            Menu itrObjPage = Menu.getMenu(itrStrPage, numSizeOfPages);
            String itrStrPagePrev = String.format("%s|%d/%d|", strTitle, itrNumPage > 1 ? itrNumPage - 1 : numSizeInPages, numSizeInPages);
            String itrStrPageNext = String.format("%s|%d/%d|", strTitle, itrNumPage < numSizeInPages ? itrNumPage + 1 : 1, numSizeInPages);
            Item itrObjItemPrev = Item.getItem(itrStrPagePrev, "PAPER", "previous page", "mguimenu " + itrStrPagePrev);
            Item itrObjItemNext = Item.getItem(itrStrPageNext, "PAPER", "following page", "mguimenu " + itrStrPageNext);
            Item itrObjItemBack = Item.getItem("back", "COMPASS", "swich to the last seen menu...", "back");
            itrObjPage.setItem(itrObjItemPrev, numSizeInSlots - 3);
            itrObjPage.setItem(itrObjItemBack, numSizeInSlots - 2);
            itrObjPage.setItem(itrObjItemNext, numSizeInSlots - 1);
            this.tabPages.add(itrObjPage);
        }
        tab.put(this.getSign(), this);
    }
    protected Book(String strTitle, int numSizeInPages) { this(strTitle, numSizeInPages, Main.get().getConfigInt("sizeof_usem")); }
    protected Book(String strTitle) { this(strTitle, Main.get().getConfigInt("sizeof_useb"), Main.get().getConfigInt("sizeof_usem")); }
    /* getters */
    static public Book getBook(String strTitle, int numSizeInPages, int numSizeOfPages) {
        Sign objSign = Sign.getSign(strTitle, numSizeInPages, numSizeOfPages);
        Book objBook = null;
        if (vetBook(objSign)) { objBook = tab.get(objSign); }
        else                  { objBook = new Book(strTitle, numSizeInPages, numSizeOfPages); }
        return objBook;
    }
    static public Book getBook(String strTitle, int numSizeInPages) {
        return getBook(strTitle, numSizeInPages, Main.get().getConfigInt("sizeof_usem"));
    }
    static public Book getBook(String strTitle) {
        return getBook(strTitle, Main.get().getConfigInt("sizeof_useb"), Main.get().getConfigInt("sizeof_usem"));
    }
    public int getSizeOfPages() { return this.tabPages.get(0).getSizeInLines(); }
    public int getSizeInPages() { return this.tabPages.size(); }
    public int getSizeOfLines() { return this.tabPages.get(0).getSizeOfLines(); }
    public int getSizeInLines() { return this.getSizeInPages() * this.getSizeOfPages(); }
    public int getSizeOfSlots() { return this.tabPages.get(0).getSizeOfSlots(); }
    public int getSizeInSlots() { return this.getSizeInPages() * this.getSizeOfPages() * this.getSizeOfLines(); }
    public Menu getPage()            { return this.tabPages.get(0); }
    public Menu getPage(int numPage) { return this.tabPages.get(numPage - 1); }
    /* setters */
    public boolean setItem(Item objItem, int numSlot) {
        return this.getPage().setItem(objItem, numSlot);
    }
    public boolean setItem(Item objItem, int numY, int numX) {
        return this.getPage().setItem(objItem, numY, numX);
    }
    public boolean setItem(Item objItem, int numP, int numY, int numX) {
        return this.getPage(numP).setItem(objItem, numY, numX);
    }
    public boolean setItem(Item objItem, int[] arrNums) {
        if (arrNums.length == 0) {
            Main.get().doLogO(
                "no coordinate arguments provided! Book.setItem(objItem, arrNums);"
            );
            return false;
        } else if (arrNums.length == 1) {
            return this.setItem(objItem, arrNums[0]);
        } else if (arrNums.length == 2) {
            return this.setItem(objItem, arrNums[0], arrNums[1]);
        } else if (arrNums.length == 3) {
            return this.setItem(objItem, arrNums[0], arrNums[1], arrNums[2]);
        } else {
            Main.get().doLogO(
                "invalid argument count: %d! Book.setItem(objItem, arrNums);",
                arrNums.length
            );
            return false;
        }
    }
    public boolean setItem(Item objItem, List<Integer> arrNums) {
        if (arrNums.size() == 0) {
            Main.get().doLogO(
                "no coordinate arguments provided! Book.setItem(objItem, arrNums);"
            );
            return false;
        } else if (arrNums.size() == 1) {
            return this.setItem(objItem, arrNums.get(0));
        } else if (arrNums.size() == 2) {
            return this.setItem(objItem, arrNums.get(0), arrNums.get(1));
        } else if (arrNums.size() == 3) {
            return this.setItem(objItem, arrNums.get(0), arrNums.get(1), arrNums.get(2));
        } else {
            Main.get().doLogO(
                "invalid argument count : %d! Book.setItem(objItem, arrNums);",
                arrNums.size()
            );
            return false;
        }
    }
    /* vetters */
    static public boolean vetBook(Book objBook)      { return tab.containsKey(objBook.getSign()); }
    static public boolean vetBook(Sign objSign)      { return tab.containsKey(objSign); }
    static public boolean vetBook(Object ... arrObj) { return tab.containsKey(Sign.getSign(arrObj)); }
    public boolean vetSize(int numNumb) { return this.getSizeInPages() >= numNumb; }
    public boolean vetPage(int numPage) { return this.getSizeInPages() >= numPage; }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO(
                "init has already beendone!"
            );
            return false;
        }
        tab = new HashMap<Sign, Book>();
//        if (Main.get().vetConfig("listof_book")) {
//            ConfigurationSection objSectionListOf = Main.get().getConfigSec("listof_book");
//            Set<String> setKeys = objSectionListOf.getKeys(false);
//            /*if (setKeys.size() > 0) {
//                Main.get().doLogO(
//                    "count: %d;", setKeys.size()
//                );
//            } else {
//                Main.get().doLogO(
//                    "the config section listof_book is empty!"
//                );
//                return false;
//            }*/
//            for (String itrStrKey : setKeys) {
//                ConfigurationSection itrObjSectionBook = objSectionListOf.getConfigurationSection(itrStrKey);
//                String itrStrSign = null;
//                List<Integer> itrNumSize = null;
//                if (itrObjSectionBook.contains("info")) {
//                    ConfigurationSection itrObjSectionInfo = itrObjSectionBook.getConfigurationSection("info");
//                    itrStrSign = itrObjSectionInfo.getString("sign");
//                    itrNumSize = itrObjSectionInfo.getIntegerList("size");
//                } else {
//                    Main.get().doLogO(
//                        "the config section \"%s\" does not have config section\"info\"!",
//                        itrStrSign
//                    );
//                    return false;
//                }
//                Book itrObjBook = Book.getBook(itrStrSign, itrNumSize.get(0), itrNumSize.get(1));
//                if (itrObjBook != null) {
//                    /* Main.get().doLogO(
//                        "the book has been added: %s;",
//                        itrStrKey
//                    ); */
//                } else {
//                    Main.get().doLogO(
//                        "failed to add the book: %s;",
//                        itrStrSign
//                    );
//                    return false;
//                }
//                if (itrObjSectionBook.contains("data")) {
//                    ConfigurationSection itrObjSectionData = itrObjSectionBook.getConfigurationSection("data");
//                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
//                    for (String itrStrItem : itrSetItems) {
//                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
//                        String itrStrItemSign = itrObjSectionItem.getString("sign");
//                        List<Integer> itrArrItemSlot = itrObjSectionItem.getIntegerList("slot");
//                        if (Item.vetItem(itrStrItemSign)) {
//                            if (itrObjBook.setItem(
//                                Item.getItem(itrStrItemSign),
//                                itrArrItemSlot.get(0), itrArrItemSlot.get(1), itrArrItemSlot.get(2)
//                            ) == true) {
//                                /* Main.get().doLogO("the book item has been set: %s;", itrStrItemSign); */
//                            } else {
//                                Main.get().doLogO(
//                                    "failed to set the book item: %s;",
//                                    itrStrItemSign
//                                );
//                                return false;
//                            }
//                        } else {
//                            Main.get().doLogO(
//                                "the book item is not found: %s;",
//                                itrStrItemSign
//                            );
//                            return false;
//                        }
//                    }
//                } else {
//                    Main.get().doLogO(
//                        "config section \"%s\" does not have config section \"data\"!",
//                        itrStrKey
//                    );
//                    return false;
//                }
//            }
//        } else {
//            Main.get().doLogO("the config section listof_book is not found!");
//            return false;
//        }
//        return true;
//    }
//    static public boolean doQuit() {
//        if (tab == null) {
//            Main.get().doLogO("init is not done!");
//            return false;
//        }
//        tab.clear();
//        tab = null;
        return true;
    }
    static public boolean doQuit() {
        if (tab == null) {
            Main.get().doLogO("init is not done!");
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