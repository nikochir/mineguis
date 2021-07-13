/* package */
package src.main.nikochir;
/* include */
import src.main.nikochir.*;
import src.main.nikochir.kernel.*;
import src.main.nikochir.execut.*;
import src.main.nikochir.listen.*;
import src.main.nikochir.permit.*;
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
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.Material;
/* Main class
 * > description:
 * -> initializer-terminator plugin singleton;
 * -> handles all menus;
 * -> manages permissions, menus and items;
*/
public class Main extends JavaPlugin {
    /* members */
    private static Main objInstance;
    /* getters */
    public static Main get() { return objInstance; }
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
    //public boolean setConfigBit(String strKey, Boolean bitVal)      { this.getConfig().setBoolean(strKey, bitVal); return true; }
    //public boolean setConfigInt(String strKey, Integer intVal)      { this.getConfig().setInteger(strKey, intVal); return true; }
    //public boolean setConfigNum(String strKey, Double numVal)       { this.getConfig().setDouble(strKey, numVal); return true; }
    //public boolean setConfigStr(String strKey, String strVal)       { this.getConfig().setString(strKey, strVal); return true; }
    //public boolean setConfigArr(String strKey, List<String> arrVal) { this.getConfig().setStringList(strKey, arrVal); return true; }
    /* vetters */
    public boolean vetConfig(String strPath)      { return this.getConfig().contains(strPath); }
    /* actions */
    public void doLogO(String strFormat, Object ... objArgs) {
        System.out.printf(String.format("%s:%s\n", this.getConfigStr("nameof_logo"), strFormat), objArgs);
    }
    public void doLogO(CommandSender objSender, String strFormat, Object ... objArgs) {
        System.out.printf(String.format("%s:%s\n", this.getConfigStr("nameof_logo"), strFormat), objArgs);
        objSender.sendMessage(String.format(strFormat, objArgs));
    }
    public Boolean doInitExecuts() {
        /* defs */
        /** user **/
        this.getCommand("mineguis").setExecutor(new Execut());
        this.getCommand("mguimain").setExecutor(new ExecutMain());
        this.getCommand("e").setExecutor(new ExecutMain());
        this.getCommand("mguivoid").setExecutor(new ExecutVoid());
        this.getCommand("mguiitem").setExecutor(new ExecutItem());
        this.getCommand("mguimenu").setExecutor(new ExecutMenu());
        this.getCommand("mguibook").setExecutor(new ExecutBook());
        this.getCommand("mguiback").setExecutor(new ExecutBack());
        this.getCommand("mguiinfo").setExecutor(new ExecutInfo());
        /** oper **/
        /** over **/
        /* quit */
        return true;
    }
    public Boolean doInitListens() {
        this.getServer().getPluginManager().registerEvents(new Listen(), this);
        return true;
    }
    public Boolean doInitPermits() {
        this.getServer().getPluginManager().addPermission(new Permit());
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
            for (String itrStrKey : setKeys) { /* this.doLogO(itrStrKey); */ }
        }
        return true;
    }
    /* handles */
    @Override
    public void onEnable() {
        /* init */
        objInstance = this;
        /* work */
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
        if (User.doInit()) {
            this.doLogO("init users is done;");
        } else {
            this.doLogO("init users is failed;");
        }
        /** items **/
        if (Item.doInit()) {
            this.doLogO("init items is done;");
        } else {
            this.doLogO("init items is failed;");
        }
        /** menus **/
        if (Menu.doInit()) {
            this.doLogO("init menus is done;");
        } else {
            this.doLogO("init menus is failed;");
        }
        /** books **/
        if (Book.doInit()) {
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
        if (User.doQuit()) {
            this.doLogO("quit users is done;");
        } else {
            this.doLogO("quit users is failed;");
        }
        /** items **/
        if (Item.doQuit()) {
            this.doLogO("quit items is done;");
        } else {
            this.doLogO("quit items is failed;");
        }
        /** menus **/
        if (Menu.doQuit()) {
            this.doLogO("quit menus is done;");
        } else {
            this.doLogO("quit menus is failed;");
        }
        /** books **/
        if (Book.doQuit()) {
            this.doLogO("quit books is done;");
        } else {
            this.doLogO("quit books is failed;");
        }
        /* quit */
        objInstance = null;
    }
}
/* endfile */