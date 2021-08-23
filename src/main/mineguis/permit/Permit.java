/* package */

package main.mineguis.permit;

/* include */

import main.mineguis.Main;
import main.mineguis.permit.*;

/** bukkit **/

import org.bukkit.permissions.Permission;

/* typedef */

/* Permit class
 * > Description:
 * -> just a placeholder for the beginning;
*/
public class Permit extends Permission {

    /* codetor */

    public Permit() { super("mineguis.*"); }

    /* actions */

    public static boolean doInit() {
    
        /*
        Main.get().setPermit("user", new PermitUser());
        Main.get().setPermit("oper", new PermitOper());
        Main.get().setPermit("over", new PermitOver());
        */

        return true;
    
    }

    public static boolean doQuit() {
        
        return true;

    }

}
/* endfile */