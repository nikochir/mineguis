/* package */
package nikochir.kernel;
/* include */
import nikochir.MineGuis;
/* javkit */
import java.util.HashMap;
/* typedef */
/* MineGuisSign class
 * > Description:
 * -> unique identificator for any mineguis kernel;
*/
public class MineGuisSign {
    /* members */
    private final String strData;
    private static HashMap<String, MineGuisSign> tabSigns = new HashMap<String, MineGuisSign>();
    /* codetor */
    private MineGuisSign(String strData)       { this.strData = strData.replace(" ", "_"); }
    private MineGuisSign(MineGuisSign objSign) { this(objSign.getData()); }
    /* getters */
    public static MineGuisSign get(String strData) {
        if (vetSign(strData) == false) { tabSigns.put(strData, new MineGuisSign(strData)); }
        return tabSigns.get(strData);
    }
    public String getData()  { return this.strData; }
    public Integer getHash() { return this.strData.hashCode(); }
    public int hashCode()    { return this.strData.hashCode(); }
    /* setters */
    /* vetters */
    public static boolean vetSign(String strData)       { return tabSigns.containsKey(strData); }
    public static boolean vetSign(MineGuisSign objSign) { return tabSigns.containsValue(objSign); }
    public boolean vet(MineGuisSign objSign) { return this.getData().equals(objSign.getData()); }
    public boolean vetData(String strData)   { return this.getData().equals(strData.replace(" ", "_")); }
    public boolean vetHash(int numHash)      { return this.getHash().equals(numHash); }
    public boolean equals(MineGuisSign objSign) { return this.getData().equals(objSign.getData()); }
    public boolean equals(String strData)       { return this.getData().equals(strData.replace(" ", "_")); }
    public boolean equals(int numHash)          { return this.getHash().equals(numHash); }
    /* actions */
    public String toString() { return this.getData(); }
    /* handles */
}
/* endfile */