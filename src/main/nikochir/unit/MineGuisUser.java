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
    public MineGuisUser setPermit(String strPermit, Boolean bitPermit) {
        this.objPermitAttachment.setPermission(strPermit, bitPermit);
        return this;
    }
    public MineGuisUser setMenuCurr(MineGuisMenu objMenu) {
        if (this.objMenuCurr == objMenu) { return this; }
        if (this.objMenuCurr != null) { this.objMenuLast = this.objMenuCurr; }
        this.objMenuCurr = objMenu;
        return this;
    }
    public MineGuisUser setMenuLast(MineGuisMenu objMenu) {
        if (this.objMenuLast == objMenu) { return this; }
        this.objMenuLast = objMenu;
        return this;
    }
    /* vetters */
    public Boolean vetPermit(String strPermit)       { return this.objPermitAttachment.getPermissions().get(strPermit); }
    public Boolean vetMenuCurr()                     { return getMenuCurr() != null; }
    public Boolean vetMenuCurr(MineGuisMenu objMenu) { return getMenuCurr() == objMenu; }
    public Boolean vetMenuLast()                     { return getMenuLast() != null; }
    public Boolean vetMenuLast(MineGuisMenu objMenu) { return getMenuLast() == objMenu; }
    /* actions */
    /* handles */
}
/* endfile */