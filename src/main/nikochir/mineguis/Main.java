/* package */

package nikochir.mineguis;

/* include */

import nikochir.mineguis.config.*;
import nikochir.mineguis.execut.*;
import nikochir.mineguis.listen.*;
import nikochir.mineguis.permit.*;
import nikochir.mineguis.kernel.*;

/** java - standard utilities **/

import java.util.List;
import java.util.Set;

/** bukkit - plugin, config, events **/

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.configuration.ConfigurationSection;

import org.bukkit.event.Listener;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import org.bukkit.permissions.Permission;

import org.bukkit.entity.Player;

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
    public List<Player> getPlayerList(String strName)  { return this.getServer().matchPlayer(strName); }

    
    public Set<String> getConfigKeys()              { return this.getConfig().getKeys(true); }
    public Object getConfigObj(String strKey)       { return this.getConfig().get(strKey); }
    public Boolean getConfigBit(String strKey)      { return this.getConfig().getBoolean(strKey); }
    public Integer getConfigInt(String strKey)      { return this.getConfig().getInt(strKey); }
    public Double getConfigNum(String strKey)       { return this.getConfig().getDouble(strKey); }
    public String getConfigStr(String strKey)       { return this.getConfig().getString(strKey); }
    public String getConfigStrMain()                { return this.getConfigStr("nameof_main"); }
    public List<Boolean> getConfigBitList(String strKey)    { return this.getConfig().getBooleanList(strKey); }
    public List<Integer> getConfigIntList(String strKey)    { return this.getConfig().getIntegerList(strKey); }
    public List<Double> getConfigNumList(String strKey)     { return this.getConfig().getDoubleList(strKey); }
    public List<String> getConfigStrList(String strKey)     { return this.getConfig().getStringList(strKey); }
    
    public ConfigurationSection getConfigSec(String strKey) { return this.getConfig().getConfigurationSection(strKey); }

    /* setters */
    
    public boolean setListen(String strName, Listener objListen)        { this.getServer().getPluginManager().registerEvents(objListen, this); return true; }
    public boolean setExecut(String strName, CommandExecutor objExecut) { this.getCommand(strName).setExecutor(objExecut); return true; }
    public boolean setPermit(String strName, Permission objPermit)      { this.getServer().getPluginManager().addPermission(objPermit); return true; }

    public boolean setConfigObj(String strKey, Object objVal)       { this.getConfig().set(strKey, objVal); return true; }
    public boolean setConfigBit(String strKey, Boolean bitVal)      { this.getConfig().set(strKey, bitVal); return true; }
    public boolean setConfigInt(String strKey, Integer intVal)      { this.getConfig().set(strKey, intVal); return true; }
    public boolean setConfigNum(String strKey, Double numVal)       { this.getConfig().set(strKey, numVal); return true; }
    public boolean setConfigStr(String strKey, String strVal)       { this.getConfig().set(strKey, strVal); return true; }
    public boolean setConfigArr(String strKey, List<String> arrVal) { this.getConfig().set(strKey, arrVal); return true; }
    
    /* vetters */
    
    public boolean vetConfig(String strPath) { return this.getConfig().contains(strPath); }
    public boolean vetPlayer(String strName) { return this.getServer().matchPlayer(strName).isEmpty() == false; }
    
    /* actions */
    
    public void doLogO(String strFormat, Object ... objArgs) {

        System.out.printf(String.format("%s:%s\n", this.getConfigStr("nameof_logo"), strFormat), objArgs);

    }
    
    public void doLogO(CommandSender objSender, String strFormat, Object ... objArgs) {

        System.out.printf(String.format("%s:%s\n", this.getConfigStr("nameof_logo"), strFormat), objArgs);

        objSender.sendMessage(String.format(strFormat, objArgs));

    }
    
    public boolean doInit() {
        
        if (Execut.doInit()) {
            this.doLogO("init executs is done;");
        } else {
            this.doLogO("init executs is failed;");
        }
        
        if (Listen.doInit()) {
            this.doLogO("init listens is done;");
        } else {
            this.doLogO("init listens is failed;");
        }
        
        if (Permit.doInit()) {
            this.doLogO("init permits is done;");
        } else {
            this.doLogO("init permits is failed;");
        }
        
        if (Config.doInit()) {
            this.doLogO("init configs is done;");
        } else {
            this.doLogO("init configs is failed;");
        }
        
        if (User.doInit()) {
            this.doLogO("init users is done;");
        } else {
            this.doLogO("init users is failed;");
        }
        
        if (Item.doInit()) {
            this.doLogO("init items is done;");
        } else {
            this.doLogO("init items is failed;");
        }
        
        if (Menu.doInit()) {
            this.doLogO("init menus is done;");
        } else {
            this.doLogO("init menus is failed;");
        }

        return true;

    }
   
    public boolean doQuit() {

        if (Menu.doQuit()) {
            this.doLogO("quit menus is done;");
        } else {
            this.doLogO("quit menus is failed;");
        }
        
        if (Item.doQuit()) {
            this.doLogO("quit items is done;");
        } else {
            this.doLogO("quit items is failed;");
        }

        if (User.doQuit()) {
            this.doLogO("quit users is done;");
        } else {
            this.doLogO("quit users is failed;");
        }

        if (Config.doQuit()) {
            this.doLogO("quit configs is done;");
        } else {
            this.doLogO("quit configs is failed;");
        }

        if (Permit.doQuit()) {
            this.doLogO("quit permits is done;");
        } else {
            this.doLogO("quit permits is failed;");
        }

        if (Listen.doQuit()) {
            this.doLogO("quit listens is done;");
        } else {
            this.doLogO("quit listens is failed;");
        }

        if (Execut.doQuit()) {
            this.doLogO("quit executs is done;");
        } else {
            this.doLogO("quit executs is failed;");
        }

        return true;
    }

    /* handles */
    
    @Override
    public void onEnable() {

        objInstance = this;

        this.doInit();

    }

    @Override
    public void onDisable() {
        
        this.doQuit();

        objInstance = null;

    }
}
/* endfile */