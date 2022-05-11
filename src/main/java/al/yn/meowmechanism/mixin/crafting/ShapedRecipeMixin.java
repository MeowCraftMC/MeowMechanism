package al.yn.meowmechanism.mixin.crafting;

import al.yn.meowmechanism.utility.RecipeHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {
    @Shadow
    public abstract Identifier getId();

    @Inject(method = "matchesPattern", at = @At("RETURN"), cancellable = true)
    private void afterMatchesPattern(CraftingInventory inv, int offsetX, int offsetY,
                                boolean flipped, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            if (getId().getNamespace().equals("minecraft")) {
                var ret = RecipeHelper.hasStrictItemInRecipe(inv);
                cir.setReturnValue(!ret);
            }
        }
    }
}
