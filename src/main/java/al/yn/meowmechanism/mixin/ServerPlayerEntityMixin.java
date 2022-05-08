package al.yn.meowmechanism.mixin;

import al.yn.meowmechanism.interfaces.IServerPlayerEntityMixin;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements IServerPlayerEntityMixin {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Shadow
    public ServerPlayNetworkHandler networkHandler;

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
}
