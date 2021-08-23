/* package */

package main.mineguis.config;

/* include */

import main.mineguis.Main;
import main.mineguis.config.*;

/* java */

import java.util.Set;

/* typedef */

/* Config class
 * > Description:
 * -> ;
*/
public class Config {
    
    /* actions */

    public static boolean doInit() {
        
        Main.get().getConfig().options().copyDefaults(true);
        Main.get().saveDefaultConfig();

        Main.get().doLogO("========<config_yaml>========");
        
        Set<String> setKeys = Main.get().getConfigKeys();
        Main.get().doLogO( String.format("count: %d;", setKeys.size()) );
        //for (String itrStrKey : setKeys) { this.doLogO(itrStrKey); }

        return true;

    }
    
    public static boolean doQuit() {
        
        return true;

    }

}

/* endfile */