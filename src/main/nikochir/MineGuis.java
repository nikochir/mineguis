/* package */
package nikochir;
/* include */
import nikochir.*;
import nikochir.kernel.*;
import nikochir.preset.*;
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
import java.util.Collection;
import java.lang.Class;
/** bukkit - plugin, config, events **/
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
    private HashMap<MineGuisSign, MineGuisUser> tabUsers;
    private HashMap<MineGuisSign, MineGuisItem> tabItems;
    private HashMap<MineGuisSign, MineGuisMenu> tabMenus;
    private HashMap<MineGuisSign, MineGuisBook> tabBooks;
    /* getters */
    public static MineGuis get() { return objInstance; }
    public MineGuisUser getUser(MineGuisSign objSign)  { return this.tabUsers.get(objSign); }
    public MineGuisUser getUser(String strSign)        { return this.getUser(MineGuisSign.get(strSign)); }
    public MineGuisUser getUser(Player objPlayer)      { return this.getUser(objPlayer.getUniqueId().toString()); }
    public MineGuisUser getUser(HumanEntity objEntity) { return this.getUser(objEntity.getUniqueId().toString()); }
    public MineGuisItem getItem(MineGuisSign objSign)  { return this.tabItems.get(objSign); }
    public MineGuisItem getItem(String strSign)        { return this.getItem(MineGuisSign.get(strSign)); }
    public MineGuisItem getItem(ItemStack objStack)    { return this.getItem(objStack.getItemMeta().getDisplayName()); }
    public MineGuisItem getItem(ItemMeta objMeta)      { return this.getItem(objMeta.getDisplayName()); }
    public MineGuisItem getItem(String strName, String[] strLore, String strIcon, String strExec) {
        if (this.vetItem(strName) == false) {
            this.addItem(new MineGuisItem(strName, strLore, strIcon, strExec));
        } else {
            MineGuisItem objItem = this.getItem(strName);
            if (objItem.vetLore(strLore) == false) { if (objItem.setLore(strLore) == false) { return null; } }
            if (objItem.vetIcon(strIcon) == false) { if (objItem.setIcon(strIcon) == false) { return null; } }
            if (objItem.vetExec(strExec) == false) { if (objItem.setExec(strExec) == false) { return null; } }
        }
        return this.getItem(strName);
    }
    public MineGuisItem getItemMain()                  { return this.getItem(this.getConfigStr("nameof_main")); }
    public MineGuisMenu getMenu(MineGuisSign objSign)  { return this.tabMenus.get(objSign); }
    public MineGuisMenu getMenu(String strSign)        { return this.getMenu(MineGuisSign.get(strSign)); }
    public MineGuisMenu getMenu(InventoryView objMenu) { return this.getMenu(objMenu.getTitle()); }
    public MineGuisMenu getMenuMain()                  { return this.getMenu(this.getConfigStr("nameof_main")); }
    public MineGuisBook getBook(MineGuisSign objSign)  { return this.tabBooks.get(objSign); }
    public MineGuisBook getBook(String strSign)        { return this.getBook(MineGuisSign.get(strSign)); }
    public MineGuisBook getBookMain()                  { return this.getBook(this.getConfigStr("nameof_main")); }
    public PluginCommand getCommand(String strCommand) { return this.getServer().getPluginCommand(strCommand); }
    public Player getPlayer(String strPlayer)          { return this.getServer().getPlayer(strPlayer); }
    public boolean getConfigBit(String strKey)      { return this.getConfig().getBoolean(strKey); }
    public Integer getConfigInt(String strKey)      { return this.getConfig().getInt(strKey); }
    public Double getConfigNum(String strKey)       { return this.getConfig().getDouble(strKey); }
    public String getConfigStr(String strKey)       { return this.getConfig().getString(strKey); }
    public List<Boolean> getConfigBitList(String strKey) { return this.getConfig().getBooleanList(strKey); }
    public List<Integer> getConfigIntList(String strKey) { return this.getConfig().getIntegerList(strKey); }
    public List<Double> getConfigNumList(String strKey)  { return this.getConfig().getDoubleList(strKey); }
    public List<String> getConfigStrList(String strKey)  { return this.getConfig().getStringList(strKey); }
    public ConfigurationSection getConfigSec(String strKey) { return this.getConfig().getConfigurationSection(strKey); }
    /* setters */
    public boolean addUser(MineGuisUser objUser) {
        if (objUser == null) { this.doLogO("the user is null!"); return false; }
        if (this.vetUser(objUser) == true) { this.doLogO("the user has already been added!"); return false; }
        this.tabUsers.put(objUser.getSign(), objUser);
        return true;
    }
    public boolean rmvUser(MineGuisUser objUser) {
        if (objUser == null) { this.doLogO("the user is null!"); return false; }
        if (this.vetUser(objUser) == false) { this.doLogO("the user has already been removed!"); return false; }
        this.tabUsers.remove(objUser.getSign(), objUser);
        return true;
    }
    public boolean rmvUser(String strSign) { return this.rmvUser(this.getUser(strSign)); }
    public boolean addItem(MineGuisItem objItem) {
        if (objItem == null) { this.doLogO("the item is null!"); return false; }
        if (this.vetItem(objItem) == true) { this.doLogO("the item has already been added!"); return false; }
        this.tabItems.put(objItem.getSign(), objItem);
        return true;
    }
    public boolean addItem(String strName, String strLore, String strIcon, String strExec) {
        if (vetItem(strName)) {
            MineGuisItem objItem = this.getItem(strName);
            if (objItem.vetLore(strLore) && objItem.vetIcon(strIcon) && objItem.vetExec(strExec)) { return false; }
        }
        return this.addItem(new MineGuisItem(strName, strLore, strIcon, strExec));
    }
    public boolean rmvItem(MineGuisItem objItem) {
        if (objItem == null) { this.doLogO("the item is null!"); return false; }
        if (this.vetItem(objItem) == false) { this.doLogO("the item has already been removed!"); return false; }
        this.tabItems.remove(objItem.getSign(), objItem);
        return true;
    }
    public boolean rmvItem(String strSign) { return this.rmvItem(this.getItem(strSign)); }
    public boolean addMenu(MineGuisMenu objMenu) {
        if (objMenu == null) { this.doLogO("the menu is null!"); return false; }
        if (this.vetMenu(objMenu) == true) { this.doLogO("the menu has already been added!"); return false; }
        this.tabMenus.put(objMenu.getSign(), objMenu);
        return true;
    }
    public boolean rmvMenu(MineGuisMenu objMenu) {
        if (objMenu == null) { this.doLogO("the menu is null!"); return false; }
        if (this.vetMenu(objMenu) == false) { this.doLogO("the menu has already been removed!"); return false; }
        this.tabMenus.remove(objMenu.getSign(), objMenu);
        return true;
    }
    public boolean rmvMenu(String strSign) { return this.rmvMenu(this.getMenu(strSign)); }
    public boolean addBook(MineGuisBook objBook) {
        if (objBook == null) { this.doLogO("the book is null!"); return false; }
        if (this.vetBook(objBook) == true) { this.doLogO("the book has already been added!"); return false; }
        this.tabBooks.put(objBook.getSign(), objBook);
        return true;
    }
    public boolean rmvBook(MineGuisBook objBook) {
        if (objBook == null) { this.doLogO("the book is null!"); return false; }
        if (this.vetBook(objBook) == false) { this.doLogO("the book has already been removed!"); return false; }
        this.tabBooks.remove(objBook.getSign(), objBook);
        return true;
    }
    public boolean rmvBook(String strSign) { return this.rmvBook(this.getBook(strSign)); }
    //public boolean setConfigBit(String strKey, Boolean bitVal)      { this.getConfig().setBoolean(strKey, bitVal); return true; }
    //public boolean setConfigInt(String strKey, Integer intVal)      { this.getConfig().setInteger(strKey, intVal); return true; }
    //public boolean setConfigNum(String strKey, Double numVal)       { this.getConfig().setDouble(strKey, numVal); return true; }
    //public boolean setConfigStr(String strKey, String strVal)       { this.getConfig().setString(strKey, strVal); return true; }
    //public boolean setConfigArr(String strKey, List<String> arrVal) { this.getConfig().setStringList(strKey, arrVal); return true; }
    /* vetters */
    public boolean vetUser(MineGuisSign objSign)  { return this.tabUsers.containsKey(objSign); }
    public boolean vetUser(String strSign)        { return this.vetUser(MineGuisSign.get(strSign)); }
    public boolean vetUser(MineGuisUser objUser)  { return this.vetUser(objUser.getSign()); }
    public boolean vetUser(Player objUser)        { return this.vetUser(objUser.getUniqueId().toString()); }
    public boolean vetUser(HumanEntity objUser)   { return this.vetUser(objUser.getUniqueId().toString()); }
    public boolean vetItem(MineGuisSign objSign)  { return this.tabItems.containsKey(objSign); }
    public boolean vetItem(String strSign)        { return this.vetItem(MineGuisSign.get(strSign)); }
    public boolean vetItem(MineGuisItem objItem)  { return this.vetItem(objItem.getSign()); }
    public boolean vetItem(ItemStack objItem)     { return this.vetItem(objItem.getItemMeta().getDisplayName()); }
    public boolean vetItem(ItemMeta objMeta)      { return this.vetItem(objMeta.getDisplayName()); }
    public boolean vetMenu(MineGuisSign objSign)  { return this.tabMenus.containsKey(objSign); }
    public boolean vetMenu(String strSign)        { return this.vetMenu(MineGuisSign.get(strSign)); }
    public boolean vetMenu(MineGuisMenu objMenu)  { return this.vetMenu(objMenu.getSign()); }
    public boolean vetMenu(InventoryView objMenu) { return this.vetMenu(objMenu.getTitle()); }
    public boolean vetBook(MineGuisSign objSign)  { return this.tabBooks.containsKey(objSign); }
    public boolean vetBook(String strSign)        { return this.vetBook(MineGuisSign.get(strSign)); }
    public boolean vetBook(MineGuisBook objBook)  { return this.vetBook(objBook.getSign()); }
    public boolean vetConfig(String strPath)      { return this.getConfig().contains(strPath); }
    /* actions */
    public void doLogO(String strFormat, Object ... objArgs) {
        System.out.printf(String.format("%s:%s\n", this.getConfigStr("nameof_logo"), strFormat), objArgs);
    }
    public void doLogO(CommandSender objSender, String strFormat, Object ... objArgs) {
        this.doLogO(strFormat, objArgs);
        objSender.sendMessage(
            String.format(
                String.format(
                    "%s:%s",
                    this.getConfigStr("nameof_logo"),
                    strFormat
                ),
                objArgs
            )
        );
    }
    private boolean doInitExecuts() {
        this.getCommand("mineguis").setExecutor(new MineGuisExecut());
        this.getCommand("mguimain").setExecutor(new MineGuisExecutMain());
        this.getCommand("mguiitem").setExecutor(new MineGuisExecutItem());
        this.getCommand("mguiitemctor").setExecutor(new MineGuisExecutItemCtor());
        this.getCommand("mguiitemdtor").setExecutor(new MineGuisExecutItemDtor());
        this.getCommand("mguimenu").setExecutor(new MineGuisExecutMenu());
        this.getCommand("mguimenuctor").setExecutor(new MineGuisExecutMenuCtor());
        this.getCommand("mguimenudtor").setExecutor(new MineGuisExecutMenuDtor());
        this.getCommand("mguibook").setExecutor(new MineGuisExecutBook());
        this.getCommand("mguibookctor").setExecutor(new MineGuisExecutBookCtor());
        this.getCommand("mguibookdtor").setExecutor(new MineGuisExecutBookDtor());
        this.getCommand("mguiback").setExecutor(new MineGuisExecutBack());
        this.getCommand("mguivoid").setExecutor(new MineGuisExecutVoid());
        return true;
    }
    private boolean doInitListens() {
        this.getServer().getPluginManager().registerEvents(new MineGuisListen(), this);
        return true;
    }
    private boolean doInitPermits() {
        this.getServer().getPluginManager().addPermission(new MineGuisPermit());
        return true;
    }
    private boolean doInitConfigs() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        /*** yaml ***/
        /* this.doLogO("========<config_yaml>========"); */
        {
            Set<String> setKeys = this.getConfig().getKeys(true);
            /*this.doLogO("count: %d;", setKeys.size()); */
            for (String itrStrKey : setKeys) { /* this.doLogO(itrStrKey); */ }
        }
        return true;
    }
    private boolean doInitUsers() {
        Collection<? extends Player> arrPlayers = this.getServer().getOnlinePlayers();
        for (Player objPlayer : arrPlayers) {
            if (this.addUser(new MineGuisUser(objPlayer)) == false) {
                this.doLogO("failed to add the player: %s;", objPlayer.getName());
                return false;
            }
        }
        return true;
    }
    private boolean doInitItems() {
        /* this.doLogO("========<listof_item>========"); */
        if (this.vetConfig("listof_item")) {
            ConfigurationSection objSectionListOf = this.getConfigSec("listof_item");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                /* this.doLogO( String.format("count: %d;", setKeys.size()) ); */
            } else {
                this.doLogO( String.format("the listof config section is empty!") );
                return false;
            }
            for (String itrStrKey : setKeys) {
                ConfigurationSection itrObjSectionItem = objSectionListOf.getConfigurationSection(itrStrKey);
                String itrStrSign = null;
                String itrStrLore = null; List<String> itrArrLore = null;
                String itrStrIcon = null;
                String itrStrExec = null;
                if (itrObjSectionItem.contains("info")) {
                    ConfigurationSection itrObjSectionInfo = itrObjSectionItem.getConfigurationSection("info");
                    itrStrSign = itrObjSectionInfo.getString("sign");
                    itrStrIcon = itrObjSectionInfo.getString("icon");
                    if (itrObjSectionInfo.isString("lore")) {
                        itrStrLore = itrObjSectionInfo.getString("lore");
                    } else if (itrObjSectionInfo.isList("lore")) {
                        itrArrLore = itrObjSectionInfo.getStringList("lore");
                    } else {
                        MineGuis.get().doLogO("invalid item lore type!");
                        return false;
                    }
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"info\"!", itrStrKey);
                    return false;
                }
                if (itrObjSectionItem.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionItem.getConfigurationSection("data");
                    itrStrExec = itrObjSectionData.getString("exec");
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"data\"!", itrStrKey);
                    return false;
                }
                MineGuisItem itrObjItem = null;
                if (itrStrLore == null && itrArrLore == null) {
                    itrObjItem = new MineGuisItem(itrStrSign, itrStrIcon, itrStrExec);
                } else if (itrStrLore != null) {
                    itrObjItem = new MineGuisItem(itrStrSign, itrStrLore, itrStrIcon, itrStrExec);
                } else if (itrArrLore != null) {
                    itrObjItem = new MineGuisItem(itrStrSign, itrArrLore, itrStrIcon, itrStrExec);
                }
                if (this.addItem(itrObjItem)) {
                    /* this.doLogO("the item has been added: %s;", itrStrKey); */
                } else {
                    this.doLogO("failed to add the item: %s;", itrStrKey);
                    return false;
                }
            }
        } else {
            this.doLogO("the config section listof_item is not found!");
            return false;
        }
        return true;
    }
    private boolean doInitMenus() {
        /* this.doLogO("========<listof_menu>========"); */
        if (vetConfig("listof_menu")) {
            ConfigurationSection objSectionListOf = this.getConfigSec("listof_menu");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                /* this.doLogO( String.format("count: %d;", setKeys.size()) ); */
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
                    this.doLogO("config section \"%s\" does not have config section \"info\"!", itrStrKey);
                    return false;
                }
                MineGuisMenu itrObjMenu = new MineGuisMenu(itrStrSign, itrNumSize);
                if (this.addMenu(itrObjMenu)) {
                    /* this.doLogO("the menu has been added: %s;", itrStrKey); */
                } else {
                    this.doLogO("failed to add the menu: %s;", itrStrKey);
                    return false;
                }
                if (itrObjSectionMenu.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionMenu.getConfigurationSection("data");
                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
                    for (String itrStrItem : itrSetItems) {
                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
                        String itrStrItemSign = itrObjSectionItem.getString("sign");
                        List<Integer> itrArrItemSlot = itrObjSectionItem.getIntegerList("slot");
                        if (this.vetItem(itrStrItemSign)) {
                            if (itrObjMenu.setItem(
                                this.getItem(itrStrItemSign),
                                itrArrItemSlot.get(0), itrArrItemSlot.get(1)
                            ) == true) {
                                /* this.doLogO("the menu item has been set; sign: %s; slot: [%d,%d]",
                                itrStrItemSign, itrArrItemSlot.get(0), itrArrItemSlot.get(1)); */
                            } else {
                                this.doLogO("failed to set the menu item: %s;", itrStrItemSign);
                                return false;
                            }
                        } else {
                            this.doLogO("the menu item is not found: %s;", itrStrItemSign);
                            return false;
                        }
                    }
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"data\"!", itrStrKey);
                    return false;
                }
            }
        } else {
            this.doLogO("the config section listof_menu is not found!");
            return false;
        }
        return true;
    }
    private boolean doInitBooks() {
        /*this.doLogO("========<listof_book>========"); */
        if (this.vetConfig("listof_book")) {
            ConfigurationSection objSectionListOf = this.getConfigSec("listof_book");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            /*if (setKeys.size() > 0) {
                this.doLogO( String.format("count: %d;", setKeys.size()) );
            } else {
                this.doLogO( "the config section listof_book is empty!" );
                return false;
            }*/
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
                    /* this.doLogO("the book has been added: %s;", itrStrKey); */
                } else {
                    this.doLogO("failed to add the book: %s;", itrStrSign);
                    return false;
                }
                if (itrObjSectionBook.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionBook.getConfigurationSection("data");
                    Set<String> itrSetItems = itrObjSectionData.getKeys(false);
                    for (String itrStrItem : itrSetItems) {
                        ConfigurationSection itrObjSectionItem = itrObjSectionData.getConfigurationSection(itrStrItem);
                        String itrStrItemSign = itrObjSectionItem.getString("sign");
                        List<Integer> itrArrItemSlot = itrObjSectionItem.getIntegerList("slot");
                        if (this.vetItem(itrStrItemSign)) {
                            if (itrObjBook.setItem(
                                this.getItem(itrStrItemSign),
                                itrArrItemSlot.get(0), itrArrItemSlot.get(1), itrArrItemSlot.get(2)
                            ) == true) {
                                /* this.doLogO("the book item has been set: %s;", itrStrItemSign); */
                            } else {
                                this.doLogO("failed to set the book item: %s;", itrStrItemSign);
                                return false;
                            }
                        } else {
                            this.doLogO("the book item is not found: %s;", itrStrItemSign);
                            return false;
                        }
                    }
                } else {
                    this.doLogO("config section \"%s\" does not have config section \"data\"!", itrStrKey);
                    return false;
                }
            }
        } else {
            this.doLogO("the config section listof_book is not found!");
            return false;
        }
        return true;
    }
    private boolean doQuitUsers() {
        this.tabUsers.clear();
        return true;
    }
    private boolean doQuitItems() {
        this.tabItems.clear();
        return true;
    }
    private boolean doQuitMenus() {
        this.tabMenus.clear();
        return true;
    }
    private boolean doQuitBooks() {
        this.tabBooks.clear();
        return true;
    }
    /* handles */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        this.tabUsers = new HashMap<MineGuisSign, MineGuisUser>();
        this.tabItems = new HashMap<MineGuisSign, MineGuisItem>();
        this.tabMenus = new HashMap<MineGuisSign, MineGuisMenu>();
        this.tabBooks = new HashMap<MineGuisSign, MineGuisBook>();
        /* work */
        this.addItem(new MineGuisItem("mineguis", "basic gui command", "KNOWLEDGE_BOOK", "mineguis"));
        this.addItem(new MineGuisItem("mineguis menu", "basic gui menu command", "PAPER", "mineguis menu"));
        this.addItem(new MineGuisItem("mineguis book", "basic gui book command", "BOOK", "mineguis book"));
        this.addItem(new MineGuisItem("void", "____", "BLACK_STAINED_GLASS_PANE", "mguivoid"));
        this.addItem(new MineGuisItem("main", "open the main menu", "KNOWLEDGE_BOOK", "mguimain"));
        this.addItem(new MineGuisItem("back", "open the last opened menu", "COMPASS", "mguiback"));
        this.addMenu(new MineGuisMenuMain());
        this.addBook(new MineGuisBookMain());
        MineGuisMenu objMenuMain = this.getMenu("Menue");
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
        /** users **/
        if (this.doInitUsers()) {
            this.doLogO("init users is done;");
        } else {
            this.doLogO("init users is failed;");
        }
        /** items **/
        if (this.doInitItems()) {
            this.doLogO("init items is done;");
        } else {
            this.doLogO("init items is failed;");
        }
        /** menus **/
        if (this.doInitMenus()) {
            this.doLogO("init menus is done;");
        } else {
            this.doLogO("init menus is failed;");
        }
        /** books **/
        if (this.doInitBooks()) {
            this.doLogO("init books is done;");
        } else {
            this.doLogO("init books is failed;");
        }
        /* quit */
    }
    @Override
    public void onDisable() {
        /* init */
        /* work */
        /** users **/
        if (this.doQuitUsers()) {
            this.doLogO("quit users is done;");
        } else {
            this.doLogO("quit users is failed;");
        }
        /** items **/
        if (this.doQuitItems()) {
            this.doLogO("quit items is done;");
        } else {
            this.doLogO("quit items is failed;");
        }
        /** menus **/
        if (this.doQuitMenus()) {
            this.doLogO("quit menus is done;");
        } else {
            this.doLogO("quit menus is failed;");
        }
        /** books **/
        if (this.doQuitBooks()) {
            this.doLogO("quit books is done;");
        } else {
            this.doLogO("quit books is failed;");
        }
        /* quit */
        this.tabUsers = null;
        this.tabItems = null;
        this.tabMenus = null;
        this.tabBooks = null;
        objInstance = null;
    }
}
/* endfile */