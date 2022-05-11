package al.yn.meowmechanism;

import al.yn.meowmechanism.item.MeowCraftItems;
import al.yn.meowmechanism.module.be_a_cat.BeACat;
import net.fabricmc.api.DedicatedServerModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeowMechanism implements DedicatedServerModInitializer {
    public static final String MOD_ID = "meowmechanism";
    public static final String NAME = "MeowMechanism";
    public static final String VERSION = "1.0.2";

    private static MeowMechanism INSTANCE = null;

    private Logger logger = LogManager.getLogger(NAME);

    public MeowMechanism() {
        if (INSTANCE != null) {
            throw new RuntimeException("Unexpected initialize.");
        }

        INSTANCE = this;
    }

    public static MeowMechanism getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void onInitializeServer() {
        logger.info("MeowMechanism " + VERSION + " is running!");

        new MeowCraftItems();
        new BeACat();
    }
}
