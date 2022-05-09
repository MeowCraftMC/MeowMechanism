package al.yn.meowmechanism.utility;

import net.minecraft.inventory.CraftingInventory;

public class RecipeHelper {
    public static final byte BYTE_TRUE = (byte) 1;

    public static boolean hasStrictItemInRecipe(CraftingInventory inventory) {
        for (var i = 0; i < inventory.size(); i++) {
            var stack = inventory.getStack(i);
            if (stack.hasNbt()) {
                var nbt = stack.getNbt();
                if (nbt.contains("meowmechanism")) {
                    var m11m = nbt.getCompound("meowmechanism");
                    if (m11m.contains("strict") && m11m.getBoolean("strict")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
