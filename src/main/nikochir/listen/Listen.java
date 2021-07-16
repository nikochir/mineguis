/* package */
package src.main.nikochir.listen;
/* include */
import src.main.nikochir.Main;
import src.main.nikochir.kernel.Unit;
import src.main.nikochir.kernel.User;
import src.main.nikochir.kernel.Item;
import src.main.nikochir.kernel.Menu;
import src.main.nikochir.execut.Execut;
import src.main.nikochir.permit.Permit;
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
import org.bukkit.ChatColor;
/** nkyori **/
import net.kyori.adventure.text.Component;
/* typedef */
/* Listener class
 * > Description:
 * -> ;
*/
public class Listen implements Listener {
    /* handles */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent objEvent) {
        if ((objEvent.getWhoClicked() instanceof Player) == false) {
            Main.get().doLogO("not player click!");
            return;
        }
        Player objPlayer = (Player) objEvent.getWhoClicked();
        if (Menu.vetMenu(objEvent.getView().getTitle()) == false) {
            //Main.get().doLogO("the menu is not found! onInventoryClick(objEvent);");
            return;
        }
        /*Menu objMenu = Menu.getMenu(objEvent.getView().getTitle());
        we do no need this, only check if it exists */
        ItemStack objStack = objEvent.getCurrentItem();
        if (objStack == null) { return; }
        if (objStack.hasItemMeta() == false) { return; }
        if (Item.vetItem(objStack) == false) {
            Main.get().doLogO(
                "the item is not registered! onInventoryClick(objEvent); "
                + objStack.getItemMeta().getDisplayName()
                );
                return;
        }
        // respond in some way;
        objEvent.setCancelled(true);
        objPlayer.playSound(objPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
        // in this case we are cancelling the click producing a sound;
        Item objItem = Item.getItem(objStack);
        if (objItem.doExec(objPlayer) == false) {
            Main.get().doLogO("failed to execute the item command! onIntentoryClick(objEvent);");
            return;
        }
        /*if (objMenu.doPass(objPlayer, objStack) == false) {
            Main.get().doLogO("failed menu pass! onInventoryClick(objEvent);");
            return;
        } we already have all items, no need to pass it in the menu */
    }
    @EventHandler
    public void onInventoryShow(InventoryOpenEvent objEvent) {
        if (User.vetUser(objEvent.getPlayer().getUniqueId().toString()) == false) {
            Main.get().doLogO("user is not found! onInventoryShow(objEvent);");
            return;
        }
        User objUser = User.getUser(objEvent.getPlayer().getUniqueId().toString());
        if (Menu.vetMenu(objEvent.getView().getTitle()) == false) {
            //Main.get().doLogO("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        Menu objMenu = Menu.getMenu(objEvent.getView().getTitle());
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
    public void onInventoryHide(InventoryCloseEvent objEvent) {
        if (User.vetUser(objEvent.getPlayer()) == false) {
            Main.get().doLogO("the user is not found! onInventoryHide(objEvent);");
            return;
        }
        User objUser = User.getUser(objEvent.getPlayer());
        if (Menu.vetMenu(objEvent.getView().getTitle()) == false) {
            //Main.get().doLogO("menu is not found! onInventoryHide(objEvent);");
            return;
        }
        Menu objMenu = Menu.getMenu(objEvent.getView().getTitle());
        if (objUser.vetMenu(objMenu) == false) {
            Main.get().doLogO("this is not the current menu! onInventoryHide(objEvent);");
            return;
        }
        /*if (objUser.setMenu(null) == false) {
            Main.get().doLogO("failed to set the current menu! onInventoryHide(objEvent);");
            return;
        }*/
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent objEvent) {
        if (User.setUser(objEvent.getPlayer())) { Main.get().doLogO("the user has been added!"); }
        else { Main.get().doLogO("failed to create the user!"); return; }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent objEvent) {
        User objUser = User.getUser(objEvent.getPlayer());
    }
    @EventHandler
    public void onPlayerWork(PlayerInteractEvent objEvent) {
        if (User.vetUser(objEvent.getPlayer()) == false) {
            Main.get().doLogO("failed to find the user!");
            return;
        }
        User objUser = User.getUser(objEvent.getPlayer());
    }
}
/* endfile */