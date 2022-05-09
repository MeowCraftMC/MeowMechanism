package al.yn.meowmechanism.mixin.crafting;

import al.yn.meowmechanism.utility.RecipeHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapelessRecipe.class)
public abstract class ShapelessRecipeMixin {
    @Shadow
    public abstract Identifier getId();

    @Inject(method = "matches(Lnet/minecraft/inventory/CraftingInventory;Lnet/minecraft/world/World;)Z",
            at = @At("RETURN"), cancellable = true)
    private void afterMatches(CraftingInventory inv, World world, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            if (getId().getNamespace().equals("minecraft")) {
                var ret = RecipeHelper.hasStrictItemInRecipe(inv);
                cir.setReturnValue(!ret);
            }
        }
    }
}
