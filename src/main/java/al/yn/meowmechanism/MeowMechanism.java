package al.yn.meowmechanism;

import net.fabricmc.api.DedicatedServerModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeowMechanism implements DedicatedServerModInitializer {
    public static final String MOD_ID = "meowmechanism";
    public static final String NAME = "MeowMechanism";
    public static final String VERSION = "1.0.0";

    private Logger logger = LogManager.getLogger(NAME);

    @Override
    public void onInitializeServer() {
        logger.info("MeowMechanism " + VERSION + " is running!");
    }
}
