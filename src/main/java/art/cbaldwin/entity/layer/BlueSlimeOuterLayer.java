package art.cbaldwin.entity.layer;

import art.cbaldwin.SimpleSlimes;
import art.cbaldwin.entity.custom.BlueSlimeEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class BlueSlimeOuterLayer extends GeoRenderLayer<BlueSlimeEntity> {
    private static final Identifier TEXTURE = new Identifier(SimpleSlimes.MOD_ID, "textures/entity/blue_slime.png");

    public BlueSlimeOuterLayer(GeoRenderer<BlueSlimeEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack poseStack, BlueSlimeEntity animatable, BakedGeoModel bakedModel, RenderLayer renderType,
                       VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick,
                       int packedLight, int packedOverlay) {

        RenderLayer outerLayer = RenderLayer.getEntityTranslucent(TEXTURE);
        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, outerLayer, bufferSource.getBuffer(outerLayer), partialTick, packedLight, 1, 1, 1, 1, .5f);
    }
}
