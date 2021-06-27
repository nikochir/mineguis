/* package */
package nikochir;
/* include */
import nikochir.*;
import nikochir.menu.*;
import nikochir.item.*;
import nikochir.execut.*;
import nikochir.listen.*;
import nikochir.permit.*;
/** javkit - standard utilities **/
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/** bukkit - plugin, config, events **/
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
/* MineGuis class
 * > description:
 * -> initializer-terminator plugin singleton;
 * -> handles all menus;
 * -> manages permissions, menus and items;
*/
public class MineGuis extends JavaPlugin {
    /* members */
    private static MineGuis objInstance;
    private HashMap<String, MineGuisUser> mapUsers;
    private HashMap<String, MineGuisMenu> mapMenus;
    private HashMap<String, MineGuisMenuBook> mapBooks;
    /* getters */
    public static MineGuis get() { return objInstance; }
    public MineGuisUser getUser()                      { return this.getUser(this.getConfigStr("nameof_main")); }
    public MineGuisUser getUser(String strPlayer)      { return mapUsers.get(strPlayer); }
    public MineGuisUser getUser(Player objPlayer)      { return mapUsers.get(objPlayer.getName()); }
    public MineGuisMenu getMenu()                      { return this.getMenu(this.getConfigStr("nameof_main")); }
    public MineGuisMenu getMenu(String strTitle)       { return mapMenus.get(strTitle); }
    public MineGuisMenuBook getBook()                  { return this.getBook(this.getConfigStr("nameof_main")); }
    public MineGuisMenuBook getBook(String strTitle)   { return mapBooks.get(strTitle); }
    public PluginCommand getCommand(String strCommand) { return this.getServer().getPluginCommand(strCommand); }
    public Player getPlayer(String strPlayer)          { return this.getServer().getPlayer(strPlayer); }
    public Boolean getConfigBit(String strKey)      { return this.getConfig().getBoolean(strKey); }
    public Integer getConfigInt(String strKey)      { return this.getConfig().getInt(strKey); }
    public Double getConfigNum(String strKey)       { return this.getConfig().getDouble(strKey); }
    public String getConfigStr(String strKey)       { return this.getConfig().getString(strKey); }
    public List<String> getConfigArr(String strKey) { return this.getConfig().getStringList(strKey); }
    /* setters */
    public Boolean addUser(MineGuisUser objUser) {
        if (objUser == null) {
            this.doLog("the user is null!");
            return false;
        }
        if (this.vetUser(objUser.getName()) == true) {
            this.doLog("this user is already added!");
            return false;
        }
        this.doLog("user has been added: " + objUser.getName());
        this.mapUsers.put(objUser.getName(), objUser);
        return true;
    }
    public Boolean addUser(Player objPlayer) { return addUser(new MineGuisUser(objPlayer)); }
    public Boolean addUser(String strPlayer) { return addUser(new MineGuisUser(strPlayer)); }
    public Boolean rmvUser(MineGuisUser objUser) {
        if (this.vetUser(objUser.getPlayer()) == false) {
            this.doLog("this user is already removed!");
            return false;
        }
        this.mapUsers.remove(objUser.getPlayer());
        return true;
    }
    public Boolean rmvUser(Player objPlayer) { return rmvUser(getUser(objPlayer)); }
    public Boolean rmvUser(String strPlayer) { return rmvUser(getUser(strPlayer)); }
    public Boolean addMenu(MineGuisMenu objMenu) {
        if (this.vetMenu(objMenu.getTitle()) == true) {
            this.doLog("this menu is already added!");
            return false;
        }
        this.mapMenus.put(objMenu.getTitle(), objMenu);
        this.getServer().getPluginManager().registerEvents(objMenu, this);
        return true;
    }
    public Boolean rmvMenu(MineGuisMenu objMenu) {
        if (this.vetMenu(objMenu.getTitle()) == false) {
            this.doLog("this menu is already removed!");
            return false;
        }
        this.mapMenus.remove(objMenu.getTitle());
        return true;
    }
    public Boolean addBook(MineGuisMenuBook objBook) {
        if (this.vetBook(objBook.getTitle()) == true) { return false; }
        this.mapBooks.put(objBook.getTitle(), objBook);
        return true;
    }
    public Boolean rmvBook(MineGuisMenuBook objBook) {
        if (this.vetMenu(objBook.getTitle()) == false) {
            this.doLog("this menu is already removed!");
            return false;
        }
        this.mapBooks.remove(objBook.getTitle());
        return true;
    }
    //public Boolean setConfigBit(String strKey, Boolean bitVal)      { this.getConfig().setBoolean(strKey, bitVal); return true; }
    //public Boolean setConfigInt(String strKey, Integer intVal)      { this.getConfig().setInteger(strKey, intVal); return true; }
    //public Boolean setConfigNum(String strKey, Double numVal)       { this.getConfig().setDouble(strKey, numVal); return true; }
    //public Boolean setConfigStr(String strKey, String strVal)       { this.getConfig().setString(strKey, strVal); return true; }
    //public Boolean setConfigArr(String strKey, List<String> arrVal) { this.getConfig().setStringList(strKey, arrVal); return true; }
    /* vetters */
    public Boolean vetUser(Player objUser) { return mapUsers.containsKey(objUser.getName()); }
    public Boolean vetUser(String strUser) { return mapUsers.containsKey(strUser); }
    public Boolean vetMenu(String strName) { return mapMenus.containsKey(strName); }
    public Boolean vetBook(String strName) { return mapBooks.containsKey(strName); }
    /* actions */
    public void doLog(String strLog) { System.out.println(this.getConfigStr("nameof_log") + strLog); }
    /* handles */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        this.mapUsers = new HashMap<String, MineGuisUser>();
        this.mapMenus = new HashMap<String, MineGuisMenu>();
        this.mapBooks = new HashMap<String, MineGuisMenuBook>();
        /* work */
        /** conf **/
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        /** regs **/
        MineGuisMenu objMenuMain = new MineGuisMenu(this.getConfigStr("nameof_main"), this.getConfigInt("sizeof_main"));
        MineGuisMenu objMenuLeft = new MineGuisMenu("left", 10);
        MineGuisMenu objMenuRigt = new MineGuisMenu("rigt", 10);
        objMenuMain.setItem(1, 3, new MineGuisItemMenu(objMenuMain, objMenuLeft));
        objMenuMain.setItem(1, 7, new MineGuisItemMenu(objMenuMain, objMenuRigt));
        objMenuMain.setItem(3, 5, new MineGuisItemQuit());
        objMenuLeft.setItem(7, new MineGuisItemMenu(objMenuLeft, objMenuMain));
        objMenuLeft.setItem(8, new MineGuisItemQuit());
        objMenuRigt.setItem(7, new MineGuisItemMenu(objMenuRigt, objMenuMain));
        objMenuRigt.setItem(8, new MineGuisItemQuit());
        MineGuisMenuBook objBookMain = new MineGuisMenuBook(
            this.getConfigStr("nameof_main"),
            this.getConfigInt("sizeof_main"),
            this.getConfigInt("sizeof_main")
        );
        objBookMain.getPage(1).setItem(1, 1, new MineGuisItemCall("menu", new String[]{ "left" }));
        objBookMain.getPage(1).setItem(1, 2, new MineGuisItemCall("menu", new String[]{ "mineguis" }));
        objBookMain.getPage(1).setItem(1, 3, new MineGuisItemCall("menu", new String[]{ "rigt" }));
        objBookMain.getPage(2).setItem(1, 1, new MineGuisItemCall("book", new String[]{ "mineguis" }));
        List<String> arrConfig;
        arrConfig = this.getConfigArr("listof_menu");
        for (int itr = 0; itr < arrConfig.size(); itr++) { this.doLog(arrConfig.get(itr)); }
        arrConfig = this.getConfigArr("listof_book");
        for (int itr = 0; itr < arrConfig.size(); itr++) { this.doLog(arrConfig.get(itr)); }
        /*** execut ***/
        this.getCommand("mineguis").setExecutor(new MineGuisExecut());
        this.getCommand("menu").setExecutor(new MineGuisExecutMenu());
        this.getCommand("book").setExecutor(new MineGuisExecutBook());
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
        this.mapUsers.clear();
        this.mapMenus.clear();
        this.mapBooks.clear();
        objInstance = null;
    }
    /* handles */
}
/* end_of_file */