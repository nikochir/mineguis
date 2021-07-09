/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Item;
/* javkit */
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
/* bukkit */
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
/* typedef */
/* Menu class
 * > Description:
 * -> counts cells [from 1 to 9];
 * -> coordinates are considered from greater to lesser:
 * --> [z, y, x] is used because this is how we use counting systems;
 * --> page is z, row is y, column is x;
 * --> 100 = z, 10 = y, x = 1;
*/
public class Menu extends Unit {
    /* members */
    static private HashMap<Sign, Menu> tab;
    private Inventory objPack;
    private List<Item> tabItems;
    /* codetor */
    protected Menu(String strTitle, int numSizeInLines) {
        super(strTitle, numSizeInLines);
        int numLinesMin = Main.get().getConfigInt("sizeof_minm");
        int numLinesMax = Main.get().getConfigInt("sizeof_maxm");
        if (numSizeInLines <= 0) {
            Main.get().doLogO("invalid number of lines!");
            return;
        } else if (numSizeInLines < numLinesMin) {
            Main.get().doLogO("too few lines!");
            numSizeInLines = numLinesMin;
        } else if (numSizeInLines > numLinesMax) {
            Main.get().doLogO("too many lines!");
            numSizeInLines = numLinesMax;
        }
        this.objPack = Bukkit.createInventory(null, numSizeInLines * 9, strTitle);
        this.tabItems = new ArrayList<Item>(numSizeInLines * 9);
        Item objItem = Item.getItem("void", "BLACK_STAINED_GLASS_PANE", "____", "mguivoid");
        for (int itr = 0; itr < this.getSizeInSlots(); itr++) {
            this.tabItems.add(objItem);
            this.objPack.setItem(itr, objItem.getStack());
        }
        tab.put(this.getSign(), this);
    }
    protected Menu(String strTitle) {
        this(strTitle, Main.get().getConfigInt("sizeof_usem"));
    }
    /* getters */
    static public Menu getMenu(String strTitle, int numSizeInLines) {
        Sign objSign = Sign.getSign(strTitle, numSizeInLines);
        Menu objMenu = null;
        if (vetMenu(objSign)) { objMenu = tab.get(objSign); }
        else                  { objMenu = new Menu(strTitle, numSizeInLines); }
        return objMenu;
    }
    static public Menu getMenu(String strTitle) {
        return getMenu(strTitle, Main.get().getConfigInt("sizeof_usem"));
    }
    static public Menu getMenu(InventoryView objView) {
        return getMenu(objView.getTitle(), objView.getTopInventory().getSize() / 9);
    }
    public int getSizeOfSlots() { return 1; }
    public int getSizeOfLines() { return 9; }
    public int getSizeInSlots() { return this.objPack.getSize(); }
    public int getSizeInLines() { return this.getSizeInSlots(); }
    public Item getItem(int numSlot)        { return this.tabItems.get((numSlot - 1) % this.getSizeOfLines()); }
    public Item getItem(int numY, int numX) { return this.getItem((numY - 1) * this.getSizeInLines() + numX - 1); }
    public Item getItem(ItemStack objStack)  {
        for (int itr = 0; itr < getSizeInSlots(); itr++) {
            if (this.tabItems.get(itr).vetStack(objStack)) { return this.tabItems.get(itr); }
            else { continue; }
        }
        return null;
    }
    /* setters */
    public boolean setItem(Item objItem, int numSlot) {
        if (objItem == null) {
            Main.get().doLogO("null argument! setItem(null, numSlot);");
            return false;
        }
        this.tabItems.set((numSlot - 1) % getSizeInSlots(), objItem);
        this.objPack.setItem((numSlot - 1) % getSizeInSlots(), objItem.getStack());
        return true;
    }
    public boolean setItem(Item objItem, int numY, int numX) {
        return this.setItem(objItem, (numY - 1) * getSizeOfLines() + numX);
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
        } else {
            Main.get().doLogO(
                "invalid argument count: %d! Book.setItem(objItem, arrNums);",
                arrNums.size()
            );
            return false;
        }
    }
    /* vetters */
    static public boolean vetMenu(Menu objMenu)          { return tab.containsKey(objMenu.getSign()); }
    static public boolean vetMenu(Sign objSign)          { return tab.containsKey(objSign); }
    static public boolean vetMenu(InventoryView objView) { return tab.containsKey(Sign.getSign(objView.getTitle(), objView.getTopInventory().getSize() / 9)); }
    static public boolean vetMenu(Object ... arrObj) { return tab.containsKey(Sign.getSign(arrObj)); }
    public boolean vetPack(Inventory objPack)     { return this.objPack.equals(objPack); }
    public boolean vetView(InventoryView objView) { return this.vetSign(Sign.getSign(objView.getTitle(), objView.getTopInventory().getSize() / 9)); }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("init is already done!");
            return false;
        }
        tab = new HashMap<Sign, Menu>();
//        if (Main.get().vetConfig("listof_menu")) {
//            ConfigurationSection objSectionListOf = Main.get().getConfigSec("listof_menu");
//            Set<String> setKeys = objSectionListOf.getKeys(false);
//            if (setKeys.size() > 0) {
//                /* Main.get().doLogO(
//                    "count: %d;",
//                    setKeys.size()
//                ); */
//            } else {
//                Main.get().doLogO(
//                    "the listof config section is empty!"
//                );
//                return false;
//            }
//            for (String itrStrKey : setKeys) {
//                ConfigurationSection itrObjSectionMenu = objSectionListOf.getConfigurationSection(itrStrKey);
//                String itrStrSign = null;
//                Integer itrNumSize = null;
//                if (itrObjSectionMenu.contains("info")) {
//                    ConfigurationSection itrObjSectionInfo = itrObjSectionMenu.getConfigurationSection("info");
//                    itrStrSign = itrObjSectionInfo.getString("sign");
//                    itrNumSize = itrObjSectionInfo.getInt("size");
//                } else {
//                    Main.get().doLogO(
//                        "config section \"%s\" does not have config section \"info\"!",
//                        itrStrKey
//                    );
//                    return false;
//                }
//                Menu itrObjMenu = Menu.getMenu(itrStrSign, itrNumSize);
//                if (itrObjMenu != null) {
//                    /* Main.get().doLogO(
//                        "the menu has been added: %s;",
//                        itrStrKey
//                    ); */
//                } else {
//                    Main.get().doLogO(
//                        "failed to add the menu: %s;",
//                        itrStrKey
//                    );
//                    return false;
//                }
//                if (itrObjSectionMenu.contains("data")) {
//                    ConfigurationSection itrObjSectionData = itrObjSectionMenu.getConfigurationSection("data");
//                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
//                    for (String itrStrItem : itrSetItems) {
//                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
//                        String itrStrItemSign = itrObjSectionItem.getString("sign");
//                        List<Integer> itrArrItemSlot = itrObjSectionItem.getIntegerList("slot");
//                        if (Item.vetItem(itrStrItemSign)) {
//                            if (itrObjMenu.setItem(
//                                Item.getItem(itrStrItemSign),
//                                itrArrItemSlot.get(0), itrArrItemSlot.get(1)
//                            ) == true) {
//                                /* this.doLogO("the menu item has been set; sign: %s; slot: [%d,%d]",
//                                itrStrItemSign, itrArrItemSlot.get(0), itrArrItemSlot.get(1)); */
//                            } else {
//                                Main.get().doLogO(
//                                    "failed to set the menu item: %s;",
//                                    itrStrItemSign
//                                );
//                                return false;
//                            }
//                        } else {
//                            Main.get().doLogO(
//                                "the menu item is not found: %s;",
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
//            Main.get().doLogO("the config section listof_menu is not found!");
//            return false;
//        }
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
    public boolean doShow(Player objPlayer) {
        if (objPlayer == null) {
            Main.get().doLogO("null argument! doShow(null);");
            return false;
        }
        /*if ((objPlayer.getInventory() instanceof PlayerInventory) == true) {
            Main.get().doLogO("the inventory is already shown!");
            return false;
        }*/
        objPlayer.openInventory(this.objPack);
        return true;
    }
    public boolean doHide(Player objPlayer) {
        if (objPlayer == null) {
            Main.get().doLogO("null argument! doHide(null);");
            return false;
        }
        /*if ((objPlayer.getInventory() instanceof PlayerInventory) == false) {
            Main.get().doLogO("the inventory is already hidden!");
            return false;
        }*/
        objPlayer.closeInventory();
        return true;
    }
    public boolean doPass(Player objPlayer, ItemStack objStack) { /* pass the item across the entire pack */
        if (objPlayer == null || objStack == null) {
            Main.get().doLogO("null argument is passed! doPass(objPlayer, objStack);");
            return false;
        }
        Item objItem = this.getItem(objStack);
        if (objItem == null) {
            Main.get().doLogO("the item is not found in this inventory! doPass(objPlayer, objStack);");
            return false;
        } 
        if (objItem.doExec(objPlayer) == false) {
            Main.get().doLogO("failed item execute! doPass(objPlayer, objStack);");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */