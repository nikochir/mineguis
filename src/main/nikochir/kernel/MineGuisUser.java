/* package */
package nikochir.kernel;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisUnit;
import nikochir.kernel.MineGuisItem;
import nikochir.kernel.MineGuisMenu;
import nikochir.kernel.MineGuisBook;
/* bukkit */
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.entity.Player;
/* typedef */
/* MineGuisUser class
 * > Description:
 * -> stores permission data;
 * -> keeps track on the current and the last menus;
*/
public class MineGuisUser extends MineGuisUnit {
    /* members */
    private PermissionAttachment objPermitAttachment;
    private MineGuisMenu objMenuCurr;
    private MineGuisMenu objMenuLast;
    /* codetor */
    public MineGuisUser(Player objPlayer) {
        super(objPlayer.getUniqueId().toString());
        this.objPermitAttachment = new PermissionAttachment(MineGuis.get(), objPlayer);
        this.objMenuCurr = null;
        this.objMenuLast = null;
    }
    //public MineGuisUser(String strPlayer) { this(MineGuis.get().getPlayer(strPlayer)); }
    /* getters */
    public String getName()   { return getPlayer().getName(); }
    public Player getPlayer() { return (Player) this.objPermitAttachment.getPermissible(); }
    public MineGuisMenu getMenu() { return this.objMenuCurr; }
    /* setters */
    public boolean setPermit(String strPermit, boolean bitPermit) {
        this.objPermitAttachment.setPermission(strPermit, bitPermit);
        return true;
    }
    public boolean setMenu(MineGuisMenu objMenu) {
        this.objMenuLast = this.objMenuCurr;
        this.objMenuCurr = objMenu;
        return true;
    }
    /* vetters */
    public boolean vetPermit(String strPermit)   { return this.objPermitAttachment.getPermissions().get(strPermit); }
    public boolean vetMenu()                     { return this.getMenu() != null; }
    public boolean vetMenu(MineGuisMenu objMenu) { return this.getMenu() == objMenu; }
    /* actions */
    public boolean doMenuShow(MineGuisMenu objMenu) {
        if (objMenu == null) {
            MineGuis.get().doLogO("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu(objMenu)) {
            MineGuis.get().doLogO("objMenu is current! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu()) {
            if (this.getMenu().doHide(this.getPlayer()) == false) {
                MineGuis.get().doLogO("failed menu hide! doMenuShow(objMenu);");
                return false;
            }
        }
        if (objMenu.doShow(this.getPlayer()) == false) {
            MineGuis.get().doLogO("failed menu show! doMenuShow(objMenu);");
            return false;
        }
        return true;
    }
    public boolean doMenuHide(MineGuisMenu objMenu) {
        if (objMenu == null) {
            MineGuis.get().doLogO("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu(objMenu) == false) {
            MineGuis.get().doLogO("objMenu is not current! doMenuShow(objMenu);");
            return false;
        }
        if (objMenu.doHide(this.getPlayer()) == false) {
            MineGuis.get().doLogO("failed menu hide! doMenuHide(objMenu);");
            return false;
        }
        return true;
    }
    public boolean doMenuBack() {
        if (this.objMenuLast == null) {
            MineGuis.get().doLogO("the last menu is null! doMenuBack();");
            return false;
        }
        if (doMenuShow(this.objMenuLast) == false) {
            MineGuis.get().doLogO("failed to show the last menu! doMenuBack();");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */