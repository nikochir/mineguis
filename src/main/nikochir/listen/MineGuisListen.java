/* package */
package nikochir.listen;
/* include */
import nikochir.MineGuis;
import nikochir.unit.MineGuisUnit;
import nikochir.unit.MineGuisUser;
import nikochir.unit.MineGuisItem;
import nikochir.unit.MineGuisMenu;
import nikochir.unit.MineGuisBook;
import nikochir.execut.MineGuisExecut;
import nikochir.permit.MineGuisPermit;
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
        if (MineGuis.get().<MineGuisUser>vetUnit(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLog("menu is not found!");
            return;
        }
        MineGuisUser objUser = MineGuis.get().<MineGuisUser>getUnit(objPlayer.getUniqueId().toString());
        if (MineGuis.get().<MineGuisMenu>vetUnit(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLog("menu is not found!");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().<MineGuisMenu>getUnit(objEvent.getView().getTitle());
        objEvent.setCancelled(true);
    }
    @EventHandler
    public void onInventoryShow(InventoryOpenEvent objEvent) {
        if (MineGuis.get().<MineGuisUser>vetUnit(objEvent.getPlayer().getUniqueId().toString()) == false) {
            MineGuis.get().doLog("user is not found!");
            return;
        }
        MineGuisUser objUser = MineGuis.get().<MineGuisUser>getUnit(objEvent.getPlayer().getUniqueId().toString());
        if (MineGuis.get().<MineGuisMenu>vetUnit(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLog("menu is not found!");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().<MineGuisMenu>getUnit(objEvent.getView().getTitle());
        objUser.setMenuCurr(objMenu);
    }
    @EventHandler
    public void onInventoryHide(InventoryCloseEvent objEvent) {
        if (MineGuis.get().<MineGuisUser>vetUnit(objEvent.getPlayer().getUniqueId().toString()) == false) {
            MineGuis.get().doLog("user is not found!");
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer().getName());
        if (MineGuis.get().<MineGuisMenu>vetUnit(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLog("menu is not found!");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {
        if (MineGuis.get().<MineGuisUser>addUnit(new MineGuisUser(objEvent.getPlayer())) == false) {
            MineGuis.get().doLog("failed to insert a player!");
            return;
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {
        if (MineGuis.get().<MineGuisUser>rmvUnit(objEvent.getPlayer().getUniqueId().toString()) == false) {
            MineGuis.get().doLog("failed to remove a player!");
            return;
        }
    }
    @EventHandler
    public void onPlayerWork(PlayerInteractEvent objEvent) {
        if (MineGuis.get().vetUnit(objEvent.getPlayer().getUniqueId().toString()) == false) {
            MineGuis.get().doLog("failed to find a player!");
            return;
        }
        //
    }
}
/* endfile */