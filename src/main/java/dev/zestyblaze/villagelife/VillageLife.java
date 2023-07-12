package dev.zestyblaze.villagelife;

import dev.zestyblaze.villagelife.registry.ModEntities;
import dev.zestyblaze.villagelife.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VillageLife implements ModInitializer {
	public static final String MOD_ID = "villagelife";
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		ModEntities.register();
		ModItems.register();
	}
}
