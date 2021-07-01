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
    public MineGuisMenu getMenuCurr() { return objMenuCurr; }
    public MineGuisMenu getMenuLast() { return objMenuLast; }
    /* setters */
    public Boolean setPermit(String strPermit, Boolean bitPermit) {
        this.objPermitAttachment.setPermission(strPermit, bitPermit);
        return true;
    }
    public Boolean setMenuCurr(MineGuisMenu objMenu) {
        if (this.vetMenuCurr(objMenu) == true) { return false; }
        if (this.setMenuLast(this.getMenuCurr()) == false ) {
            MineGuis.get().doLog("failed to set last menu! setMenuCurr(objMenu);");
            return false;
        }
        this.objMenuCurr = objMenu;
        return true;
    }
    public Boolean setMenuLast(MineGuisMenu objMenu) {
        if (this.vetMenuLast(objMenu)) { return false; }
        if (this.vetMenuCurr(objMenu) && (objMenu != null)) {
            if (this.doMenuHide(objMenu) == false) {
                MineGuis.get().doLog("failed to hide the menu! setMenuLast(objMenu)");
                return false;
            }
        }
        this.objMenuLast = objMenu;
        return true;
    }
    /* vetters */
    public Boolean vetPermit(String strPermit)       { return this.objPermitAttachment.getPermissions().get(strPermit); }
    public Boolean vetMenuCurr()                     { return getMenuCurr() != null; }
    public Boolean vetMenuCurr(MineGuisMenu objMenu) { return getMenuCurr() == objMenu; }
    public Boolean vetMenuLast()                     { return getMenuLast() != null; }
    public Boolean vetMenuLast(MineGuisMenu objMenu) { return getMenuLast() == objMenu; }
    /* actions */
    public Boolean doMenuShow(MineGuisMenu objMenu) {
        if (objMenu == null) {
            MineGuis.get().doLog("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenuCurr(objMenu)) {
            MineGuis.get().doLog("objMenu is current! doMenuShow(objMenu);");
            return false;
        }
        if (objMenu.doShow(this.getPlayer()) == false) {
            MineGuis.get().doLog("failed menu show! doMenuShow(objMenu);");
            return false;
        }
        if (this.setMenuCurr(objMenu) == false) {
            MineGuis.get().doLog("failed to set the current menu! doMenuShow(objMenu);");
            return false;
        }
        return true;
    }
    public Boolean doMenuHide(MineGuisMenu objMenu) {
        if (objMenu == null) {
            MineGuis.get().doLog("objMenu is null! doMenuShow(objMenu);");
            return false;
        }
        if (this.vetMenuCurr(objMenu) == false) {
            MineGuis.get().doLog("objMenu is not current! doMenuShow(objMenu);");
            return false;
        }
        if (objMenu.doHide(this.getPlayer()) == false) {
            MineGuis.get().doLog("failed menu hide! doMenuHide(objMenu);");
            return false;
        }
        if (this.setMenuCurr(null) == false) {
            MineGuis.get().doLog("failed to reset the current menu! doMenuHide(objMenu);");
            return false;
        }
        return true;
    }
    public Boolean doMenuBack() { return doMenuShow(this.getMenuLast()); }
    /* handles */
}
/* endfile */