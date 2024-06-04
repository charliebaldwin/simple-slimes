package art.cbaldwin.entity.client;

import art.cbaldwin.SimpleSlimes;
import art.cbaldwin.entity.custom.BlueSlimeEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class BlueSlimeGeoModel extends GeoModel<BlueSlimeEntity> {

    @Override
    public Identifier getModelResource(BlueSlimeEntity animatable) {
        return new Identifier(SimpleSlimes.MOD_ID, "geo/blue_slime.geo.json");
    }

    @Override
    public Identifier getTextureResource(BlueSlimeEntity animatable) {
        return new Identifier(SimpleSlimes.MOD_ID, "textures/entity/blue_slime.png");
    }

    @Override
    public Identifier getAnimationResource(BlueSlimeEntity animatable) {
        return new Identifier(SimpleSlimes.MOD_ID, "animations/blue_slime.animation.json");
    }

    @Override
    public RenderLayer getRenderType(BlueSlimeEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(texture);
    }
}
