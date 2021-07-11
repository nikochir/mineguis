/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
import nikochir.kernel.Book;
/** javkit **/
import java.util.Set;
import java.util.HashMap;
import java.util.Collection;
/** bukkit **/
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.entity.Player;
import org.bukkit.entity.HumanEntity;
import org.bukkit.Server;
/* typedef */
/* User class
 * > Description:
 * -> stores permission data;
 * -> keeps track on the current and the last menus;
*/
public class User extends Unit {
    /* members */
    static private HashMap<String, User> tab;
    private PermissionAttachment objPermitAttachment;
    private Menu objMenuCurr;
    private Menu objMenuLast;
    /* codetor */
    protected User(Player objPlayer) {
        super(objPlayer.getUniqueId().toString());
        this.objPermitAttachment = new PermissionAttachment(Main.get(), objPlayer);
        this.objMenuCurr = null;
        this.objMenuLast = null;
    }
    //public User(String strPlayer) { this(Main.get().getPlayer(strPlayer)); }
    /* getters */
    public static HashMap<String, User> getUserTab() { return tab; }
    static public User getUser(String strSign) {
        if (vetUser(strSign)) { return tab.get(strSign); }
        else { Main.get().doLogO("the user \"%s\" is not found!", strSign); return null; }
    }
    static public User getUser(Player objPlayer) {
        return getUser(objPlayer.getUniqueId().toString());
    }
    static public User getUser(HumanEntity objEntity) {
        if (objEntity instanceof Player) { return getUser((Player) objEntity); }
        else { Main.get().doLogO("the human entity is not the player!"); return null; }
    }
    public String getName()   { return getPlayer().getName(); }
    public Player getPlayer() { return (Player) this.objPermitAttachment.getPermissible(); }
    public Menu getMenu() { return this.objMenuCurr; }
    /* setters */
    static public boolean setUser(Player objPlayer) {
        if (vetUser(objPlayer)) { Main.get().doLogO("the user has already been created!"); return false; }
        else { tab.put(objPlayer.getUniqueId().toString(), new User(objPlayer)); return true; }
    }
    static public boolean setUser(HumanEntity objEntity) {
        if (objEntity instanceof Player) { return setUser((Player) objEntity); }
        else { return false; }
    }
    public Boolean setPermit(String strPermit, Boolean bitPermit) {
        this.objPermitAttachment.setPermission(strPermit, bitPermit);
        return true;
    }
    public Boolean setMenu(Menu objMenu) {
        this.objMenuLast = this.objMenuCurr;
        this.objMenuCurr = objMenu;
        return true;
    }
    /* vetters */
    static public boolean vetUser(String strSign)        { return tab.containsKey(strSign); }
    static public boolean vetUser(Player objPlayer)      { return tab.containsKey(objPlayer.getUniqueId().toString()); }
    static public boolean vetUser(HumanEntity objEntity) { return tab.containsKey(objEntity.getUniqueId().toString()); }
    public boolean vetPermit(String strPermit)   { return this.objPermitAttachment.getPermissions().get(strPermit); }
    public boolean vetMenu()                     { return this.getMenu() != null; }
    public boolean vetMenu(Menu objMenu) { return this.getMenu() == objMenu; }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("the init has already been done!");
            return false;
        }
        tab = new HashMap<String, User>();
        Main.get().doLogO("========<listof_user>========");
        Collection<? extends Player> arrPlayers = Main.get().getServer().getOnlinePlayers();
        for (Player objPlayer : arrPlayers) {
            if (User.setUser(objPlayer) == false) {
                Main.get().doLogO("failed to initialise the \"%s\" user!", objPlayer.getName());
                return false;
            }
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
    public boolean doMenuShow(Menu objMenu) {
        if (objMenu == null) {
            Main.get().doLogO("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu(objMenu)) {
            Main.get().doLogO("objMenu is current! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu()) {
            if (this.getMenu().doHide(this.getPlayer()) == false) {
                Main.get().doLogO("failed menu hide! doMenuShow(objMenu);");
                return false;
            }
        }
        if (objMenu.doShow(this.getPlayer()) == false) {
            Main.get().doLogO("failed menu show! doMenuShow(objMenu);");
            return false;
        }
        return true;
    }
    public boolean doMenuHide(Menu objMenu) {
        if (objMenu == null) {
            Main.get().doLogO("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu(objMenu) == false) {
            Main.get().doLogO("objMenu is not current! doMenuShow(objMenu);");
            return false;
        }
        if (objMenu.doHide(this.getPlayer()) == false) {
            Main.get().doLogO("failed menu hide! doMenuHide(objMenu);");
            return false;
        }
        return true;
    }
    public boolean doMenuBack() {
        if (this.objMenuLast == null) {
            Main.get().doLogO("the last menu is null! doMenuBack();");
            return false;
        }
        if (doMenuShow(this.objMenuLast) == false) {
            Main.get().doLogO("failed to show the last menu! doMenuBack();");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */