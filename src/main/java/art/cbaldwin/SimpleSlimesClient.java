package art.cbaldwin;

import art.cbaldwin.entity.SlimeEntities;
import art.cbaldwin.entity.client.BlueSlimeModel;
import art.cbaldwin.entity.client.BlueSlimeRenderer;
import art.cbaldwin.entity.client.SlimeModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SimpleSlimesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(SlimeModelLayers.BLUE_SLIME, BlueSlimeModel::getTexturedModelDataInner);
        EntityModelLayerRegistry.registerModelLayer(SlimeModelLayers.BLUE_SLIME_OUTER, BlueSlimeModel::getTexturedModelDataOuter);
        EntityRendererRegistry.register(SlimeEntities.BLUE_SLIME, BlueSlimeRenderer::new);
    }
}
