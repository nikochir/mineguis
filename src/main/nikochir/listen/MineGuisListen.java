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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Sound;
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
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLog("the menu is not found! onInventoryClick(objEvent);");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        // respond in some way;
        objEvent.setCancelled(true);
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        // in this case we are cancelling the click producing a sound;
        ItemStack objStack = objEvent.getCurrentItem();
        if (objStack == null) { return; }
        if (MineGuis.get().vetItem(objStack) == false) {
            MineGuis.get().doLog(
                "the item is not registered! onInventoryClick(objEvent); "
                + objStack.getItemMeta().getDisplayName()
            );
            return;
        }
        if (MineGuis.get().getItem(objStack).doExecute(objPlayer) == false) {
            MineGuis.get().doLog("failed to execute the item command! onIntentoryClick(objEvent);");
            return;
        }
        /*if (objMenu.doPass(objPlayer, objStack) == false) {
            MineGuis.get().doLog("failed menu pass! onInventoryClick(objEvent);");
            return;
        } we already have all items, no need to pass it in the menu */
    }
    @EventHandler
    public void onInventoryShow(InventoryOpenEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer().getUniqueId().toString()) == false) {
            MineGuis.get().doLog("user is not found! onInventoryShow(objEvent);");
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer().getUniqueId().toString());
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            //MineGuis.get().doLog("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        objUser.setMenuCurr(objMenu);
    }
    @EventHandler
    public void onInventoryHide(InventoryCloseEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer()) == false) {
            MineGuis.get().doLog("user is not found! onInventoryHide(objEvent);");
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer());
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            //MineGuis.get().doLog("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        //objUser.setMenuLast(objMenu);
        objUser.setMenuCurr(null);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {
        MineGuisUser objUser = new MineGuisUser(objEvent.getPlayer());
        if (MineGuis.get().addUser(objUser) == false) {
            MineGuis.get().doLog("failed to create the user!");
            return;
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer());
        if (MineGuis.get().rmvUser(objUser) == false) {
            MineGuis.get().doLog("failed to delete the user!");
            return;
        }
    }
    @EventHandler
    public void onPlayerWork(PlayerInteractEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer()) == false) {
            MineGuis.get().doLog("failed to find the user!");
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer());
    }
}
/* endfile */