/* package */
package nikochir.kernel;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
import nikochir.kernel.Book;
/* javkit */
import java.util.HashMap;
/* bukkit */
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
/* typedef */
/* User class
 * > Description:
 * -> stores permission data;
 * -> keeps track on the current and the last menus;
*/
public class User extends Unit {
    /* members */
    static private HashMap<Sign, User> tab;
    private PermissionAttachment objPermitAttachment;
    private Menu objMenuCurr;
    private Menu objMenuLast;
    /* codetor */
    protected User(Player objPlayer) {
        super(objPlayer);
        this.objPermitAttachment = new PermissionAttachment(Main.get(), objPlayer);
        this.objMenuCurr = null;
        this.objMenuLast = null;
        tab.put(this.getSign(), this);
    }
    /* getters */
    static public User getUser(Player objPlayer) {
        Sign objSign = Sign.getSign(objPlayer);
        User objUser = null;
        if (vetUser(objSign)) { objUser = tab.get(objSign); }
        else                  { objUser = new User(objPlayer); }
        return objUser;
    }
    static public User getUser(HumanEntity objEntity) {
        if ((objEntity instanceof Player) == false) { return null; }
        else { return getUser((Player) objEntity); }
    }
    public String getName()   { return getPlayer().getName(); }
    public Player getPlayer() { return (Player) this.objPermitAttachment.getPermissible(); }
    public Menu getMenu()     { return this.objMenuCurr; }
    /* setters */
    public boolean setPermit(String strPermit, boolean bitPermit) {
        this.objPermitAttachment.setPermission(strPermit, bitPermit);
        return true;
    }
    public boolean setMenu(Menu objMenu) {
        this.objMenuLast = this.objMenuCurr;
        this.objMenuCurr = objMenu;
        return true;
    }
    /* vetters */
    static public boolean vetUser(User objUser)      { return tab.containsKey(objUser.getSign()); }
    static public boolean vetUser(Sign objSign)      { return tab.containsKey(objSign); }
    static public boolean vetUser(Object ... arrObj) { return tab.containsKey(Sign.getSign(arrObj)); }
    public boolean vetPermit(String strPermit)  { return this.objPermitAttachment.getPermissions().get(strPermit); }
    public boolean vetMenu()                    { return this.getMenu() != null; }
    public boolean vetMenu(Menu objMenu)        { return this.getMenu() == objMenu; }
    /* actions */
    static public boolean doInit() {
        if (tab != null) {
            Main.get().doLogO("init is already done!");
            return false;
        }
        tab = new HashMap<Sign, User>();
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