/* package */
package nikochir.execut;
/* include */
import nikochir.MineGuis;
/** javkit **/
import java.util.ArrayList;
/** bukkit **/
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
/* typedef */
/* MineGuisExecutor class
 * Description:
 * -> ;
*/
public class MineGuisExecut implements CommandExecutor {
    /* onevent */
    @Override
    public boolean onCommand(
        @NotNull CommandSender objSender,
        @NotNull Command objCommand,
        @NotNull String strLabel,
        @NotNull String[] strArgs
    ) {
        if (objSender instanceof Player) {
            Player objPlayer = (Player) objSender;
            //if (objPlayer.hasPermission("mineguis.user") == false) { return false; }
            /* create and customize the inventory */
            Inventory objPack = Bukkit.createInventory(objPlayer, 9, Component
                .text(MineGuis.get().getConfig().getString("root_name"))
                .color(TextColor.fromHexString("aaffff"))
                .decorate(TextDecoration.UNDERLINED)
            );
            /* create and customize the items */
            ItemStack objItem0 = new ItemStack(Material.BOW);
            ItemMeta objMeta0 = objItem0.getItemMeta();
            objMeta0.displayName(Component
                .text("jumper bow")
                .color(TextColor.fromHexString("aaaaff"))
                .decorate(TextDecoration.ITALIC)
            );
            ArrayList<Component> txtLore0 = new ArrayList<Component>();
            txtLore0.add(Component
                .text("you can appear where your arrow falls")
                .color(TextColor.fromHexString("ffaaaa"))
                .decorate(TextDecoration.BOLD)
            );
            objMeta0.lore(txtLore0);
            objMeta0.addEnchant(Enchantment.ARROW_INFINITE, 5, true);
            objItem0.setItemMeta(objMeta0);
            ItemStack arrItems[] = { objItem0 };
            /* add items */
            objPack.addItem(arrItems);
            /* display the panel */
            objPlayer.openInventory(objPack);
            return true;
        }
        return true;
    }
}
/* end_of_file */