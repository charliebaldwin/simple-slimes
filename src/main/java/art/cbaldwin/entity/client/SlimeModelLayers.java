package art.cbaldwin.entity.client;
import art.cbaldwin.SimpleSlimes;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class SlimeModelLayers {
    public static final EntityModelLayer BLUE_SLIME = new EntityModelLayer(new Identifier(SimpleSlimes.MOD_ID, "blue_slime"), "main");
    public static final EntityModelLayer BLUE_SLIME_OUTER = new EntityModelLayer(new Identifier(SimpleSlimes.MOD_ID, "blue_slime"), "outer");
}
