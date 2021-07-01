/* package */
package nikochir.unit;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
/* bukkit */
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.entity.Player;
/* typedef */
/* MineGuisUser class
* > Description:
* -> ;
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
    public Boolean setPermit(String strPermit, Boolean bitPermit) {
        this.objPermitAttachment.setPermission(strPermit, bitPermit);
        return true;
    }
    public Boolean setMenu(MineGuisMenu objMenu) {
        this.objMenuLast = this.objMenuCurr;
        this.objMenuCurr = objMenu;
        return true;
    }
    /* vetters */
    public Boolean vetPermit(String strPermit)   { return this.objPermitAttachment.getPermissions().get(strPermit); }
    public Boolean vetMenu()                     { return this.getMenu() != null; }
    public Boolean vetMenu(MineGuisMenu objMenu) { return this.getMenu() == objMenu; }
    /* actions */
    public Boolean doMenuShow(MineGuisMenu objMenu) {
        if (objMenu == null) {
            MineGuis.get().doLog("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu(objMenu)) {
            MineGuis.get().doLog("objMenu is current! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu()) {
            if (this.getMenu().doHide(this.getPlayer()) == false) {
                MineGuis.get().doLog("failed menu hide! doMenuShow(objMenu);");
                return false;
            }
        }
        if (objMenu.doShow(this.getPlayer()) == false) {
            MineGuis.get().doLog("failed menu show! doMenuShow(objMenu);");
            return false;
        }
        return true;
    }
    public Boolean doMenuHide(MineGuisMenu objMenu) {
        if (objMenu == null) {
            MineGuis.get().doLog("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenu(objMenu) == false) {
            MineGuis.get().doLog("objMenu is not current! doMenuShow(objMenu);");
            return false;
        }
        if (objMenu.doHide(this.getPlayer()) == false) {
            MineGuis.get().doLog("failed menu hide! doMenuHide(objMenu);");
            return false;
        }
        return true;
    }
    public Boolean doMenuBack() {
        if (this.objMenuLast == null) {
            MineGuis.get().doLog("the last menu is null! doMenuBack();");
            return false;
        }
        if (doMenuShow(this.objMenuLast) == false) {
            MineGuis.get().doLog("failed to show the last menu! doMenuBack();");
            return false;
        }
        return true;
    }
    /* handles */
}
/* endfile */