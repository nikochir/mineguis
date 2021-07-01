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
import java.util.HashMap;
/** bukkit - plugin, config, events **/
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.configuration.file.FileConfiguration;
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
    public List<String> getConfigArr(String strKey) { return this.getConfig().getStringList(strKey); }
    /* setters */
    public Boolean addUser(MineGuisUser objUser) {
        if (objUser == null) { this.doLog("the user is null!"); return false; }
        if (this.vetUser(objUser) == true) { this.doLog("the user has already been added!"); return false; }
        this.tabUsers.put(objUser.getSign(), objUser);
        return true;
    }
    public Boolean rmvUser(MineGuisUser objUser) {
        if (objUser == null) { this.doLog("the user is null!"); return false; }
        if (this.vetUser(objUser) == false) { this.doLog("the user has already been removed!"); return false; }
        this.tabUsers.remove(objUser.getSign(), objUser);
        return true;
    }
    public Boolean rmvUser(String strSign) { return this.rmvUser(this.getUser(strSign)); }
    public Boolean addItem(MineGuisItem objItem) {
        if (objItem == null) { this.doLog("the item is null!"); return false; }
        if (this.vetItem(objItem) == true) { this.doLog("the item has already been added!"); return false; }
        this.tabItems.put(objItem.getSign(), objItem);
        return true;
    }
    public Boolean rmvItem(MineGuisItem objItem) {
        if (objItem == null) { this.doLog("the item is null!"); return false; }
        if (this.vetItem(objItem) == false) { this.doLog("the item has already been removed!"); return false; }
        this.tabItems.remove(objItem.getSign(), objItem);
        return true;
    }
    public Boolean rmvItem(String strSign) { return this.rmvItem(this.getItem(strSign)); }
    public Boolean addMenu(MineGuisMenu objMenu) {
        if (objMenu == null) { this.doLog("the menu is null!"); return false; }
        if (this.vetMenu(objMenu) == true) { this.doLog("the menu has already been added!"); return false; }
        this.tabMenus.put(objMenu.getSign(), objMenu);
        return true;
    }
    public Boolean rmvMenu(MineGuisMenu objMenu) {
        if (objMenu == null) { this.doLog("the menu is null!"); return false; }
        if (this.vetMenu(objMenu) == false) { this.doLog("the menu has already been removed!"); return false; }
        this.tabMenus.remove(objMenu.getSign(), objMenu);
        return true;
    }
    public Boolean rmvMenu(String strSign) { return this.rmvMenu(this.getMenu(strSign)); }
    public Boolean addBook(MineGuisBook objBook) {
        if (objBook == null) { this.doLog("the book is null!"); return false; }
        if (this.vetBook(objBook) == true) { this.doLog("the book has already been added!"); return false; }
        this.tabBooks.put(objBook.getSign(), objBook);
        return true;
    }
    public Boolean rmvBook(MineGuisBook objBook) {
        if (objBook == null) { this.doLog("the book is null!"); return false; }
        if (this.vetBook(objBook) == false) { this.doLog("the book has already been removed!"); return false; }
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
    /* actions */
    public void doLog(String strLog) { System.out.println(this.getConfigStr("nameof_log") + strLog); }
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
        /** conf **/
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        /** regs **/
        this.addItem(new MineGuisItem("void", "____", Material.BLACK_STAINED_GLASS_PANE, "mguivoid"));
        this.addItem(new MineGuisItem("main", "open the main menu", Material.COMPASS, "mguimain"));
        this.addItem(new MineGuisItem("back", "open the last opened menu", Material.COMPASS, "mguiback"));
        this.addMenu(new MineGuisMenu(getConfigStr("nameof_main"), getConfigInt("sizeof_main")));
        MineGuisMenu objMenu = this.getMenu(getConfigStr("nameof_main"));
        objMenu.setItem(this.getItem("main"), 1);
        objMenu.setItem(this.getItem("back"), 2);
        this.addBook(new MineGuisBook(getConfigStr("nameof_main"), getConfigInt("sizeof_useb")));
        /*** execut ***/
        this.getCommand("mineguis").setExecutor(new MineGuisExecut());
        this.getCommand("mguimain").setExecutor(new MineGuisExecutMain());
        this.getCommand("mguimenu").setExecutor(new MineGuisExecutMenu());
        this.getCommand("mguibook").setExecutor(new MineGuisExecutBook());
        this.getCommand("mguiback").setExecutor(new MineGuisExecutBack());
        this.getCommand("mguivoid").setExecutor(new MineGuisExecutVoid());
        /*** listen ***/
        this.getServer().getPluginManager().registerEvents(new MineGuisListen(), this);
        /*** permit ***/
        this.getServer().getPluginManager().addPermission(new MineGuisPermit());
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
    /* handles */
}
/* endfile */