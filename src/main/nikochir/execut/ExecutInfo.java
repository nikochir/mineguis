/* package */
package src.main.nikochir.execut;
/* include */
import src.main.nikochir.Main;
import src.main.nikochir.kernel.Unit;
import src.main.nikochir.kernel.Menu;
import src.main.nikochir.kernel.User;
import src.main.nikochir.kernel.Book;
import src.main.nikochir.kernel.Item;
import src.main.nikochir.execut.Execut;
/** javkit **/
import java.util.HashMap;
/** bukkit - command interface **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
    private boolean doSendListofBook(CommandSender objSender) {
        HashMap<String, Book> tab = Book.getBookTab();
        if (tab == null) {
            Main.get().doLogO(objSender, "there is no initialized book table!");
            return false;
        } else if (tab.isEmpty()) {
            Main.get().doLogO(objSender, "there is no books in the table!");
            return false;
        }
        Main.get().doLogO(objSender, "========<listof_book>========");
        int numBook = 0;
        for (HashMap.Entry<String, Book> objPair : tab.entrySet()) {
            Book objBook = objPair.getValue();
            Main.get().doLogO(objSender, "[%d]: sign: %s; size: [%d, %d];", numBook++, objBook.getSign(), objBook.getSizeInPages(), objBook.getSizeOfPages());
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
        /*if ((objSender instanceof Player) == false) {
            Main.get().doLogO("this is not a player call!");
            return false;
        }*/
        Player objPlayer = (Player) objSender;
        if (strArgs.length == 0) {
            if (this.doSendListofUser(objSender) == false) {
                Main.get().doLogO(objSender, "failed to show the list of users!");
                return false;
            }
            if (this.doSendListofItem(objSender) == false) {
                Main.get().doLogO(objSender, "failed to show the list of items!");
                return false;
            }
            if (this.doSendListofMenu(objSender) == false) {
                Main.get().doLogO(objSender, "failed to show the list of menus!");
                return false;
            }
            if (this.doSendListofBook(objSender) == false) {
                Main.get().doLogO(objSender, "failed to show the list of books!");
                return false;
            }
            return true;
        } else {
            Main.get().doLogO(objSender, "invalid argument count: %d!", strArgs.length);
            return false;
        }
    }
}
/* endfile */