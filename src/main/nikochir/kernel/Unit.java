/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
import nikochir.kernel.Sign;
/* javkit */
import java.util.HashMap;
/* typedef */
/* Unit class
 * > Description:
 * -> stores unique signature for identification;
 * -> all derrived classes can have a different signature;
 * -> the string signature allows to easily identify any object;
 * -> any sign with spaces will be sign with underscores;
 * --> underscore allows us to have a sign as a whole word;
 * --> it is required for command line arguments where space is separator;
*/
public class Unit {
    /* members */
    static private HashMap<Sign, Unit> tab;
    private final Sign objSign;
    /* codetor */
    private Unit(Sign objSign) {
        this.objSign = objSign;
        tab.put(this.getSign(), this);
    }
    protected Unit(Object ... arrObj) {
        this(Sign.getSign(arrObj));
    }
    /* getters */
    static public Unit getUnit(Sign objSign) {
        Unit objUnit = null;
        if (vetUnit(objSign)) { objUnit = tab.get(objSign); }
        else                  { objUnit = new Unit(objSign); }
        return objUnit;
    }
    static public Unit getUnit(Object ... arrObj) { return getUnit(Sign.getSign(arrObj)); }
    public Sign getSign() { return objSign; }
    /* setters */
    /* vetters */
    static public boolean vetUnit(Unit objUnit)      { return tab.containsKey(objUnit.getSign()); }
    static public boolean vetUnit(Sign objSign)      { return tab.containsKey(objSign); }
    static public boolean vetUnit(String strSign)    { return tab.containsKey(Sign.getSign(strSign)); }
    static public boolean vetUnit(Object ... arrObj) { return tab.containsKey(Sign.getSign(arrObj)); }
    public boolean vetSign(Sign objSign)      { return this.getSign().equals(objSign); }
    public boolean vetSign(String strSign)    { return this.getSign().equals(strSign); }
    public boolean vetSign(Object ... arrObj) { return this.getSign().equals(arrObj); }
    public boolean equals(Unit objUnit)       { return this.getSign().equals(objUnit.getSign()); }
    public boolean equals(Sign objSign)       { return this.getSign().equals(objSign); }
    public boolean equals(String strSign)     { return this.getSign().equals(strSign); }
    public boolean equals(Object ... arrObj)  { return this.getSign().equals(arrObj); }
    public boolean vet(Unit objUnit)          { return this.getSign().equals(objUnit.getSign()); }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("init is already done!");
            return false;
        }
        tab = new HashMap<Sign, Unit>();
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
    /* handles */
}
/* endfile */