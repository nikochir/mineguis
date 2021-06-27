/* package */
package nikochir.listen;
/* include */
import nikochir.MineGuis;
import nikochir.MineGuisUser;
import nikochir.item.MineGuisItem;
import nikochir.menu.MineGuisMenu;
import nikochir.execut.MineGuisExecut;
/** bukkit **/
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
/* typedef */
/* MineGuisListener class
 * > Description:
 * -> ;
*/
public class MineGuisListen implements Listener {
    /* handles */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            MineGuis.get().doLog("not player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
    }
    @EventHandler
    public void onInventoryShow(InventoryOpenEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer().getName()) == false) {
            MineGuis.get().doLog(String.format("user is not found! name: %s;",objEvent.getPlayer().getName()));
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer().getName());
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLog("menu is not found!");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        objUser.setMenuCurr(objMenu);
    }
    @EventHandler
    public void onInventoryHide(InventoryCloseEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer().getName()) == false) {
            MineGuis.get().doLog(String.format("user is not found! name: %s;",objEvent.getPlayer().getName()));
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer().getName());
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLog("menu is not found!");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        //objUser.setMenuCurr(null);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {
        if (MineGuis.get().addUser(objEvent.getPlayer().getName()) == false) {
            MineGuis.get().doLog("failed to insert a player!");
            return;
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {
        if (MineGuis.get().rmvUser(objEvent.getPlayer().getName()) == false) {
            MineGuis.get().doLog("failed to remove a player!");
            return;
        }
    }
    @EventHandler
    public void onPlayerWork(PlayerInteractEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer().getName()) == false) {
            MineGuis.get().doLog("failed to find a player!");
            return;
        }
        //
    }
}
/* end_of_file */