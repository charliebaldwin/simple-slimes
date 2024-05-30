// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 4.10.1
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class BlueSlimeModelAnimation {
	public static final AnimationDefinition jump_up = AnimationDefinition.Builder.withLength(0.5833F)
		.addAnimation("blue_slime", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.9575F, 1.085F, 0.9575F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.scaleVec(1.3F, 0.5F, 1.3F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.1667F, KeyframeAnimations.scaleVec(0.8F, 1.4F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.scaleVec(0.9575F, 1.085F, 0.9575F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition jump_fall = AnimationDefinition.Builder.withLength(0.5F)
		.addAnimation("blue_slime", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2917F, KeyframeAnimations.scaleVec(0.9208F, 1.1585F, 0.9208F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.scaleVec(0.8F, 1.4F, 0.8F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition jump_land = AnimationDefinition.Builder.withLength(0.5F)
		.addAnimation("blue_slime", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.8F, 1.4F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0417F, KeyframeAnimations.scaleVec(1.2F, 0.6F, 1.2F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.scaleVec(0.95F, 1.05F, 0.95F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4167F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(1.5F).looping()
		.addAnimation("blue_slime", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.scaleVec(1.05F, 0.95F, 1.05F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();
}