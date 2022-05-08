package al.yn.meowmechanism.module.be_a_cat.interfaces;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

public interface ICatPlayerMixin {
    // qyl27: meow~.
    public void meow();
    public void setMeow(boolean meow);
    public boolean isCat();
    public void setCat(boolean cat);
}
