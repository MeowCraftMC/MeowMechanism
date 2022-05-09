package al.yn.meowmechanism.module.harvest.mixin;

import al.yn.meowmechanism.interfaces.IServerPlayerEntityMixin;
import al.yn.meowmechanism.module.harvest.HarvestHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetherWartBlock.class)
public abstract class NetherWartBlockMixin extends PlantBlock {
    public NetherWartBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    @Final
    public static IntProperty AGE;

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient() && player instanceof ServerPlayerEntity) {
            if (state.get(AGE) >= 3) {
                var serverPlayer = (IServerPlayerEntityMixin) player;
                serverPlayer.playSoundIn(pos, SoundEvents.BLOCK_CROP_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return HarvestHelper.harvest(state, (ServerWorld) world, pos, player, hand, Items.NETHER_WART, AGE);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
