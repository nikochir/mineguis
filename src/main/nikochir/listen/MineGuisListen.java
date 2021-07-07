/* package */
package nikochir.listen;
/* include */
import nikochir.MineGuis;
import nikochir.kernel.MineGuisUnit;
import nikochir.kernel.MineGuisUser;
import nikochir.kernel.MineGuisItem;
import nikochir.kernel.MineGuisMenu;
import nikochir.kernel.MineGuisBook;
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
import org.bukkit.event.block.Action;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Sound;
/* typedef */
/* MineGuisListener class
 * > Description:
 * -> global event listener for all main general events;
 * --> processes every single player event related to the gui;
 * -> user could be a listener itself, but because we are listening to
 * global events, every single user would have to listen each other;
 * --> so we have only one global listener for all global events;
*/
public class MineGuisListen implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {
        MineGuisUser objUser = new MineGuisUser(objEvent.getPlayer());
        if (MineGuis.get().vetUser(objUser) == true) {
            MineGuis.get().doLogO("the user has already been created! onUserJoin(objEvent);");
            return;
        }
        if (MineGuis.get().addUser(objUser) == false) {
            MineGuis.get().doLogO("failed to create the user! onUserJoin(objEvent);");
            return;
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer());
        if (MineGuis.get().vetUser(objUser) == false) {
            MineGuis.get().doLogO("the user has not been created! onUserJoin(objEvent);");
            return;
        }
        if (MineGuis.get().rmvUser(objUser) == false) {
            MineGuis.get().doLogO("failed to delete the user! onUserQuit(objEvent);");
            return;
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer()) == false) {
            MineGuis.get().doLogO("failed to find the user!");
            return;
        }
        if (objEvent.isCancelled() == true) { return; }
        Action objAction = objEvent.getAction();
        switch (objAction) {
            case PHYSICAL: {
            }
            break;
            case LEFT_CLICK_AIR: case LEFT_CLICK_BLOCK: {
            }
            break;
            case RIGHT_CLICK_AIR: case RIGHT_CLICK_BLOCK: {
                if (objEvent.hasItem() == false) { return; }
                ItemStack objStack = objEvent.getItem();
                if (MineGuis.get().vetItem(objStack) == false) { return; }
                MineGuisItem objItem = MineGuis.get().getItem(objStack);
                objEvent.setCancelled(true);
                if (objItem.doExec(objEvent.getPlayer()) == false) {
                    MineGuis.get().doLogO("failed to execute the item! onPlayerInteract(objEvent);");
                    return;
                }
            }
            break;
        }
    }
    /* handles */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            MineGuis.get().doLogO("not player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            MineGuis.get().doLogO("the menu is not found! onInventoryClick(objEvent);");
            return;
        }
        /*MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        we do no need this, only check if it exists */
        // respond in some way;
        objEvent.setCancelled(true);
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        // in this case we are cancelling the click producing a sound;
        ItemStack objStack = objEvent.getCurrentItem();
        if (objStack == null) { return; }
        if (MineGuis.get().vetItem(objStack) == false) {
            MineGuis.get().doLogO(
                "the item is not registered! onInventoryClick(objEvent); "
                + objStack.getItemMeta().getDisplayName()
            );
            return;
        }
        if (MineGuis.get().getItem(objStack).doExec(objPlayer) == false) {
            MineGuis.get().doLogO("failed to execute the item command! onIntentoryClick(objEvent);");
            return;
        }
        /*if (objMenu.doPass(objPlayer, objStack) == false) {
            MineGuis.get().doLogO("failed menu pass! onInventoryClick(objEvent);");
            return;
        } we already have all items, no need to pass it in the menu */
    }
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer().getUniqueId().toString()) == false) {
            MineGuis.get().doLogO("user is not found! onInventoryShow(objEvent);");
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer().getUniqueId().toString());
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            //MineGuis.get().doLogO("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        if (objUser.vetMenu(objMenu) == true) {
            MineGuis.get().doLogO("this is already current menu! onInventoryShow(objEvent);");
            return;
        }
        if (objUser.setMenu(objMenu) == false) {
            MineGuis.get().doLogO("failed to set the current menu! onInventoryShow(objEvent);");
            return;
        }
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent objEvent) {
        if (MineGuis.get().vetUser(objEvent.getPlayer()) == false) {
            MineGuis.get().doLogO("user is not found! onInventoryHide(objEvent);");
            return;
        }
        MineGuisUser objUser = MineGuis.get().getUser(objEvent.getPlayer());
        if (MineGuis.get().vetMenu(objEvent.getView().getTitle()) == false) {
            //MineGuis.get().doLogO("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        MineGuisMenu objMenu = MineGuis.get().getMenu(objEvent.getView().getTitle());
        if (objUser.vetMenu(objMenu) == false) {
            MineGuis.get().doLogO("this is not the current menu! onInventoryHide(objEvent);");
            return;
        }
        /*if (objUser.setMenu(null) == false) {
            MineGuis.get().doLogO("failed to set the current menu! onInventoryHide(objEvent);");
            return;
        }*/
    }
}
/* endfile */