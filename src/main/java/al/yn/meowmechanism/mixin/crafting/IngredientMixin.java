package al.yn.meowmechanism.mixin.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ingredient.class)
public class IngredientMixin {
    @Inject(method = "test(Lnet/minecraft/item/ItemStack;)Z", at = @At("TAIL"))
    private void onTest(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

    }
}
