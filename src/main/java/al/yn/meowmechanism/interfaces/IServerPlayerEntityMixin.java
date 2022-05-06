package al.yn.meowmechanism.interfaces;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

public interface IServerPlayerEntityMixin {
    // qyl27: meow~.
    public void meow();
    public void setMeow(boolean meow);
    public boolean isCat();
    public void setCat(boolean cat);

    public void playSoundToClient(SoundEvent event, SoundCategory category, float volume, float pitch);
    public void playSoundIn(BlockPos pos, SoundEvent event, SoundCategory category, float volume, float pitch);
}
