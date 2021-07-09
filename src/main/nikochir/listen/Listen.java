/* package */
package nikochir.listen;
/* include */
import nikochir.Main;
import nikochir.kernel.Unit;
import nikochir.kernel.User;
import nikochir.kernel.Item;
import nikochir.kernel.Menu;
import nikochir.kernel.Book;
import nikochir.execut.Execut;
import nikochir.permit.Permit;
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
/* Listener class
 * > Description:
 * -> global event listener for all main general events;
 * --> processes every single player event related to the gui;
 * -> user could be a listener itself, but because we are listening to
 * global events, every single user would have to listen each other;
 * --> so we have only one global listener for all global events;
*/
public class Listen implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {
        User objUser = User.getUser(objEvent.getPlayer());
        if (User.vetUser(objEvent.getPlayer()) != true) {
            Main.get().doLogO("the user is not registered! onPlayerJoin(objEvent);");
            return;
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {
        if (User.vetUser(objEvent.getPlayer()) != true) {
            Main.get().doLogO("the user is not registered! onPlayerJoin(objEvent);");
            return;
        }
        User objUser = User.getUser(objEvent.getPlayer());
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent objEvent) {
        if (User.vetUser(objEvent.getPlayer()) != true) {
            Main.get().doLogO("failed to find the user!");
            return;
        }
        User objUser = User.getUser(objEvent.getPlayer());
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
                if (Item.vetItem(objStack) != true) { return; }
                Item objItem = Item.getItem(objStack);
                objEvent.setCancelled(true);
                if (objItem.doExec(objEvent.getPlayer()) != true) {
                    Main.get().doLogO("failed to execute the item! onPlayerInteract(objEvent);");
                    return;
                }
            }
            break;
        }
    }
    /* handles */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) != true) {
            Main.get().doLogO("not player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        if (Menu.vetMenu(objEvent.getView()) != true) {
            Main.get().doLogO("the menu is not found! onInventoryClick(objEvent);");
            return;
        }
        /* Menu objMenu = Menu.getMenu(objEvent.getView().getTitle());
        we do not need this, only check if it exists */
        // respond in some way;
        objEvent.setCancelled(true);
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        // in this case we are cancelling the click producing a sound;
        ItemStack objStack = objEvent.getCurrentItem();
        if (objStack == null) { return; }
        if (Item.vetItem(objStack) != true) {
            Main.get().doLogO(
                "the item is not registered! onInventoryClick(objEvent); "
                + objStack.getItemMeta().getDisplayName()
            );
            return;
        }
        if (Item.getItem(objStack).doExec(objPlayer) != true) {
            Main.get().doLogO("failed to execute the item command! onIntentoryClick(objEvent);");
            return;
        }
        /* if (objMenu.doPass(objPlayer, objStack) == false) {
            Main.get().doLogO("failed menu pass! onInventoryClick(objEvent);");
            return;
        } we already have all items, no need to pass it in the menu */
    }
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent objEvent) {
        if (User.vetUser(objEvent.getPlayer()) != true) {
            Main.get().doLogO("user is not found! onInventoryShow(objEvent);");
            return;
        }
        User objUser = User.getUser(objEvent.getPlayer());
        if (Menu.vetMenu(objEvent.getView()) != true) {
            //Main.get().doLogO("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        Menu objMenu = Menu.getMenu(objEvent.getView());
        if (objUser.vetMenu(objMenu) == true) {
            Main.get().doLogO("this is already current menu! onInventoryShow(objEvent);");
            return;
        }
        if (objUser.setMenu(objMenu) == false) {
            Main.get().doLogO("failed to set the current menu! onInventoryShow(objEvent);");
            return;
        }
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent objEvent) {
        if (User.vetUser(objEvent.getPlayer()) == false) {
            Main.get().doLogO("user is not found! onInventoryHide(objEvent);");
            return;
        }
        User objUser = User.getUser(objEvent.getPlayer());
        if (Menu.vetMenu(objEvent.getView()) != true) {
            //Main.get().doLogO("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        Menu objMenu = Menu.getMenu(objEvent.getView());
        if (objUser.vetMenu(objMenu) != true) {
            Main.get().doLogO("this is not the current menu! onInventoryHide(objEvent);");
            return;
        }
        /*if (objUser.setMenu(null) == false) {
            Main.get().doLogO("failed to set the current menu! onInventoryHide(objEvent);");
            return;
        }*/
    }
}
/* endfile */