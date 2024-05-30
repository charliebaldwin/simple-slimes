// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.example.mod;
   
public class BlueSlimeModel extends EntityModel<Entity> {
	private final ModelPart blue_slime;
	private final ModelPart body;
	private final ModelPart core;
	private final ModelPart eye0;
	private final ModelPart eye1;
	private final ModelPart mouth;
	public BlueSlimeModel(ModelPart root) {
		this.blue_slime = root.getChild("blue_slime");
		this.body = root.getChild("body");
		this.core = root.getChild("core");
		this.eye0 = root.getChild("eye0");
		this.eye1 = root.getChild("eye1");
		this.mouth = root.getChild("mouth");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData blue_slime = modelPartData.addChild("blue_slime", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = blue_slime.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData core = blue_slime.addChild("core", ModelPartBuilder.create().uv(0, 16).cuboid(-3.0F, -7.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData eye0 = core.addChild("eye0", ModelPartBuilder.create().uv(32, 0).cuboid(-3.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData eye1 = core.addChild("eye1", ModelPartBuilder.create().uv(32, 4).cuboid(1.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData mouth = core.addChild("mouth", ModelPartBuilder.create().uv(32, 8).cuboid(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		blue_slime.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}