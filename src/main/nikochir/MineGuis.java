/* package */
package nikochir;
/* include */
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
    private HashMap<String, MineGuisMenu> mapMenus;
    /* getters */
    public static MineGuis get() { return objInstance; }
    public MineGuisMenu getMenu()                { return this.getMenu(this.getConfigStr("nameof_main")); }
    public MineGuisMenu getMenu(String strTitle) { return mapMenus.get(strTitle); }
    public Boolean getConfigBit(String strKey)      { return this.getConfig().getBoolean(strKey); }
    public Integer getConfigInt(String strKey)      { return this.getConfig().getInt(strKey); }
    public Double getConfigNum(String strKey)       { return this.getConfig().getDouble(strKey); }
    public String getConfigStr(String strKey)       { return this.getConfig().getString(strKey); }
    public List<String> getConfigArr(String strKey) { return this.getConfig().getStringList(strKey); }
    /* setters */
    public MineGuisMenu setMenu(Integer numRows, String strTitle) {
        if (vetMenu(strTitle)) { return null; }
        MineGuisMenu objMenu = new MineGuisMenu(numRows, strTitle);
        mapMenus.put(strTitle, objMenu);
        this.getServer().getPluginManager().registerEvents(objMenu, this);
        return objMenu;
    }
    /* vetters */
    public Boolean vetMenu(String strTitle) { return mapMenus.containsKey(strTitle); }
    /* command */
    public void doLog(String strLog) {
        System.out.println(this.getConfigStr("nameof_olog") + strLog);
    }
    /* onevent */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        mapMenus = new HashMap<String, MineGuisMenu>();
        /* work */
        /** conf **/
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        /** regs **/
        this.setMenu(this.getConfigInt("sizeof_main"), this.getConfigStr("nameof_main"));
        this.setMenu(1, "left");
        this.setMenu(1, "rigt");
        this.doLog("creating an item...");
        this.getMenu().setItem(0, 2, new MineGuisItemMenu("left"));
        this.getMenu().setItem(0, 6, new MineGuisItemMenu("rigt"));
        this.getMenu().setItem(2, 4, new MineGuisItemQuit());
        this.getMenu("left").setItem(6, new MineGuisItemMenu());
        this.getMenu("left").setItem(7, new MineGuisItemQuit());
        this.getMenu("rigt").setItem(6, new MineGuisItemMenu());
        this.getMenu("rigt").setItem(7, new MineGuisItemQuit());
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
        mapMenus.clear();
        objInstance = null;
    }
    /* onevent */
}
/* end_of_file */