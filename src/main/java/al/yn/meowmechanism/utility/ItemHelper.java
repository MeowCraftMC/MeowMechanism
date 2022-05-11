package al.yn.meowmechanism.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemHelper {
    public static boolean isByNbt(Identifier id, ItemStack stack) {
        if (stack.hasNbt()) {
            var nbt = stack.getNbt();
            if (nbt.contains("meowmechanism")) {
                var m11m = nbt.getCompound("meowmechanism");
                if (m11m.contains("id")) {
                    var stackId = new Identifier(m11m.getString("id"));
                    return id.equals(stackId);
                }
            }
        }

        return false;
    }
}
