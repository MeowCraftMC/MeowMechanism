package al.yn.meowmechanism.module.be_a_cat;

import al.yn.meowmechanism.module.be_a_cat.interfaces.ICatPlayerMixin;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BeACat {
    public BeACat() {
        UseEntityCallback.EVENT.register(this::onUseEntity);
    }

    private ActionResult onUseEntity(PlayerEntity player, World world, Hand hand,
                                     Entity entity, @Nullable EntityHitResult hitResult) {
        if (entity instanceof ICatPlayerMixin) {
            var cat = (ICatPlayerMixin) entity;
            cat.meow();
        }

        return ActionResult.PASS;
    }
}
