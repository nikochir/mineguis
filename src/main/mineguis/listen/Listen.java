/* package */

package main.mineguis.listen;

/* include */

import main.mineguis.Main;
import main.mineguis.listen.*;

/** bukkit **/

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

/* typedef */

/* Listen class
 * > Description:
 * -> ;
*/
public class Listen implements Listener {

    /* actions */

    public static boolean doInit() {
        
        Main.get().setListen("player", new ListenPlayer());
        Main.get().setListen("invent", new ListenInvent());

        return true;
    
    }

    public static boolean doQuit() {
        
        return true;
    
    }

}

/* endfile */