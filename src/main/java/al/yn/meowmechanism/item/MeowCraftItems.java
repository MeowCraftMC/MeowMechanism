package al.yn.meowmechanism.item;

import al.yn.meowmechanism.MeowMechanism;
import al.yn.meowmechanism.registry.ItemsRegistry;
import net.minecraft.util.Identifier;

public class MeowCraftItems {
    public static final String ID = "meowcraft";
    public static final String NAME = "MeowCraft";
    public static final String VERSION = "1.0.0";

    public MeowCraftItems() {
        MeowMechanism.getInstance().getLogger().info("[" + NAME + "] Registering (Fake) Items.");

        addItems();
    }

    public void addItems() {
        ItemsRegistry.getInstance().register(meowLoc("proxyneva"), new ItemsRegistry.Entry(
                meowLoc("proxyneva"), mcLoc("paper"), true));


    }

    public Identifier meowLoc(String path) {
       return new Identifier(ID, path);
    }

    public Identifier mcLoc(String path) {
        return new Identifier("minecraft", path);
    }
}
