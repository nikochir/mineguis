/* package */
package nikochir;
/* include */
import nikochir.MineGuisUser;
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
import org.bukkit.entity.Player;
/** nkyori - text facilities **/
import net.kyori.adventure.text.Component;
/*
 * MineGuis class
 * > description:
 * -> initializer-terminator plugin singleton;
 * -> handles all menus;
 * -> manages permissions, menus and items;
*/
public class MineGuis extends JavaPlugin {
    /* members */
    private static MineGuis objInstance;
    private HashMap<Player, MineGuisUser> mapUsers;
    private HashMap<String, MineGuisMenu> mapMenus;
    private HashMap<String, MineGuisBook> mapBooks;
    /* getters */
    public static MineGuis get() { return objInstance; }
    public MineGuisMenu getUser()                { return this.getMenu(this.getConfigStr("nameof_main")); }
    public MineGuisMenu getUser(String strTitle) { return mapMenus.get(strTitle); }
    public MineGuisMenu getMenu()                { return this.getMenu(this.getConfigStr("nameof_main")); }
    public MineGuisMenu getMenu(String strTitle) { return mapMenus.get(strTitle); }
    public MineGuisBook getBook()                { return this.getBook(this.getConfigStr("nameof_main")); }
    public MineGuisBook getBook(String strTitle) { return mapBooks.get(strTitle); }
    public Player getPlayer(String strPlayer)    { return this.getServer().getPlayer(strPlayer); }
    public Boolean getConfigBit(String strKey)      { return this.getConfig().getBoolean(strKey); }
    public Integer getConfigInt(String strKey)      { return this.getConfig().getInt(strKey); }
    public Double getConfigNum(String strKey)       { return this.getConfig().getDouble(strKey); }
    public String getConfigStr(String strKey)       { return this.getConfig().getString(strKey); }
    public List<String> getConfigArr(String strKey) { return this.getConfig().getStringList(strKey); }
    /* setters */
    public MineGuisUser setUser(Player objPlayer) {
        if (objPlayer != null) {
            if (this.vetUser(objPlayer)) { return null; }
            MineGuisUser objUser = new MineGuisUser(objPlayer);
            this.mapUsers.put(objPlayer, objUser);
            return objUser;
        } else {
            this.mapUsers.remove(objPlayer);
            return null;
        }
    }
    public MineGuisMenu setMenu(String strTitle, Integer numRows) {
        if (this.vetMenu(strTitle)) { return null; }
        MineGuisMenu objMenu = new MineGuisMenu(strTitle, numRows);
        this.mapMenus.put(strTitle, objMenu);
        this.getServer().getPluginManager().registerEvents(objMenu, this);
        return objMenu;
    }
    public MineGuisBook setBook(String strTitle, Integer numPages, Integer numLines) {
        if (this.vetBook(strTitle)) { return null; }
        MineGuisBook objBook = new MineGuisBook(strTitle, numPages, numLines);
        this.mapBooks.put(strTitle, objBook);
        return objBook;
    }
    /* vetters */
    public Boolean vetUser(Player objUser) { return mapUsers.containsKey(objUser); }
    public Boolean vetUser(String strUser) { return mapUsers.containsKey(this.getPlayer(strUser)); }
    public Boolean vetMenu(String strName) { return mapMenus.containsKey(strName); }
    public Boolean vetBook(String strName) { return mapBooks.containsKey(strName); }
    /* actions */
    public void doLog(String strLog) {
        System.out.println(this.getConfigStr("nameof_log") + strLog);
    }
    /* handles */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        this.mapUsers = new HashMap<Player, MineGuisUser>();
        this.mapMenus = new HashMap<String, MineGuisMenu>();
        this.mapBooks = new HashMap<String, MineGuisBook>();
        /* work */
        /** conf **/
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        /** regs **/
        MineGuisMenu objMenuMain = this.setMenu(this.getConfigStr("nameof_main"), this.getConfigInt("sizeof_main"));
        MineGuisMenu objMenuLeft = this.setMenu("left", 1);
        MineGuisMenu objMenuRigt = this.setMenu("rigt", 1);
        this.doLog("creating an item...");
        objMenuMain.setItem(1, 3, new MineGuisItemMenu(objMenuLeft));
        objMenuMain.setItem(1, 7, new MineGuisItemMenu(objMenuRigt));
        objMenuMain.setItem(3, 5, new MineGuisItemQuit());
        objMenuLeft.setItem(7, new MineGuisItemMenu(objMenuMain));
        objMenuLeft.setItem(8, new MineGuisItemQuit());
        objMenuRigt.setItem(7, new MineGuisItemMenu(objMenuMain));
        objMenuRigt.setItem(8, new MineGuisItemQuit());
        MineGuisBook objBookMain = this.setBook(
            this.getConfigStr("nameof_main"),
            this.getConfigInt("sizeof_main"),
            this.getConfigInt("sizeof_main")
        );
        /*** execut ***/
        this.getServer().getPluginCommand("mineguis").setExecutor(new MineGuisExecut());
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