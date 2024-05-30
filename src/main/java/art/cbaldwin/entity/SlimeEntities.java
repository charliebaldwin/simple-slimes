package art.cbaldwin.entity;

import art.cbaldwin.SimpleSlimes;
import art.cbaldwin.entity.custom.BlueSlimeEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SlimeEntities {

    public static final EntityType<BlueSlimeEntity> BLUE_SLIME = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SimpleSlimes.MOD_ID, "blue_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BlueSlimeEntity::new)
                    .dimensions(EntityDimensions.fixed(.5f, .5f))
                    .build());
}
