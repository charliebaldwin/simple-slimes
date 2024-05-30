package art.cbaldwin;

import art.cbaldwin.entity.SlimeEntities;
import art.cbaldwin.entity.custom.BlueSlimeEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleSlimes implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("simple-slimes");
	public static String MOD_ID = "simple-slimes";


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing SimpleSlimes...");


		FabricDefaultAttributeRegistry.register(SlimeEntities.BLUE_SLIME, BlueSlimeEntity.createBlueSlimeAttributes());

	}
}