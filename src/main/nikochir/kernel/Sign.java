/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
/* javkit */
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
/* bukkit */
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
/* typedef */
/* Sign class
 * > Description:
 * -> unique identificator for any mineguis kernel;
*/
public class Sign {
    /* members */
    static private HashMap<String, Sign> tab;
    private final String strData;
    /* codetor */
    private Sign(String strData) { this.strData = strData; tab.put(strData, this); }
    /* getters */
    static public Sign getSign(String strData) {
        Sign objSign = null;
        if (vetSign(strData)) { objSign = tab.get(strData); }
        else                  { objSign = new Sign(strData); }
        return objSign;
    }
    static public Sign getSign(Object ... arrObj) {
        return getSign(doData(arrObj));
    }
    public String getData()  { return this.strData; }
    public Integer getHash() { return this.strData.hashCode(); }
    public int hashCode()    { return this.strData.hashCode(); }
    /* setters */
    /* vetters */
    static public boolean vetSign(Sign objSign)      { return tab.containsKey(doData(objSign.getData())); }
    static public boolean vetSign(String strData)    { return tab.containsKey(strData); }
    static public boolean vetSign(Object ... arrObj) { return tab.containsKey(doData(arrObj)); }
    public boolean vetData(String strData)    { return this.getData().equals(strData); }
    public boolean vetData(Object ... arrObj) { return this.getData().equals(doData(arrObj)); }
    public boolean vetHash(int numHash)       { return this.getHash().equals(numHash); }
    public boolean equals(Sign objSign)       { return this.getData().equals(objSign.getData()); }
    public boolean equals(String strData)     { return this.getData().equals(strData); }
    public boolean equals(Object ... arrObj)  { return this.getData().equals(doData(arrObj)); }
    public boolean equals(int numHash)        { return this.getHash().equals(numHash); }
    public boolean vet(Sign objSign)          { return this.getData().equals(objSign.getData()); }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("init is already done!");
            return false;
        }
        tab = new HashMap<String, Sign>();
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
    static public String doData(Object ... arrObj) {
        String strData = "|";
        for (Object itrObj : arrObj) { strData += itrObj.toString() + "|"; }
        return strData;
    }
    public String toString() { return this.getData(); }
    /* handles */
}
/* endfile */