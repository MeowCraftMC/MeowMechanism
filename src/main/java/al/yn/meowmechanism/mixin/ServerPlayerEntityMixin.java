package al.yn.meowmechanism.mixin;

import al.yn.meowmechanism.interfaces.IServerPlayerEntityMixin;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements IServerPlayerEntityMixin {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Shadow
    public ServerPlayNetworkHandler networkHandler;

    private boolean cat = false;

    @Override
    public boolean isCat() {
        return cat;
    }

    @Override
    public void setCat(boolean value) {
        cat = value;
    }

    @Override
    public void meow() {
        // Todo.
        if (isCat()) {
            playSoundIn(getBlockPos(), SoundEvents.ENTITY_CAT_PURR,
                    SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }

    @Override
    public void setMeow(boolean meow) {
        cat = meow;
    }

    @Override
    public void playSoundToClient(SoundEvent event, SoundCategory category, float volume, float pitch) {
        networkHandler.sendPacket(new PlaySoundS2CPacket(event, category,
                getX(), getY(), getZ(), volume, pitch));
    }

    @Override
    public void playSoundIn(BlockPos pos, SoundEvent event, SoundCategory category, float volume, float pitch) {
        networkHandler.sendPacket(new PlaySoundS2CPacket(event, category,
                pos.getX(), pos.getY(), pos.getZ(), volume, pitch));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void onReadCustomDataFromNbt(NbtCompound tag, CallbackInfo ci) {
        if (tag.contains("cat")) {
            setCat(tag.getBoolean("cat"));
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void onWriteCustomDataToNbt(NbtCompound tag, CallbackInfo ci) {
        tag.putBoolean("cat", isCat());
    }
}
