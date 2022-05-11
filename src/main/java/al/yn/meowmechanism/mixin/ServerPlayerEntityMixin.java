package al.yn.meowmechanism.mixin;

import al.yn.meowmechanism.interfaces.IServerPlayerEntityMixin;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements IServerPlayerEntityMixin {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Shadow
    public ServerPlayNetworkHandler networkHandler;

    @Shadow public abstract ServerWorld getWorld();

    @Shadow @Final public MinecraftServer server;

    @Override
    public void playSoundToClient(SoundEvent sound, SoundCategory category, float volume, float pitch) {
        networkHandler.sendPacket(new PlaySoundS2CPacket(sound, category,
                getX(), getY(), getZ(), volume, pitch));
    }

    @Override
    public void playSoundIn(BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch) {
        broadcastSound(pos, sound, category, volume, pitch, true);
    }

    @Override
    public void broadcastSound(BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean distance) {
        var playerWorld = getWorld();

        if (!playerWorld.isClient) {
            server.getPlayerManager().sendToAround(null, pos.getX(), pos.getY(), pos.getZ(),
                    volume > 1.0F ? (double)(16.0F * volume) : 16.0, getWorld().getRegistryKey(),
                    new PlaySoundFromEntityS2CPacket(sound, category, this, volume, pitch));
        }
    }
}
