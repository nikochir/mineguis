/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
/* javkit */
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
/* bukkit */
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
/* nkyori */
import net.kyori.adventure.text.Component;
/* typedef */
/* Item class
 * > Description:
 * -> represents an item in the menu;
 * -> stores command execution data;
 * -> the sign is the item name;
 * --> ItemStack.getI18NDisplayName() is invalid;
 * --> use ItemMeta.getDisplayName() instead - this is what we see;
 * -> exec is the actual command to execute;
 * --> sign does not include Executive command;
*/
public class Item extends Unit {
    /* members */
    static private HashMap<Sign, Item> tab;
    private final ItemStack objStack;
    private String strExec;
    /* codetor */
    protected Item(String strName, String strIcon, List<String> arrStrLore, String strExec) {
        super(strName, strIcon, arrStrLore);
        this.objStack = new ItemStack(Material.matchMaterial(strIcon));
        this.objStack.setLore(arrStrLore);
        this.objStack.getItemMeta().setDisplayName(strName);
        this.strExec = strExec;
        tab.put(this.getSign(), this);
    }
    protected Item(String strName, String strIcon, String[] arrStrLore, String strExec) {
        this(strName, strIcon, Arrays.asList(arrStrLore), strExec);
    }
    protected Item(String strName, String strIcon, String strLore, String strExec) {
        this(strName, strIcon, new String[] { strLore }, strExec);
    }
    /** not_exec **/
    protected Item(String strName, String strIcon, List<String> arrStrLore) {
        this(strName, strIcon, arrStrLore, "");
    }
    protected Item(String strName, String strIcon, String[] arrStrLore) {
        this(strName, strIcon, arrStrLore, "");
    }
    protected Item(String strName, String strIcon, String strLore) {
        this(strName, strIcon, strLore, "");
    }
    /** not_lore **/
    protected Item(String strName, String strIcon) {
        this(strName, strIcon, "");
    }
    protected Item(String strName) {
        this(strName, "BOOK", "");
    }
    /* getters */
    static public Item getItem(String strName, String strIcon, List<String> arrStrLore, String strExec) {
        Sign objSign = Sign.getSign(strName, strIcon, arrStrLore);
        Item objItem = null;
        if (vetItem(objSign)) {
            objItem = tab.get(objSign);
            if (objItem.vetExec(strExec) == false) {
                Main.get().doLogO("Item Exec is changed! [%s] -> [%s];", objItem.getExec(), strExec);
                objItem.strExec = strExec;
            }
        }
        else { objItem = new Item(strName, strIcon, arrStrLore, strExec); }
        return objItem;
    }
    static public Item getItem(String strName, String strIcon, String[] arrStrLore, String strExec) {
        return getItem(strName, strIcon, Arrays.asList(arrStrLore), strExec);
    }
    static public Item getItem(String strName, String strIcon, String strLore, String strExec) {
        return getItem(strName, strIcon, new String[] { strLore }, strExec);
    }
    /** execoff **/
    static public Item getItem(String strName, String strIcon, List<String> arrStrLore) {
        return getItem(strName, strIcon, arrStrLore, "");
    }
    static public Item getItem(String strName, String strIcon, String[] arrStrLore) {
        return getItem(strName, strIcon, arrStrLore, "");
    }
    static public Item getItem(String strName, String strIcon, String strLore) {
        return getItem(strName, strIcon, strLore, "");
    }
    static public Item getItem(String strName, String strIcon) {
        return getItem(strName, strIcon, "", "");
    }
    static public Item getItem(String strName) {
        return getItem(strName, "BOOK", "", "");
    }
    static public Item getItem(ItemStack objStack, String strExec) {
        return getItem(
            objStack.getItemMeta().getDisplayName(),
            objStack.getType().toString(),
            objStack.getLore(),
            strExec
        );
    }
    static public Item getItem(ItemStack objStack) {
        return getItem(objStack, "");
    }
    public String getExec()       { return this.strExec; }
    public String getName()       { return this.objStack.getItemMeta().getDisplayName(); }
    public List<String> getLore() { return this.objStack.getLore(); }
    public String getIcon()       { return this.objStack.getType().toString(); }
    public ItemStack getStack()   { return this.objStack; }
    public ItemMeta getMeta()     { return this.objStack.getItemMeta(); }
    /* setters */
    /* vetters */
    static public boolean vetItem(Item objItem)      { return tab.containsKey(objItem.getSign()); }
    static public boolean vetItem(Sign objSign)      { return tab.containsKey(objSign); }
    static public boolean vetItem(Object ... arrObj) { return tab.containsKey(arrObj); }
    public boolean vetName(String strName)           { return this.getName().equals(strName); }
    public boolean vetLore(List<String> arrStrLore)  { return this.getLore().equals(arrStrLore); }
    public boolean vetLore(String[] arrStrLore)      { return this.vetLore(Arrays.asList(arrStrLore)); }
    public boolean vetLore(String strLore)           { return this.vetLore(new String[]{ strLore }); }
    public boolean vetIcon(Material valIcon)         { return this.getIcon().equals(valIcon.toString()); }
    public boolean vetIcon(String strIcon)           { return this.getIcon().equals(strIcon); }
    public boolean vetExec(String strExec)           { return this.getExec().equals(strExec); }
    public boolean vetStack(ItemStack objStack)      { return this.getStack().equals(objStack); }
    public boolean vetMeta(ItemMeta objMeta)         { return this.getMeta().equals(objMeta); }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO(
                "init is already done!"
            );
            return false;
        }
        tab = new HashMap<Sign, Item>();
//        if (Main.get().vetConfig("listof_item")) {
//            ConfigurationSection objSectionListOf = Main.get().getConfigSec("listof_item");
//            Set<String> setKeys = objSectionListOf.getKeys(false);
//            if (setKeys.size() > 0) {
//                /* Main.get().doLogO(
//                    "count: %d;",
//                    setKeys.size()
//                ); */
//            } else {
//                Main.get().doLogO(
//                    "the listof config section is empty!"
//                );
//                return false;
//            }
//            for (String itrStrKey : setKeys) {
//                ConfigurationSection itrObjSectionItem = objSectionListOf.getConfigurationSection(itrStrKey);
//                String itrStrSign = null;
//                String itrStrLore = null; List<String> itrArrLore = null;
//                String itrStrIcon = null;
//                String itrStrExec = null;
//                if (itrObjSectionItem.contains("info")) {
//                    ConfigurationSection itrObjSectionInfo = itrObjSectionItem.getConfigurationSection("info");
//                    itrStrSign = itrObjSectionInfo.getString("sign");
//                    itrStrIcon = itrObjSectionInfo.getString("icon");
//                    if (itrObjSectionInfo.isString("lore")) {
//                        itrStrLore = itrObjSectionInfo.getString("lore");
//                    } else if (itrObjSectionInfo.isList("lore")) {
//                        itrArrLore = itrObjSectionInfo.getStringList("lore");
//                    } else {
//                        Main.get().doLogO("invalid item lore type!");
//                        return false;
//                    }
//                } else {
//                    Main.get().doLogO(
//                        "config section \"%s\" does not have config section \"info\"!",
//                        itrStrKey
//                    );
//                    return false;
//                }
//                if (itrObjSectionItem.contains("data")) {
//                    ConfigurationSection itrObjSectionData = itrObjSectionItem.getConfigurationSection("data");
//                    itrStrExec = itrObjSectionData.getString("exec");
//                } else {
//                    Main.get().doLogO(
//                        "config section \"%s\" does not have config section \"data\"!",
//                        itrStrKey
//                    );
//                    return false;
//                }
//                Item itrObjItem = null;
//                if (itrStrLore == null && itrArrLore == null) {
//                    itrObjItem = Item.getItem(itrStrSign, itrStrIcon, itrStrExec);
//                } else if (itrStrLore != null) {
//                    itrObjItem = Item.getItem(itrStrSign, itrStrIcon, itrStrLore, itrStrExec);
//                } else if (itrArrLore != null) {
//                    itrObjItem = Item.getItem(itrStrSign, itrStrIcon, itrArrLore, itrStrExec);
//                }
//                if (itrObjItem != null) {
//                    /* Main.get().doLogO(
//                        "the item has been added: %s;",
//                        itrStrKey
//                    ); */
//                } else {
//                    Main.get().doLogO(
//                        "failed to add the item: %s;",
//                        itrStrKey
//                    );
//                    return false;
//                }
//            }
//        } else {
//            Main.get().doLogO(
//                "the config section listof_item is not found!"
//            );
//            return false;
//        }
        return true;
    }
    static public boolean doQuit() {
        if (tab == null) {
            Main.get().doLogO("init is not done!");
            return false;
        }
        tab.clear();
        tab = null;
        return true;
    }
    public boolean doExec(Player objPlayer) {
        if (objPlayer == null) {
            Main.get().doLogO("null argument is passed! doExecute(objPlayer);");
            return false;
        }
        if (objPlayer.performCommand(this.getExec()) == false) {
            Main.get().doLogO("failed to execute the command! doExecute(objPlayer);");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */