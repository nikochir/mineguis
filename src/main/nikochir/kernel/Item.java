/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
/** javkit **/
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
/* typedef */
/* Item class
 * > Description:
 * -> represents an item in the menu;
 * -> stores command execution data;
 * -> the sign is the item name;
 * --> ItemStack.getI18NDisplayName() is invalid;
 * --> use ItemMeta.getDisplayName() instead - this is what we see;
 * -> Exec is the actual command to execute;
*/
public class Item extends Unit {
    /* members */
    static private HashMap<String, Item> tab;
    private final ItemStack objStack;
    private final String strExec;
    /* codetor */
    protected Item(String strName, String strIcon, List<String> arrStrLore, String strExec) {
        super(strName);
        this.strExec = strExec;
        Material valMaterial = Material.matchMaterial(strIcon);
        if (valMaterial == null) {
            Main.get().doLogO("no material \"%s\" found!", strIcon);
            valMaterial = Material.DEBUG_STICK;
        }
        this.objStack = new ItemStack(valMaterial);
        ItemMeta objMeta = this.objStack.getItemMeta();
        objMeta.setDisplayName(this.getSign());
        objMeta.setLore(arrStrLore);
        this.objStack.setItemMeta(objMeta);
    }
    protected Item(String strName, String strIcon, String[] arrStrLore, String strExec) {
        this(strName, strIcon, Arrays.asList(arrStrLore), strExec);
    }
    protected Item(String strName, String strIcon, String strLore, String strExec) {
        this(strName, strIcon, new String[]{ strLore }, strExec);
    }
    protected Item(String strName, String strIcon, String strExec) {
        this(strName, strIcon, "", strExec);
    }
    protected Item(String strName, String strExec) {
        this(strName, "BOOK", strExec);
    }
    protected Item(String strExec) {
        this(strExec.split(" ")[0], "BOOK", strExec.split(" "), strExec);
    }
    /* getters */
    public static HashMap<String, Item> getItemTab() { return tab; }
    public static Item getItem(String strSign) {
        strSign = strSign.replace(" ", "_");
        if (vetItem(strSign)) { return tab.get(strSign); }
        else { Main.get().doLogO("the item \"%s\" is not found!", strSign); return null; }
    }
    public static Item getItem(ItemStack objStack) {
        return getItem(objStack.getItemMeta().getDisplayName());
    }
    public String getExec()       { return this.strExec; }
    public String getName()       { return this.objStack.getItemMeta().getDisplayName(); }
    public String getIcon()       { return this.objStack.getType().toString(); }
    public List<String> getLore() { return this.objStack.getItemMeta().getLore(); }
    public ItemStack getStack()   { return this.objStack; }
    /* setters */
    static public boolean setItem(String strName, String strIcon, List<String> arrStrLore, String strExec) {
        if (vetItem(strName)) { Main.get().doLogO("failed to set the item \"%s\";", strName); return false; }
        else { tab.put(strName.replace(" ", "_"), new Item(strName, strIcon, arrStrLore, strExec)); return true; }
    }
    static public boolean setItem(String strName, String strIcon, String[] arrStrLore, String strExec) {
        return setItem(strName, strIcon, Arrays.asList(arrStrLore), strExec);
    }
    static public boolean setItem(String strName, String strIcon, String strLore, String strExec) {
        return setItem(strName, strIcon, Arrays.asList(new String[]{ strLore }), strExec);
    }
    static public boolean setItem(String strName, String strIcon, String strExec) {
        return setItem(strName, strIcon, "", strExec);
    }
    static public boolean setItem(String strName, String strExec) {
        return setItem(strName, "BOOK", "", strExec);
    }
    static public boolean setItem(String strExec) {
        return setItem(strExec.split(" ")[0], "BOOK", strExec.split(" "), strExec);
    }
    /* vetters */
    static public boolean vetItem(String strSign)     { return tab.containsKey(strSign.replace(" ", "_")); }
    static public boolean vetItem(Item objItem)       { return tab.containsKey(objItem.getSign()); }
    static public boolean vetItem(ItemMeta objMeta)   { return tab.containsKey(objMeta.getDisplayName()); }
    static public boolean vetItem(ItemStack objStack) { return tab.containsKey(objStack.getItemMeta().getDisplayName()); }
    public boolean vetExec(String strExec)        { return this.getExec().equals(strExec); }
    public boolean vetStack(ItemStack objStack)   { return this.getStack().equals(objStack); }
    /* actions */
    static private boolean doCreate() {
        if (false
            || Item.setItem(
                Main.get().getConfigStr("nameof_main"),
                "KNOWLEDGE_BOOK",
                "execute the main gui command", Main.get().getConfigStr("nameof_main")
            ) == false
            || Item.setItem("back", "COMPASS", "switch to the last menu", "mguiback") == false
            || Item.setItem("void", "BLACK_STAINED_GLASS_PANE", "____", "mguivoid") == false
        ) { Main.get().doLogO("failed to create some default item!"); return false; }
        return true;
    }
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("the init has already been done!");
            return false;
        }
        tab = new HashMap<String, Item>();
        if (Item.doCreate() == false) { Main.get().doLogO("failed to create default items!"); return false; }
        Main.get().doLogO("========<listof_item>========");
        if (Main.get().vetConfig("listof_item")) {
            ConfigurationSection objSectionListOf = Main.get().getConfigSec("listof_item");
            Set<String> setKeys = objSectionListOf.getKeys(false);
            if (setKeys.size() > 0) {
                Main.get().doLogO(
                    "count: %d;",
                    setKeys.size()
                );
            } else {
                Main.get().doLogO(
                    "the listof config section is empty!"
                );
                return false;
            }
            for (String itrStrKey : setKeys) {
                ConfigurationSection itrObjSectionItem = objSectionListOf.getConfigurationSection(itrStrKey);
                String itrStrSign = null;
                String itrStrIcon = null;
                String itrStrLore = null;
                List<String> itrArrStrLore = null;
                String itrStrExec = null;
                if (itrObjSectionItem.contains("info")) {
                    ConfigurationSection itrObjSectionInfo = itrObjSectionItem.getConfigurationSection("info");
                    itrStrSign = itrObjSectionInfo.getString("sign");
                    itrStrIcon = itrObjSectionInfo.getString("icon");
                    if (itrObjSectionInfo.isString("lore")) { itrStrLore = itrObjSectionInfo.getString("lore"); }
                    else { itrArrStrLore = itrObjSectionInfo.getStringList("lore"); }
                    itrStrLore = itrObjSectionInfo.getString("lore");
                } else {
                    Main.get().doLogO(
                        "config section \"%s\" does not have config section \"info\"!",
                        itrStrSign
                    );
                    continue;
                    // return false;
                }
                if (itrObjSectionItem.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionItem.getConfigurationSection("data");
                    itrStrExec = itrObjSectionData.getString("exec");
                } else {
                    Main.get().doLogO(
                        "config section \"%s\" does not have config section \"data\"!",
                        itrStrSign
                    );
                    continue;
                    // return false;
                }
                boolean bitResult = false;
                if (itrStrLore != null) { bitResult = Item.setItem(itrStrSign, itrStrIcon, itrStrLore, itrStrExec); }
                else { bitResult = Item.setItem(itrStrSign, itrStrIcon, itrArrStrLore, itrStrExec); }
                if (bitResult) {
                    /* Main.get().doLogO(
                        "the item \"%s\" has been added;",
                        itrStrKey
                    ); */
                } else {
                    Main.get().doLogO(
                        "failed to set the item \"%s\";",
                        itrStrKey
                    );
                    continue;
                    // return false;
                }
                Item objItem = Item.getItem(itrStrSign);
            }
        } else {
            Main.get().doLogO("the config section listof_item is not found!");
            return false;
        }
        return true;
    }
    static public boolean doQuit() {
        if (tab == null) {
            Main.get().doLogO("the quit has already been done!");
            return false;
        }
        tab.clear();
        tab = null;
        return true;
    }
    public Boolean doExec(Player objPlayer) {
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