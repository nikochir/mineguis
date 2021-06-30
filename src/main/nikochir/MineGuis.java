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
    /* getters */
    public static MineGuis get() { return objInstance; }
    public static <TName extends MineGuisUnit> HashMap<String, TName> getUnits() {
        final HashMap<String, TName> tabUnits = new HashMap<String, TName>();
        return tabUnits;
    }
    public <TName extends MineGuisUnit> TName getUnit(String strSign) { return this.<TName>getUnits().get(strSign); }
    public MineGuisUser getUser(String strSign)   { return this.<MineGuisUser>getUnit(strSign); }
    public MineGuisUser getUser(Player objPlayer) { return this.getUser(objPlayer.getUniqueId().toString()); }
    public MineGuisItem getItem(String strSign)   { return this.<MineGuisItem>getUnit(strSign); }
    public MineGuisItem getItemMain()             { return this.getItem(this.getConfigStr("nameof_main")); }
    public MineGuisMenu getMenu(String strSign)   { return this.<MineGuisMenu>getUnit(strSign); }
    public MineGuisMenu getMenuMain()             { return this.getMenu(this.getConfigStr("nameof_main")); }
    public MineGuisBook getBook(String strSign)   { return this.<MineGuisBook>getUnit(strSign); }
    public MineGuisBook getBookMain()             { return this.getBook(this.getConfigStr("nameof_main")); }
    public PluginCommand getCommand(String strCommand) { return this.getServer().getPluginCommand(strCommand); }
    public Player getPlayer(String strPlayer)          { return this.getServer().getPlayer(strPlayer); }
    public Boolean getConfigBit(String strKey)      { return this.getConfig().getBoolean(strKey); }
    public Integer getConfigInt(String strKey)      { return this.getConfig().getInt(strKey); }
    public Double getConfigNum(String strKey)       { return this.getConfig().getDouble(strKey); }
    public String getConfigStr(String strKey)       { return this.getConfig().getString(strKey); }
    public List<String> getConfigArr(String strKey) { return this.getConfig().getStringList(strKey); }
    /* setters */
    public <TName extends MineGuisUnit> Boolean addUnit(TName objUnit) {
        if (objUnit == null) { this.doLog("the unit is null!"); return false; }
        if (this.<TName>vetUnit(objUnit) == true) { this.doLog("this unit has already been added!"); return false; }
        this.<TName>getUnits().put(objUnit.getSign(), objUnit);
        return true;
    }
    public <TName extends MineGuisUnit> Boolean rmvUnit(TName objUnit) {
        if (objUnit == null) { this.doLog("the unit is null!"); return false; }
        if (this.<TName>vetUnit(objUnit) == false) { this.doLog("this unit has already been removed!"); return false; }
        this.<TName>getUnits().remove(objUnit.getSign(), objUnit);
        return true;
    }
    public <TName extends MineGuisUnit> Boolean rmvUnit(String strSign) { return this.<TName>rmvUnit(getUnit(strSign)); }
    //public Boolean setConfigBit(String strKey, Boolean bitVal)      { this.getConfig().setBoolean(strKey, bitVal); return true; }
    //public Boolean setConfigInt(String strKey, Integer intVal)      { this.getConfig().setInteger(strKey, intVal); return true; }
    //public Boolean setConfigNum(String strKey, Double numVal)       { this.getConfig().setDouble(strKey, numVal); return true; }
    //public Boolean setConfigStr(String strKey, String strVal)       { this.getConfig().setString(strKey, strVal); return true; }
    //public Boolean setConfigArr(String strKey, List<String> arrVal) { this.getConfig().setStringList(strKey, arrVal); return true; }
    /* vetters */
    public <TName extends MineGuisUnit> Boolean vetUnit(String strSign) { return this.<TName>getUnits().containsKey(strSign); }
    public <TName extends MineGuisUnit> Boolean vetUnit(TName objUnit)  { return this.<TName>getUnits().containsKey(objUnit.getSign()); }
    /* actions */
    public void doLog(String strLog) { System.out.println(this.getConfigStr("nameof_log") + strLog); }
    /* handles */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        /* work */
        /** conf **/
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        /** regs **/
        this.<MineGuisItem>addUnit(new MineGuisItem("mguisnap", "void", "____", Material.BLACK_STAINED_GLASS));
        this.<MineGuisItem>addUnit(new MineGuisItem("mguimain", "main", "open the main menu", Material.COMPASS));
        this.<MineGuisItem>addUnit(new MineGuisItem("mguiback", "back", "open the last opened menu", Material.COMPASS));
        this.<MineGuisMenu>addUnit(new MineGuisMenu(getConfigStr("nameof_main"), getConfigInt("sizeof_main")));
        this.<MineGuisBook>addUnit(new MineGuisBook(getConfigStr("nameof_main"), getConfigInt("sizeof_useb")));
        /*** execut ***/
        this.getCommand("mineguis").setExecutor(new MineGuisExecut());
        this.getCommand("mguimain").setExecutor(new MineGuisExecutMain());
        this.getCommand("mguimenu").setExecutor(new MineGuisExecutMenu());
        this.getCommand("mguibook").setExecutor(new MineGuisExecutBook());
        this.getCommand("mguiback").setExecutor(new MineGuisExecutBack());
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
        objInstance = null;
    }
    /* handles */
}
/* endfile */