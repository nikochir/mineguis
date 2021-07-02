/* package */
package nikochir;
/* include */
import nikochir.*;
import nikochir.unit.*;
import nikochir.execut.*;
import nikochir.listen.*;
import nikochir.permit.*;
/** javkit - standard utilities **/
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
/** bukkit - plugin, config, events **/
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.Material;
/* MineGuis class
 * > description:
 * -> initializer-terminator plugin singleton;
 * -> handles all menus;
 * -> manages permissions, menus and items;
*/
public class MineGuis extends JavaPlugin {
    /* members */
    private static MineGuis objInstance;
    private HashMap<String, MineGuisUser> tabUsers;
    private HashMap<String, MineGuisItem> tabItems;
    private HashMap<String, MineGuisMenu> tabMenus;
    private HashMap<String, MineGuisBook> tabBooks;
    /* getters */
    public static MineGuis get() { return objInstance; }
    public MineGuisUser getUser(String strSign)        { return this.tabUsers.get(strSign); }
    public MineGuisUser getUser(Player objPlayer)      { return this.getUser(objPlayer.getUniqueId().toString()); }
    public MineGuisUser getUser(HumanEntity objEntity) { return this.getUser(objEntity.getUniqueId().toString()); }
    public MineGuisItem getItem(String strSign)        { return this.tabItems.get(strSign); }
    public MineGuisItem getItem(ItemStack objItem)     { return this.tabItems.get(objItem.getItemMeta().getDisplayName()); }
    public MineGuisItem getItem(ItemMeta objMeta)      { return this.tabItems.get(objMeta.getDisplayName()); }
    public MineGuisItem getItemMain()                  { return this.getItem(this.getConfigStr("nameof_main")); }
    public MineGuisMenu getMenu(String strSign)        { return this.tabMenus.get(strSign); }
    public MineGuisMenu getMenu(InventoryView objMenu) { return this.tabMenus.get(objMenu.getTitle()); }
    public MineGuisMenu getMenuMain()             { return this.getMenu(this.getConfigStr("nameof_main")); }
    public MineGuisBook getBook(String strSign)   { return this.tabBooks.get(strSign); }
    public MineGuisBook getBookMain()             { return this.getBook(this.getConfigStr("nameof_main")); }
    public PluginCommand getCommand(String strCommand) { return this.getServer().getPluginCommand(strCommand); }
    public Player getPlayer(String strPlayer)          { return this.getServer().getPlayer(strPlayer); }
    public Boolean getConfigBit(String strKey)      { return this.getConfig().getBoolean(strKey); }
    public Integer getConfigInt(String strKey)      { return this.getConfig().getInt(strKey); }
    public Double getConfigNum(String strKey)       { return this.getConfig().getDouble(strKey); }
    public String getConfigStr(String strKey)       { return this.getConfig().getString(strKey); }
    public List<Boolean> getConfigBitList(String strKey)    { return this.getConfig().getBooleanList(strKey); }
    public List<Integer> getConfigIntList(String strKey)    { return this.getConfig().getIntegerList(strKey); }
    public List<Double> getConfigNumList(String strKey)     { return this.getConfig().getDoubleList(strKey); }
    public List<String> getConfigStrList(String strKey)     { return this.getConfig().getStringList(strKey); }
    public ConfigurationSection getConfigSec(String strKey) { return this.getConfig().getConfigurationSection(strKey); }
    /* setters */
    public Boolean addUser(MineGuisUser objUser) {
        if (objUser == null) { this.doLogO("the user is null!"); return false; }
        if (this.vetUser(objUser) == true) { this.doLogO("the user has already been added!"); return false; }
        this.tabUsers.put(objUser.getSign(), objUser);
        return true;
    }
    public Boolean rmvUser(MineGuisUser objUser) {
        if (objUser == null) { this.doLogO("the user is null!"); return false; }
        if (this.vetUser(objUser) == false) { this.doLogO("the user has already been removed!"); return false; }
        this.tabUsers.remove(objUser.getSign(), objUser);
        return true;
    }
    public Boolean rmvUser(String strSign) { return this.rmvUser(this.getUser(strSign)); }
    public Boolean addItem(MineGuisItem objItem) {
        if (objItem == null) { this.doLogO("the item is null!"); return false; }
        if (this.vetItem(objItem) == true) { this.doLogO("the item has already been added!"); return false; }
        this.tabItems.put(objItem.getSign(), objItem);
        return true;
    }
    public Boolean rmvItem(MineGuisItem objItem) {
        if (objItem == null) { this.doLogO("the item is null!"); return false; }
        if (this.vetItem(objItem) == false) { this.doLogO("the item has already been removed!"); return false; }
        this.tabItems.remove(objItem.getSign(), objItem);
        return true;
    }
    public Boolean rmvItem(String strSign) { return this.rmvItem(this.getItem(strSign)); }
    public Boolean addMenu(MineGuisMenu objMenu) {
        if (objMenu == null) { this.doLogO("the menu is null!"); return false; }
        if (this.vetMenu(objMenu) == true) { this.doLogO("the menu has already been added!"); return false; }
        this.tabMenus.put(objMenu.getSign(), objMenu);
        return true;
    }
    public Boolean rmvMenu(MineGuisMenu objMenu) {
        if (objMenu == null) { this.doLogO("the menu is null!"); return false; }
        if (this.vetMenu(objMenu) == false) { this.doLogO("the menu has already been removed!"); return false; }
        this.tabMenus.remove(objMenu.getSign(), objMenu);
        return true;
    }
    public Boolean rmvMenu(String strSign) { return this.rmvMenu(this.getMenu(strSign)); }
    public Boolean addBook(MineGuisBook objBook) {
        if (objBook == null) { this.doLogO("the book is null!"); return false; }
        if (this.vetBook(objBook) == true) { this.doLogO("the book has already been added!"); return false; }
        this.tabBooks.put(objBook.getSign(), objBook);
        return true;
    }
    public Boolean rmvBook(MineGuisBook objBook) {
        if (objBook == null) { this.doLogO("the book is null!"); return false; }
        if (this.vetBook(objBook) == false) { this.doLogO("the book has already been removed!"); return false; }
        this.tabBooks.remove(objBook.getSign(), objBook);
        return true;
    }
    public Boolean rmvBook(String strSign) { return this.rmvBook(this.getBook(strSign)); }
    //public Boolean setConfigBit(String strKey, Boolean bitVal)      { this.getConfig().setBoolean(strKey, bitVal); return true; }
    //public Boolean setConfigInt(String strKey, Integer intVal)      { this.getConfig().setInteger(strKey, intVal); return true; }
    //public Boolean setConfigNum(String strKey, Double numVal)       { this.getConfig().setDouble(strKey, numVal); return true; }
    //public Boolean setConfigStr(String strKey, String strVal)       { this.getConfig().setString(strKey, strVal); return true; }
    //public Boolean setConfigArr(String strKey, List<String> arrVal) { this.getConfig().setStringList(strKey, arrVal); return true; }
    /* vetters */
    public Boolean vetUser(MineGuisUser objUser)  { return this.tabUsers.containsKey(objUser.getSign()); }
    public Boolean vetUser(String strSign)        { return this.tabUsers.containsKey(strSign); }
    public Boolean vetUser(Player objUser)        { return this.tabUsers.containsKey(objUser.getUniqueId().toString()); }
    public Boolean vetUser(HumanEntity objUser)   { return this.tabUsers.containsKey(objUser.getUniqueId().toString()); }
    public Boolean vetItem(MineGuisItem objItem)  { return this.tabItems.containsKey(objItem.getSign()); }
    public Boolean vetItem(String strSign)        { return this.tabItems.containsKey(strSign); }
    public Boolean vetItem(ItemStack objItem)     { return this.tabItems.containsKey(objItem.getItemMeta().getDisplayName()); }
    public Boolean vetItem(ItemMeta objMeta)      { return this.tabItems.containsKey(objMeta.getDisplayName()); }
    public Boolean vetMenu(MineGuisMenu objMenu)  { return this.tabMenus.containsKey(objMenu.getSign()); }
    public Boolean vetMenu(String strSign)        { return this.tabMenus.containsKey(strSign); }
    public Boolean vetMenu(InventoryView objMenu) { return this.tabMenus.containsKey(objMenu.getTitle()); }
    public Boolean vetBook(MineGuisBook objBook)  { return this.tabBooks.containsKey(objBook.getSign()); }
    public Boolean vetBook(String strSign)        { return this.tabBooks.containsKey(strSign); }
    public Boolean vetConfig(String strPath)      { return this.getConfig().contains(strPath); }
    /* actions */
    public void doLogO(String strFormat, Object ... objArgs) {
        System.out.printf(String.format("%s:%s\n", this.getConfigStr("nameof_logo"), strFormat), objArgs);
    }
    public Boolean doInitExecuts() {
        this.getCommand("mineguis").setExecutor(new MineGuisExecut());
        this.getCommand("mguimain").setExecutor(new MineGuisExecutMain());
        this.getCommand("mguimenu").setExecutor(new MineGuisExecutMenu());
        this.getCommand("mguibook").setExecutor(new MineGuisExecutBook());
        this.getCommand("mguiback").setExecutor(new MineGuisExecutBack());
        this.getCommand("mguivoid").setExecutor(new MineGuisExecutVoid());
        return true;
    }
    public Boolean doInitListens() {
        this.getServer().getPluginManager().registerEvents(new MineGuisListen(), this);
        return true;
    }
    public Boolean doInitPermits() {
        this.getServer().getPluginManager().addPermission(new MineGuisPermit());
        return true;
    }
    public Boolean doInitConfigs() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        /*** yaml ***/
        this.doLogO("========<config_yaml>========");
        {
            Set<String> setKeys = this.getConfig().getKeys(true);
            this.doLogO( String.format("count: %d;", setKeys.size()) );
            for (String itrStrKey : setKeys) { this.doLogO(itrStrKey); }
        }
        /*** item ***/
        this.doLogO("========<listof_item>========");
        if (this.vetConfig("listof_item")) {
            ConfigurationSection objSectionListOf = this.getConfigSec("listof_item");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                this.doLogO( String.format("count: %d;", setKeys.size()) );
            } else {
                this.doLogO( String.format("the listof config section is empty!") );
                return false;
            }
            for (String itrStrKey : setKeys) {
                ConfigurationSection itrObjSectionItem = objSectionListOf.getConfigurationSection(itrStrKey);
                String itrStrSign = null;
                String itrStrLore = null;
                String itrStrIcon = null;
                String itrStrExec = null;
                if (itrObjSectionItem.contains("info")) {
                    ConfigurationSection itrObjSectionInfo = itrObjSectionItem.getConfigurationSection("info");
                    itrStrSign = itrObjSectionInfo.getString("sign");
                    itrStrIcon = itrObjSectionInfo.getString("icon");
                    itrStrLore = itrObjSectionInfo.getString("lore");
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"info\"!", itrStrSign);
                    return false;
                }
                if (itrObjSectionItem.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionItem.getConfigurationSection("data");
                    itrStrExec = itrObjSectionData.getString("exec");
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"data\"!", itrStrSign);
                    return false;
                }
                MineGuisItem itrObjItem = new MineGuisItem(itrStrSign, itrStrLore, itrStrIcon, itrStrExec);
                if (this.addItem(itrObjItem)) {
                    this.doLogO("the item has been added: " + itrStrKey);
                } else {
                    this.doLogO("failed to add the item: " + itrStrKey);
                    return false;
                }
            }
        } else {
            this.doLogO("the config section listof_item is not found!");
            return false;
        }
        /*** menu ***/
        this.doLogO("========<listof_menu>========");
        if (vetConfig("listof_menu")) {
            ConfigurationSection objSectionListOf = this.getConfigSec("listof_menu");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                this.doLogO( String.format("count: %d;", setKeys.size()) );
            } else {
                this.doLogO( String.format("the listof config section is empty!") );
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
                    this.doLogO("config section \"%s\" does not have config section \"info\"!", itrStrSign);
                    return false;
                }
                MineGuisMenu itrObjMenu = new MineGuisMenu(itrStrSign, itrNumSize);
                if (this.addMenu(itrObjMenu)) {
                    this.doLogO("the menu has been added: " + itrStrKey);
                } else {
                    this.doLogO("failed to add the menu: " + itrStrKey);
                    return false;
                }
                if (itrObjSectionMenu.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionMenu.getConfigurationSection("data");
                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
                    for (String itrStrItem : itrSetItems) {
                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
                        String itrStrItemSign = itrObjSectionItem.getString("sign");
                        Integer itrItemNumY = itrObjSectionItem.getInt("posy");
                        Integer itrItemNumX = itrObjSectionItem.getInt("posx");
                        if (this.vetItem(itrStrItemSign)) {
                            if (itrObjMenu.setItem(this.getItem(itrStrItemSign), itrItemNumY, itrItemNumX)) {
                                this.doLogO("the menu item has been added: " + itrStrItemSign);
                            } else {
                                this.doLogO("failed to add the menu item: " + itrStrItemSign);
                                return false;
                            }
                        } else {
                            this.doLogO("the menu item is not found: " + itrStrItemSign);
                            return false;
                        }
                    }
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"data\"!", itrStrSign);
                    return false;
                }
            }
        } else {
            this.doLogO("the config section listof_menu is not found!");
            return false;
        }
        /*** book ***/
        this.doLogO("========<listof_book>========");
        if (this.vetConfig("listof_book")) {
            ConfigurationSection objSectionListOf = this.getConfigSec("listof_book");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                this.doLogO( String.format("count: %d;", setKeys.size()) );
            } else {
                this.doLogO( "the config section listof_book is empty!" );
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
                    this.doLogO("the config section \"%s\" does not have config section\"info\"!", itrStrSign);
                    return false;
                }
                MineGuisBook itrObjBook = new MineGuisBook(itrStrSign, itrNumSize.get(0), itrNumSize.get(1));
                if (this.addBook(itrObjBook)) {
                    this.doLogO("the book has been added: " + itrStrSign);
                } else {
                    this.doLogO("failed to add the book: " + itrStrSign);
                    return false;
                }
                if (itrObjSectionBook.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionBook.getConfigurationSection("data");
                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
                    for (String itrStrItem : itrSetItems) {
                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
                        String itrStrItemSign = itrObjSectionItem.getString("sign");
                        Integer itrItemNumP = itrObjSectionItem.getInt("posp");
                        Integer itrItemNumY = itrObjSectionItem.getInt("posy");
                        Integer itrItemNumX = itrObjSectionItem.getInt("posx");
                        if (this.vetItem(itrStrItemSign)) {
                            if (itrObjBook.setItem(this.getItem(itrStrItemSign), itrItemNumP, itrItemNumY, itrItemNumX)) {
                                this.doLogO("the book item has been added: " + itrStrItemSign);
                            } else {
                                this.doLogO("failed to add the book item: " + itrStrItemSign);
                                return false;
                            }
                        } else {
                            this.doLogO("the book item is not found: " + itrStrItemSign);
                            return false;
                        }
                    }
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"data\"!", itrStrSign);
                    return false;
                }
            }
        } else {
            this.doLogO("the config section listof_book is not found!");
            return false;
        }
        return true;
    }
    /* handles */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        this.tabUsers = new HashMap<String, MineGuisUser>();
        this.tabItems = new HashMap<String, MineGuisItem>();
        this.tabMenus = new HashMap<String, MineGuisMenu>();
        this.tabBooks = new HashMap<String, MineGuisBook>();
        /* work */
        this.addItem(new MineGuisItem("mineguis", "basic gui command", "KNOWLEDGE_BOOK", "mineguis"));
        this.addItem(new MineGuisItem("mineguis menu", "basic gui menu command", "PAPER", "mineguis menu"));
        this.addItem(new MineGuisItem("mineguis book", "basic gui menu command", "BOOK", "mineguis book"));
        this.addItem(new MineGuisItem("void", "____", "BLACK_STAINED_GLASS_PANE", "mguivoid"));
        this.addItem(new MineGuisItem("main", "open the main menu", "COMPASS", "mguimain"));
        this.addItem(new MineGuisItem("back", "open the last opened menu", "COMPASS", "mguiback"));
        this.addMenu(new MineGuisMenu(getConfigStr("nameof_main"), getConfigInt("sizeof_main")));
        MineGuisMenu objMenuMain = this.getMenu(getConfigStr("nameof_main"));
        objMenuMain.setItem(this.getItem("main"), 1);
        objMenuMain.setItem(this.getItem("back"), 2);
        this.addBook(new MineGuisBook(getConfigStr("nameof_main"), getConfigInt("sizeof_useb")));
        /** execut **/
        if (this.doInitExecuts()) {
            this.doLogO("init executs is done;");
        } else {
            this.doLogO("init executs is failed;");
        }
        /** listen **/
        if (this.doInitListens()) {
            this.doLogO("init listens is done;");
        } else {
            this.doLogO("init listens is failed;");
        }
        /** permit **/
        if (this.doInitPermits()) {
            this.doLogO("init permits is done;");
        } else {
            this.doLogO("init permits is failed;");
        }
        /** config **/
        if (this.doInitConfigs()) {
            this.doLogO("init configs is done;");
        } else {
            this.doLogO("init configs is failed;");
        }
        /* quit */
    }
    @Override
    public void onDisable() {
        /* init */
        /* work */
        /* quit */
        this.tabUsers.clear(); this.tabUsers = null;
        this.tabItems.clear(); this.tabItems = null;
        this.tabMenus.clear(); this.tabMenus = null;
        this.tabBooks.clear(); this.tabBooks = null;
        objInstance = null;
    }
}
/* endfile */