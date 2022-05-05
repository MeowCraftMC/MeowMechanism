package al.yn.meowmechanism.module.harvest.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneBlockMixin extends Block implements Fertilizable {
    public SugarCaneBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    @Final
    public static IntProperty AGE;

    @Shadow public abstract void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        var top = getTop(world, pos, state);

        if (world.isAir(top.up())) {
            return true;
        }

        // Todo: Check max length.

        return false;
    }

    // Todo: Add config for max length.
    public int getLength(World world, BlockPos pos, BlockState state) {
        var length = 1;

        while (world.getBlockState(pos.down(length)).isOf(getDefaultState().getBlock())) {
            length += 1;
        }

        return length;
    }

    public BlockPos getTop(World world, BlockPos pos, BlockState state) {
        var top = pos;
        while (world.getBlockState(top.up()).isOf(getDefaultState().getBlock())) {
            top = top.up();
        }

        return top;
    }

    // Fixme: Maybe a better way to do this?
    //        But it works for now.
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (state.isOf(getDefaultState().getBlock())) {
            var top = getTop(world, pos, state);
            var prevAge = world.getBlockState(top).get(AGE);

            var grows = random.nextInt(0, 12);
            var newAge = grows + prevAge;
            if (newAge <= 15) {
                doAddAges(world, top, state, newAge);
            } else {
                doGrow(world, top, state);
            }
        }
    }

    public void doGrow(ServerWorld world, BlockPos top, BlockState state) {
        world.setBlockState(top.up(), getDefaultState());
        world.setBlockState(top, state.with(AGE, 0), 4);
    }

    public void doAddAges(ServerWorld world, BlockPos top, BlockState state, int newAge) {
        world.setBlockState(top, state.with(AGE, newAge), 4);
    }
}
