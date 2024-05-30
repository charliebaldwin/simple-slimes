package art.cbaldwin.entity.animation;

import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.Transformation;


public class SlimeAnimations {
    public static final Animation JUMP_UP = Animation.Builder.create(0.5834334f)
            .addBoneAnimation("blue_slime",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0.96f, 1.09f, 0.96f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.041676664f, AnimationHelper.createScalingVector(1.3f, 0.5f, 1.3f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.16766666f, AnimationHelper.createScalingVector(0.8f, 1.4f, 0.8f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.375f, AnimationHelper.createScalingVector(0.96f, 1.09f, 0.96f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5834334f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation JUMP_FALL = Animation.Builder.create(0.5f)
            .addBoneAnimation("blue_slime",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.2916767f, AnimationHelper.createScalingVector(0.92f, 1.16f, 0.92f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createScalingVector(0.8f, 1.4f, 0.8f),
                                    Transformation.Interpolations.CUBIC))).build();
    public static final Animation JUMP_LAND = Animation.Builder.create(0.5f)
            .addBoneAnimation("blue_slime",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0.8f, 1.4f, 0.8f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.041676664f, AnimationHelper.createScalingVector(1.2f, 0.6f, 1.2f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createScalingVector(0.95f, 1.05f, 0.95f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.4167667f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.CUBIC))).build();
    public static final Animation IDLE = Animation.Builder.create(1.5f).looping()
            .addBoneAnimation("blue_slime",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createScalingVector(1.05f, 0.95f, 1.05f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.CUBIC))).build();
}
