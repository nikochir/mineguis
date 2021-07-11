/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
/** javkit **/
import java.util.HashMap;
/* typedef */
/* Unit class
 * > Description:
 * -> stores unique signature for identification;
 * -> all derrived classes can have a different signature;
 * -> the string signature allows to easily identify any object;
*/
public class Unit {
    /* members */
    private final String strSign;
    /* codetor */
    public Unit(String strSign) { this.strSign = strSign.replace(" ", "_"); }
    public Unit(Unit objUnit) { this(objUnit.getSign()); }
    /* getters */
    public String getSign() { return strSign; }
    /* setters */
    /* vetters */
    public Boolean vet(Unit objUnit)         { return this.getSign().equals(objUnit.getSign()); }
    public Boolean vetSign(String strSign)   { return this.getSign().equals(strSign.replace(" ", "_")); }
    /* actions */
    /* handles */
}
/* endfile */