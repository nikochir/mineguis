/* package */
package nikochir.kernel;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisSign;
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
    private final MineGuisSign objSign;
    /* codetor */
    public MineGuisUnit(MineGuisSign objSign) { this.objSign = objSign; }
    public MineGuisUnit(String strSign)       { this(MineGuisSign.get(strSign)); }
    public MineGuisUnit(MineGuisUnit objUnit) { this(objUnit.getSign()); }
    /* getters */
    public MineGuisSign getSign() { return objSign; }
    /* setters */
    /* vetters */
    public boolean vet(MineGuisUnit objUnit)     { return this.getSign().equals(objUnit.getSign()); }
    public boolean vetSign(MineGuisSign objSign) { return this.getSign().equals(objSign); }
    public boolean vetSign(String strSign)       { return this.getSign().vetData(strSign); }
    public boolean equals(MineGuisUnit objUnit)  { return this.getSign().equals(objUnit.getSign()); }
    public boolean equals(MineGuisSign objSign)  { return this.getSign().equals(objSign); }
    public boolean equals(String strSign)        { return this.getSign().vetData(strSign); }
    /* actions */
    /* handles */
}
/* endfile */