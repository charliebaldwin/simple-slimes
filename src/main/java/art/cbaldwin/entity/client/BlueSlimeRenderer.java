package art.cbaldwin.entity.client;

import art.cbaldwin.SimpleSlimes;
import art.cbaldwin.entity.custom.BlueSlimeEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import art.cbaldwin.entity.client.BlueSlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlueSlimeRenderer extends MobEntityRenderer<BlueSlimeEntity, BlueSlimeModel<BlueSlimeEntity>> {

    private static final Identifier TEXTURE = new Identifier(SimpleSlimes.MOD_ID, "textures/entity/blue_slime.png");

    public BlueSlimeRenderer(EntityRendererFactory.Context context) {
        super(context, new BlueSlimeModel<>(context.getPart(SlimeModelLayers.BLUE_SLIME)), 0.6f);
        this.addFeature(new BlueSlimeOverlayFeatureRenderer<>(this, context.getModelLoader()));

    }

    @Override
    public Identifier getTexture(BlueSlimeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BlueSlimeEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.35F * mobEntity.getSlimeSize();

        float s = mobEntity.getSlimeSize();
        //s = 1f;

        if (mobEntity.isBaby()) {
            //matrixStack.scale(0.5f, 0.5f, 0.5f);
            matrixStack.scale(s, s, s);
        } else {
            matrixStack.scale(s, s, s);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
