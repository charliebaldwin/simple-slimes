package art.cbaldwin.entity.client;

import art.cbaldwin.SimpleSlimes;
import art.cbaldwin.entity.custom.BlueSlimeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BlueSlimeGeoRenderer extends GeoEntityRenderer<BlueSlimeEntity> {

    public BlueSlimeGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BlueSlimeGeoModel());
    }

    @Override
    public Identifier getTextureLocation(BlueSlimeEntity animatable) {
        return new Identifier(SimpleSlimes.MOD_ID, "textures/entity/blue_slime.png");
    }

    @Override
    public void render(BlueSlimeEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        this.shadowRadius = 0.75F * (float)Math.sqrt(entity.getSlimeSize());

        float s = entity.getSlimeSize();
        //s = 1f;

        if (entity.isBaby()) {
            //matrixStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.scale(s, s, s);
        } else {
            poseStack.scale(s, s, s);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
