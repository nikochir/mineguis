/* package */
package nikochir.unit;
/* include */
import nikochir.MineGuis;
/* typedef */
/* MineGuisUnit class
 * > Description:
 * -> stores unique signature for identification;
 * -> all derrived classes can have a different signature;
 * -> the string signature allows to easily identify any object;
 * -> any sign with spaces will be sign with underscores;
 * --> underscore allows us to have a sign as a whole word;
 * --> it is required for command line arguments where space is separator;
*/
public class MineGuisUnit {
    /* members */
    private final String strSign;
    /* codetor */
    public MineGuisUnit(String strSign) { this.strSign = strSign.replace(" ", "_"); }
    public MineGuisUnit(MineGuisUnit objUnit) { this(objUnit.getSign()); }
    /* getters */
    public String getSign() { return strSign; }
    /* setters */
    /* vetters */
    public Boolean vet(MineGuisUnit objUnit) { return this.getSign().equals(objUnit.getSign()); }
    public Boolean vetSign(String strSign)   { return this.getSign().equals(strSign); }
    /* actions */
    /* handles */
}
/* endfile */