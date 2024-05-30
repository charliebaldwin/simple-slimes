package art.cbaldwin.entity.custom;

import art.cbaldwin.SimpleSlimes;
import art.cbaldwin.entity.SlimeEntities;
import art.cbaldwin.entity.client.BlueSlimeModel;
import net.minecraft.entity.EntityType;
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
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BlueSlimeEntity extends AnimalEntity {
/*   private static final TrackedData<Integer> ANGER_TIME;
   private static final UniformIntProvider ANGER_TIME_RANGE;
   @Nullable
   private UUID angryAt;*/
    public float slimeSize;
    private static final TrackedData<Float> SLIME_SIZE;
    private boolean onGroundLastTick;

    public BlueSlimeEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        slimeSize = Random.create().nextFloat() + 0.7f;
        this.moveControl = new SimpleSlimeMoveControl(this);
        setSlimeSize(Random.create().nextFloat() + 0.7f);
        super.calculateDimensions();
    }

    static {
        SLIME_SIZE = DataTracker.registerData(BlueSlimeEntity.class, TrackedDataHandlerRegistry.FLOAT);
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

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SLIME_SIZE, 1f);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.RAW_COPPER);
    }

    public float getSlimeSize() {
        //return slimeSize;
        return (float) this.dataTracker.get(SLIME_SIZE);
    }

    public void setSlimeSize(float newSize) {
        slimeSize = newSize;
        this.dataTracker.set(SLIME_SIZE, newSize);

    }

    public SimpleSlimeMoveControl getSlimeMoveControl() {
        return (SimpleSlimeMoveControl) this.moveControl;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        float combinedSize = slimeSize + ((BlueSlimeEntity)entity).getSlimeSize();
        SimpleSlimes.LOGGER.info("parent 1 size: {}", slimeSize);
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
        return 0.4F * slimeSize;
    }
    protected boolean makesJumpSound() {
        return slimeSize > 0;
    }
    protected SoundEvent getJumpSound() {
        return slimeSize < 0.7 ? SoundEvents.ENTITY_SLIME_JUMP_SMALL : SoundEvents.ENTITY_SLIME_JUMP;
    }
    float getJumpSoundPitch() {
        float f = slimeSize < 0.7 ? 1.4F : 0.8F;
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
    }

/*
   protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGER_TIME, 0);
    }

    public int getAngerTime() {
        return (Integer)this.dataTracker.get(ANGER_TIME);
    }

    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }

    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }*/
}
