package al.yn.meowmechanism.module.be_a_cat;

import al.yn.meowmechanism.module.be_a_cat.interfaces.ICatPlayerMixin;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BeACat {
    public BeACat() {
        UseEntityCallback.EVENT.register(this::onUseEntity);
        UseItemCallback.EVENT.register(this::onUseItem);
    }

    private ActionResult onUseEntity(PlayerEntity player, World world, Hand hand,
                                     Entity entity, @Nullable EntityHitResult hitResult) {
        if (entity instanceof ICatPlayerMixin) {
            var cat = (ICatPlayerMixin) entity;
            cat.meow();
        }

        return ActionResult.PASS;
    }

    private TypedActionResult<ItemStack> onUseItem(PlayerEntity playerEntity, World world, Hand hand) {
        return null;
    }
}
