/* package */

package main.mineguis.kernel;

/* include */

import main.mineguis.Main;

/** javkit **/

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

/** bukkit **/

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.ConfigurationSection;

/** nkyori **/

import net.kyori.adventure.text.Component;

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
    static private HashMap<String, Menu> tab;
    private Inventory objPack;
    private List<Item> tabItems;
    /* codetor */

    protected Menu(String strTitle, int numSizeInLines) {

        super(strTitle);

        Integer numLinesMin = Main.get().getConfigInt("sizeof_minm");
        Integer numLinesMax = Main.get().getConfigInt("sizeof_maxm");

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

        Component objTitle = Component.text(this.getSign());
        this.objPack = Bukkit.createInventory(null, numSizeInLines * 9, objTitle);
        this.tabItems = new ArrayList<Item>(numSizeInLines * 9);
        
        for (int itr = 0; itr < this.getSizeInSlots(); itr++) {
            this.tabItems.add(null);
            this.objPack.setItem(itr, null);
        }

    }

    /* getters */

    public static HashMap<String, Menu> getMenuTab() { return tab; }
    
    static public Menu getMenu(String strSign) {
        strSign = strSign.replace(" ", "_");
        if (vetMenu(strSign)) { return tab.get(strSign); }
        else { Main.get().doLogO("the menu \"%s\" is not found!", strSign); return null; }
    }

    public int getSizeOfSlots() { return 1; }
    public int getSizeOfLines() { return 9; }
    public int getSizeInSlots() { return this.objPack.getSize(); }
    public int getSizeInLines() { return this.getSizeInSlots() / this.getSizeOfLines(); }
    
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

    static public boolean setMenu(String strTitle, int numSizeInLines) {
        strTitle = strTitle.replace(" ", "_");
        if (Menu.vetMenu(strTitle)) {
            Main.get().doLogO("the menu \"%s\" has already been set!", strTitle);
            return false;
        } else {
            Menu objMenu = new Menu(strTitle, numSizeInLines);
            tab.put(objMenu.getSign(), objMenu);
            return true;
        }
    }

    public boolean setItem(Item objItem, int numSlot) {
        if (objItem == null) {
            Main.get().doLogO("null argument! setItem(null, numSlot);");
            return false;
        }
        if (vetItem(numSlot)) {
            Main.get().doLogO("the item slot is already used!");
            return false;
        }
        this.tabItems.set((numSlot - 1) % getSizeInSlots(), objItem);
        this.objPack.setItem((numSlot - 1) % getSizeInSlots(), objItem.getStack());
        return true;
    }
    public boolean setItem(Item objItem, int numY, int numX) {
        return this.setItem(objItem, (numY - 1) * getSizeOfLines() + numX);
    }
    public boolean setItem(Item objItem, List<Integer> numSlot) {
        switch (numSlot.size()) {
            case 1: { return this.setItem(objItem, numSlot.get(0)); }
            case 2: { return this.setItem(objItem, numSlot.get(0), numSlot.get(1)); }
            default: { return false; }
        }
    }
    public boolean setItem(Item objItem, int[] numSlot) {
        switch (numSlot.length) {
            case 1: { return this.setItem(objItem, numSlot[0]); }
            case 2: { return this.setItem(objItem, numSlot[0], numSlot[1]); }
            default: { return false; }
        }
    }

    /* vetters */

    static public boolean vetMenu(String strTitle) { return tab.containsKey(strTitle.replace(" ", "_")); }
    static public boolean vetMenu(Menu objMenu)    { return tab.containsKey(objMenu.getSign()); }
    public boolean vetPack(Inventory objPack)      { return this.objPack == objPack; }
    public boolean vetView(InventoryView objView)  { return vetSign(objView.getTitle()); }
    public boolean vetItem(int numSlot)            { return this.objPack.getItem((numSlot - 1) % getSizeInSlots()) != null; }
    
    /* actions */

    static private boolean doCreate() {
        if (false
            || Menu.setMenu(
                Main.get().getConfigStr("nameof_main"),
                Main.get().getConfigInt("sizeof_usem")
            ) == false
            || Menu.setMenu("Menue_", 6) == false
            || Menu.setMenu("Levelmap1", 3) == false
            || Menu.setMenu("Levelmap2", 3) == false
        ) { Main.get().doLogO("failed to create some default menu!"); return false; }
        return true;
    }

    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("the init has already been done!");
            return false;
        }
        tab = new HashMap<String, Menu>();
        if (Menu.doCreate() == false) { Main.get().doLogO("failed to create default menus!"); return false; }
        Main.get().doLogO("========<listof_menu>========");
        if (Main.get().vetConfig("listof_menu")) {
            ConfigurationSection objSectionListOf = Main.get().getConfigSec("listof_menu");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                Main.get().doLogO(
                    "count: %d;",
                    setKeys.size()
                );
            } else {
                Main.get().doLogO(
                    "the listof config section is empty!"
                );
                return false;
            }
            for (String itrStrKey : setKeys) {
                ConfigurationSection itrObjSectionMenu = objSectionListOf.getConfigurationSection(itrStrKey);
                String itrStrSign = null;
                Integer itrNumSize = null;
                if (itrObjSectionMenu.contains("info")) {
                    ConfigurationSection itrObjSectionInfo = itrObjSectionMenu.getConfigurationSection("info");
                    itrStrSign = itrObjSectionInfo.getString("sign");
                    itrNumSize = itrObjSectionInfo.getInt("size");
                } else {
                    Main.get().doLogO(
                        "config section \"%s\" does not have config section \"info\"!",
                        itrStrSign
                    );
                    continue;
                    //return false;
                }
                if (Menu.setMenu(itrStrSign, itrNumSize)) {
                    /*Main.get().doLogO(
                        "the menu \"%s\" has been added: ",
                        itrStrKey
                    );*/
                } else {
                    Main.get().doLogO(
                        "failed to add the menu \"%s\";",
                        itrStrKey
                    );
                    continue;
                    //return false;
                }
                Menu itrObjMenu = Menu.getMenu(itrStrSign);
                if (itrObjSectionMenu.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionMenu.getConfigurationSection("data");
                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
                    for (String itrStrItem : itrSetItems) {
                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
                        String itrStrItemSign = itrObjSectionItem.getString("sign");
                        List<Integer> itrItemNum = itrObjSectionItem.getIntegerList("slot");
                        if (Item.vetItem(itrStrItemSign)) {
                            if (itrObjMenu.setItem(Item.getItem(itrStrItemSign), itrItemNum)) {
                                /*Main.get().doLogO(
                                    "the menu item \"%s\" has been added;",
                                    itrStrItemSign
                                );*/
                            } else {
                                Main.get().doLogO(
                                    "failed to add the menu item \"\";",
                                    itrStrItemSign
                                );
                                continue;
                                // return false;
                            }
                        } else {
                            Main.get().doLogO(
                                "the menu item \"%s\" is not found;",
                                itrStrItemSign
                            );
                            continue;
                            // return false;
                        }
                    }
                } else {
                    Main.get().doLogO(
                        "config section \"%s\" does not have config section \"data\"!",
                        itrStrSign
                    );
                    continue;
                    // return false;
                }
            }
        } else {
            Main.get().doLogO(
                "the config section listof_menu is not found!"
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