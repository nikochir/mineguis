/* [nikochir]: 2021/05/05; */
/* package */
package nikochir;
/* include */
import nikochir.MineGuis;
import nikochir.menu.*;
import nikochir.item.*;
/* javkit */
import java.util.Stack;
/* bukkit */
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.entity.Player;
/* typedef */
/*
* MineGuisUser
* > Description:
* -> ;
*/
public class MineGuisUser {
    /* members */
    private PermissionAttachment objPermitAttachment;
    private Stack<MineGuisMenu> arrMenuHistory;
    /* codetor */
    public MineGuisUser(Player objPlayer) {
        this.objPermitAttachment = new PermissionAttachment(MineGuis.get(), objPlayer);
        this.arrMenuHistory = new Stack<MineGuisMenu>();
    }
    /* getters */
    public Player getPlayer() { return (Player) this.objPermitAttachment.getPermissible(); }
    public MineGuisMenu getMenu() { return arrMenuHistory.peek(); }
    /* setters */
    public MineGuisUser setPermit(String strPermit, Boolean bitPermit) {
        this.objPermitAttachment.setPermission(strPermit, bitPermit);
        return this;
    }
    public MineGuisUser addMenu(MineGuisMenu objMenu) {
        objMenu.doShow(this.getPlayer());
        if (getMenu() != objMenu) { this.arrMenuHistory.push(objMenu); }
        return this;
    }
    public MineGuisUser rmvMenu(MineGuisMenu objMenu) {
        objMenu.doHide(this.getPlayer());
        if (getMenu() == objMenu) { this.arrMenuHistory.pop(); }
        return this;
    }
    /* vetters */
    public Boolean vetPermit(String strPermit) { return this.objPermitAttachment.getPermissions().get(strPermit); }
    public Boolean vetMenu()                     { return getMenu() != null; }
    public Boolean vetMenu(MineGuisMenu objMenu) { return getMenu() == objMenu; }
    /* actions */
    public MineGuisUser doClearHistory() {
        arrMenuHistory.clear();
        return this;
    }
}
/* end_of_file */