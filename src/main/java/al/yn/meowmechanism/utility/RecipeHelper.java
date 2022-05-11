package al.yn.meowmechanism.utility;

import al.yn.meowmechanism.registry.ItemsRegistry;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.util.Identifier;

public class RecipeHelper {
    public static final byte BYTE_TRUE = (byte) 1;

    public static boolean hasStrictItemInRecipe(CraftingInventory inventory) {
        for (var i = 0; i < inventory.size(); i++) {
            var stack = inventory.getStack(i);
            if (stack.hasNbt()) {
                var nbt = stack.getNbt();
                if (nbt.contains("meowmechanism")) {
                    var m11m = nbt.getCompound("meowmechanism");
                    if (m11m.contains("id")) {
                        var id = new Identifier(m11m.getString("id"));

                        if (ItemsRegistry.getInstance().hasEntry(id)) {
                            return ItemsRegistry.getInstance().getEntry(id).isRemoveVanillaCrafting();
                        }
                    }
                }
            }
        }
        return false;
    }
}
