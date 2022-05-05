package al.yn.meowmechanism;

import net.fabricmc.api.DedicatedServerModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeowMechanism implements DedicatedServerModInitializer {
    private Logger logger = LogManager.getLogger("MeowMechanism");

    @Override
    public void onInitializeServer() {
        logger.info("MeowMechanism is running!");
    }
}
