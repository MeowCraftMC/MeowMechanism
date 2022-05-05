package al.yn.meowmechanism.module.harvest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class HarvestHelper {
    public static ActionResult harvest(BlockState state, ServerWorld world, BlockPos pos, PlayerEntity player, Hand hand, Item seed, IntProperty ageProperty) {
        final List<ItemStack> dropWithChances = Block.getDroppedStacks(state, world, pos, null, player, player.getStackInHand(hand));
        final DefaultedList<ItemStack> drops = loot(seed, dropWithChances);
        world.setBlockState(pos, state.with(ageProperty, 0));
        ItemScatterer.spawn(world, pos, drops);
        return ActionResult.SUCCESS;
    }

    public static DefaultedList<ItemStack> loot(Item seedItem, List<ItemStack> dropWithChances) {
        final DefaultedList<ItemStack> drops = DefaultedList.ofSize(dropWithChances.size());
        drops.addAll(dropWithChances);
        for (final ItemStack stack : drops) {
            if (stack.getItem() == seedItem) {
                final ItemStack seedStack = stack.copy();
                drops.remove(stack);
                seedStack.decrement(1);
                drops.add(seedStack);
                break;
            }
        }
        return drops;
    }
}
