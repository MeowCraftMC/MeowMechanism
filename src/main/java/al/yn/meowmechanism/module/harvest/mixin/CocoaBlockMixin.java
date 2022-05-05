package al.yn.meowmechanism.module.harvest.mixin;

import al.yn.meowmechanism.module.harvest.HarvestHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CocoaBlock.class)
public abstract class CocoaBlockMixin extends HorizontalFacingBlock {
    protected CocoaBlockMixin(Settings settings) {
        super(settings);
    }

    // Todo: Add a config for it.
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (state.get(CocoaBlock.AGE) >= 2) {
                var serverPlayer = (ServerPlayerEntity) player;
                serverPlayer.playSound(SoundEvents.ITEM_CROP_PLANT, 1.0f, 1.0f);
                return HarvestHelper.harvest(state, (ServerWorld) world, pos, player, hand, Items.COCOA_BEANS, CocoaBlock.AGE);
            }
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }
}
