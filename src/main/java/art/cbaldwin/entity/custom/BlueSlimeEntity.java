package art.cbaldwin.entity.custom;

import art.cbaldwin.SimpleSlimes;
import art.cbaldwin.entity.SlimeEntities;
import art.cbaldwin.entity.animation.SlimeAnimations;
import art.cbaldwin.entity.client.BlueSlimeModel;
import com.google.common.annotations.VisibleForTesting;
import net.minecraft.client.render.entity.animation.Animation;
//import net.minecraft.entity.AnimationState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.xml.crypto.Data;
import java.util.UUID;

public class BlueSlimeEntity extends AnimalEntity implements GeoEntity {
/*   private static final TrackedData<Integer> ANGER_TIME;
   private static final UniformIntProvider ANGER_TIME_RANGE;
   @Nullable
   private UUID angryAt;*/
    public float slimeSize;
    private static final TrackedData<Float> SLIME_SIZE = DataTracker.registerData(BlueSlimeEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private boolean onGroundLastTick;

    /*public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState jumpUpAnimationState = new AnimationState();
    public final AnimationState jumpFallAnimationState = new AnimationState();
    public final AnimationState jumpLandAnimationState = new AnimationState();*/

   // public Animation currentAnimation = SlimeAnimations.JUMP_UP;

    public static final RawAnimation ANIM_IDLE = RawAnimation.begin().thenLoop("idle");
    public static final RawAnimation ANIM_JUMP_UP = RawAnimation.begin().thenPlay("jump_up");
    public static final RawAnimation ANIM_JUMP_FALL = RawAnimation.begin().thenPlayAndHold("jump_fall");
    public static final RawAnimation ANIM_JUMP_LAND = RawAnimation.begin().thenPlay("jump_land");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public BlueSlimeEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super((EntityType<? extends AnimalEntity>) entityType, world);
        this.moveControl = new SimpleSlimeMoveControl(this);


        /*if (slimeSize == 0) {
            slimeSize = Random.create().nextFloat() + 0.7f;
            setSlimeSize(slimeSize);
        }*/
    }
    /*static {
        SLIME_SIZE = DataTracker.registerData(BlueSlimeEntity.class, TrackedDataHandlerRegistry.FLOAT);
    }*/
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SLIME_SIZE, (float)1);
    }
    public float getSlimeSize() {
        //return slimeSize;
        return (float) this.dataTracker.get(SLIME_SIZE);
    }

    @VisibleForTesting
    public void setSlimeSize(float newSize) {
        this.dataTracker.set(SLIME_SIZE, newSize);
        this.slimeSize = newSize;
        this.refreshPosition();
        this.calculateDimensions();
        /*NbtCompound nbtData = new NbtCompound();
        nbtData.putFloat("simple-slimes.slime_size", newSize);
        this.saveNbt(nbtData);*/
    }

    @Override
    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setSlimeSize(Random.create().nextFloat() + 0.7f);
        SimpleSlimes.LOGGER.info("initialize size: {}", this.getSlimeSize());
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putFloat("slime_size", (float)this.getSlimeSize());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setSlimeSize(nbt.getFloat("slime_size"));
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return super.getDimensions(pose).scaled(1f * (float)this.getSlimeSize());
    }

    @Override
    public void tick() {
        super.tick();
        /*if (this.getWorld().isClient()){
            idleAnimationState.startIfNotRunning(this.age);
            jumpUpAnimationState.startIfNotRunning(this.age);

        }*/
    }

    public void playJumpUp() {
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SlimeMateGoal(this, 1, this));
        this.goalSelector.add(2, new SlimeWanderGoal(this));

       // this.targetSelector.add(3, new RevengeGoal(this, new Class[0]).setGroupRevenge(new Class[0]));
    }

    public static DefaultAttributeContainer.Builder createBlueSlimeAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .3f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.RAW_COPPER);
    }

    public SimpleSlimeMoveControl getSlimeMoveControl() {
        return (SimpleSlimeMoveControl) this.moveControl;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        float combinedSize = this.getSlimeSize() + ((BlueSlimeEntity)entity).getSlimeSize();
        SimpleSlimes.LOGGER.info("parent 1 size: {}", this.getSlimeSize());
        SimpleSlimes.LOGGER.info("parent 2 size: {}", ((BlueSlimeEntity)entity).getSlimeSize());
        SimpleSlimes.LOGGER.info("combined size: {}", combinedSize);

        BlueSlimeEntity child = SlimeEntities.BLUE_SLIME.create(world);
        if (child != null) {
            child.setSlimeSize(combinedSize);
            this.discard();
            entity.discard();

            child.setBaby(false);
        }

        SimpleSlimes.LOGGER.info("child size: {}", child.getSlimeSize());
        return child;
    }

    protected int getTicksUntilNextJump() {
        return this.random.nextInt(50) + 30;
    }


    // Sounds
    protected float getSoundVolume() {
        return 0.4F * this.getSlimeSize();
    }
    protected boolean makesJumpSound() {
        return this.getSlimeSize() > 0;
    }
    protected SoundEvent getJumpSound() {
        return this.getSlimeSize() < 0.7 ? SoundEvents.ENTITY_SLIME_JUMP_SMALL : SoundEvents.ENTITY_SLIME_JUMP;
    }
    float getJumpSoundPitch() {
        float f = this.getSlimeSize() < 0.7 ? 1.4F : 0.8F;
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
    }


    // Geckolib
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 1, this::slimeAnimController));
    }
    protected <E extends GeoAnimatable> PlayState slimeAnimController(final AnimationState<E> state) {
        /*if (getSlimeMoveControl().isGrounded()){
            state.setAnimation(RawAnimation.begin().thenLoop("idle"));
        }*/

        if (!getSlimeMoveControl().isFalling() && !getSlimeMoveControl().isGrounded()) {
            state.setAnimation(RawAnimation.begin().thenPlayAndHold("jump_up"));
        }

        if (state.isCurrentAnimation(RawAnimation.begin().thenPlayAndHold("jump_up")) && getSlimeMoveControl().isFalling()) {
            SimpleSlimes.LOGGER.info("playing jump_fall");
            state.setAnimation(RawAnimation.begin().thenPlayAndHold("jump_fall"));
        }

        if ((state.isCurrentAnimation(RawAnimation.begin().thenPlayAndHold("jump_up")) || state.isCurrentAnimation(RawAnimation.begin().thenPlayAndHold("jump_fall")))
            && getSlimeMoveControl().isGrounded()) {
            SimpleSlimes.LOGGER.info("playing jump_land");
            return state.setAndContinue(RawAnimation.begin().thenPlay("jump_land").thenLoop("idle"));
        }



        return PlayState.CONTINUE;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}
