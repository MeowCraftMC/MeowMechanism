package al.yn.meowmechanism.module.animals.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {
    protected AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At("TAIL"))
    private void afterDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()){
            if (source.getAttacker() instanceof LivingEntity) {
                var thisAnimal = (AnimalEntity) (Object) this;

                var world = thisAnimal.getEntityWorld();
                var box = thisAnimal.getBoundingBox().expand(8.0D, 4.0D, 8.0D);
                var entities = world.getEntitiesByType(thisAnimal.getType(), box,
                        entity -> entity.getType() == thisAnimal.getType());

                for (var animal : entities) {
                    if (animal instanceof AnimalEntity) {
                        ((AnimalEntity) animal).setAttacker((LivingEntity) source.getAttacker());
                    }
                }
            }
        }
    }
}
