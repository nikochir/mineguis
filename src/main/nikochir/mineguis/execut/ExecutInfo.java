/* package */
package nikochir.mineguis.execut;
/* include */
import nikochir.mineguis.Main;
import nikochir.mineguis.kernel.User;
import nikochir.mineguis.kernel.Item;
import nikochir.mineguis.kernel.Menu;
/** javkit **/
import java.util.HashMap;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/** jbrains - NotNull annotation **/
import org.jetbrains.annotations.NotNull;
/* typedef */
/* ExecutorInfo class
 * > Description:
 * -> ;
 */
public class ExecutInfo implements CommandExecutor {
    /* actions */
    private boolean doSendListofUser(CommandSender objSender) {
        HashMap<String, User> tab = User.getUserTab();
        if (tab == null) {
            Main.get().doLogO(objSender, "there is no initialized user table!");
            return false;
        } else if (tab.isEmpty()) {
            Main.get().doLogO(objSender, "there is no users in the table!");
            return false;
        }
        Main.get().doLogO(objSender, "========<listof_user>========");
        int numUser = 0;
        for (HashMap.Entry<String, User> objPair : tab.entrySet()) {
            User objUser = objPair.getValue();
            Main.get().doLogO(objSender, "[%d]: sign: %s;", numUser++, objUser.getSign());
        }
        return true;
    }
    private boolean doSendListofItem(CommandSender objSender) {
        HashMap<String, Item> tab = Item.getItemTab();
        if (tab == null) {
            Main.get().doLogO(objSender, "there is no initialized item table!");
            return false;
        } else if (tab.isEmpty()) {
            Main.get().doLogO(objSender, "there is no items in the table!");
            return false;
        }
        Main.get().doLogO(objSender, "========<listof_item>========");
        int numItem = 0;
        for (HashMap.Entry<String, Item> objPair : tab.entrySet()) {
            Item objItem = objPair.getValue();
            Main.get().doLogO(objSender, "[%d]: sign: %s; icon: %s; exec: %s;", numItem++, objItem.getSign(), objItem.getIcon(), objItem.getExec());
        }
        return true;
    }
    private boolean doSendListofMenu(CommandSender objSender) {
        HashMap<String, Menu> tab = Menu.getMenuTab();
        if (tab == null) {
            Main.get().doLogO(objSender, "there is no initialized menu table!");
            return false;
        } else if (tab.isEmpty()) {
            Main.get().doLogO(objSender, "there is no menus in the table!");
            return false;
        }
        Main.get().doLogO(objSender, "========<listof_menu>========");
        int numMenu = 0;
        for (HashMap.Entry<String, Menu> objPair : tab.entrySet()) {
            Menu objMenu = objPair.getValue();
            Main.get().doLogO(objSender, "[%d]: sign: %s; size: %d;", numMenu++, objMenu.getSign(), objMenu.getSizeInLines());
        }
        return true;
    }
    /* handles */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if (strArgs.length == 0) {
            this.doSendListofUser(objSender);
            this.doSendListofItem(objSender);
            this.doSendListofMenu(objSender);
            return true;
        } else {
            Main.get().doLogO(objSender, "invalid argument count: %d!", strArgs.length);
            return false;
        }
    }
}
/* endfile */