/* package */
package src.main.nikochir.mineguis.kernel;
/* include */
import src.main.nikochir.mineguis.Main;
/** javkit **/
import java.util.Set;
import java.util.HashMap;
/** bukkit **/
import org.bukkit.configuration.ConfigurationSection;
/** bungee **/
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
/* typedef */
/* Text class
 * > Description:
 * -> represents an item in the menu;
 * -> stores command execution data;
 * -> the sign is the item name;
 * --> ItemStack.getI18NDisplayName() is invalid;
 * --> use ItemMeta.getDisplayName() instead - this is what we see;
 * -> Exec is the actual command to execute;
*/
public class Text extends Unit {
    /* members */
    static private HashMap<String, Text> tab;
    private final TextComponent objText;
    /* codetor */
    protected Text(String strSign, String[] strDataDefault, String[] strDataHovered, String[] strDataClicked) {
        super(strSign);
        this.objText = new TextComponent(strDataDefault[0]);
        HoverEvent objHoverEvent = null;
        if (strDataHovered[0].equals("")) {
        }
        objText.setHoverEvent(objHoverEvent);
        ClickEvent objClickEvent = null;
        if (strDataClicked[0] == null) {
            Main.get().doLogO("null argument! Text(strType == null)");
        } else if (strDataClicked[0].equals("RUN_COMMAND")) {
            objClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, strDataClicked[1]);
        } else if (strDataClicked[0].equals("SUGGEST_COMMAND")) {
            objClickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, strDataClicked[1]);
        } else if (strDataClicked[0].equals("OPEN_URL")) {
            objClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, strDataClicked[1]);
        } else if (strDataClicked[0].equals("OPEN_FILE")) {
            objClickEvent = new ClickEvent(ClickEvent.Action.OPEN_FILE, strDataClicked[1]);
        } else if (strDataClicked[0].equals("CHANGE_PAGE")) {
            objClickEvent = new ClickEvent(ClickEvent.Action.CHANGE_PAGE, strDataClicked[1]);
        } else if (strDataClicked[0].equals("COPY_TO_CLIPBOARD")) {
            objClickEvent = new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, strDataClicked[1]);
        } else {
            Main.get().doLogO("unknown argument! Text(strType == null)");
        }
        objText.setClickEvent(objClickEvent);
    }
    /* getters */
    public static HashMap<String, Text> getTextTab() { return tab; }
    public static Text getText(String strSign) {
        strSign = strSign.replace(" ", "_");
        if (vetText(strSign)) { return tab.get(strSign); }
        else { Main.get().doLogO("the item \"%s\" is not found!", strSign); return null; }
    }
    public static Text getText(TextComponent objText) {
        return getText(objText.getText());
    }
    /* setters */
    static public boolean setText(String strDefault, String strHover, String strClick) {
        if (vetText(strDefault)) {
            Main.get().doLogO("failed to set the text \"%s\";", strDefault);
            return false;
        } else {
            //Text objText = new Text(strData);
            //tab.put(objText.getSign(), objText);
            return true;
        }
    }
    /* vetters */
    static public boolean vetText(String strSign)     { return tab.containsKey(strSign.replace(" ", "_")); }
    static public boolean vetText(Text objText)       { return tab.containsKey(objText.getSign()); }
    /* actions */
    static private boolean doCreate() {
        /*if (false
            || Text.setText(
                Main.get().getConfigStr("nameof_main"),
                "click_me",
                "RUN_COMMAND",
                Main.get().getConfigStr("nameof_main")
            ) == false
        ) { Main.get().doLogO("failed to create some default text!"); return false; }
        */
        return true;
    }
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("the init has already been done!");
            return false;
        }
        tab = new HashMap<String, Text>();
        if (Text.doCreate() == false) { Main.get().doLogO("failed to create default texts!"); return false; }
        Main.get().doLogO("========<listof_text>========");
        if (Main.get().vetConfig("listof_text")) {
            ConfigurationSection objSectionListOf = Main.get().getConfigSec("listof_text");
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
                ConfigurationSection itrObjSectionText = objSectionListOf.getConfigurationSection(itrStrKey);
                String itrStrSign = null;
                String itrStrText = null;
                String itrStrExec = null;
                if (itrObjSectionText.contains("info")) {
                    ConfigurationSection itrObjSectionInfo = itrObjSectionText.getConfigurationSection("info");
                    itrStrSign = itrObjSectionInfo.getString("sign");
                } else {
                    Main.get().doLogO(
                        "config section \"%s\" does not have config section \"info\"!",
                        itrStrSign
                    );
                    continue;
                    // return false;
                }
                if (itrObjSectionText.contains("data")) {
                    ConfigurationSection itrObjSectionData = itrObjSectionText.getConfigurationSection("data");
                    itrStrText = itrObjSectionData.getString("text");
                    itrStrExec = itrObjSectionData.getString("exec");
                } else {
                    Main.get().doLogO(
                        "config section \"%s\" does not have config section \"data\"!",
                        itrStrSign
                    );
                    continue;
                    // return false;
                }
                if (Text.setText(itrStrSign, itrStrText, itrStrExec)) {
                    /* Main.get().doLogO(
                        "the text \"%s\" has been added;",
                        itrStrKey
                    ); */
                } else {
                    Main.get().doLogO(
                        "failed to set the text \"%s\";",
                        itrStrKey
                    );
                    continue;
                    // return false;
                }
                //Text objText = Text.getText(itrStrSign);
            }
        } else {
            Main.get().doLogO("the config section listof_text is not found!");
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
    /* handles */
}
/* endfile */