package art.cbaldwin.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SlimeEntity;

public class SimpleSlimeMoveControl extends MoveControl {
    private float targetYaw;
    private int ticksUntilJump;
    private final BlueSlimeEntity slime;
    private boolean jumpOften;

    public SimpleSlimeMoveControl(BlueSlimeEntity slime) {
        super(slime);
        this.slime = slime;
        this.targetYaw = 180.0F * slime.getYaw() / 3.1415927F;
    }

    public void look(float targetYaw, boolean jumpOften) {
        this.targetYaw = targetYaw;
        this.jumpOften = jumpOften;
    }

    public void lookAtEntity(Entity target) {

    }

    public void move(double speed) {
        this.speed = speed;
        this.state = MoveControl.State.MOVE_TO;
    }

    public void tick() {
        this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), this.targetYaw, 90.0F));
        this.entity.headYaw = this.entity.getYaw();
        this.entity.bodyYaw = this.entity.getYaw();
        if (this.state != MoveControl.State.MOVE_TO) {
            this.entity.setForwardSpeed(0.0F);
        } else {
            this.state = MoveControl.State.WAIT;
            if (this.entity.isOnGround()) {
                this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                if (this.ticksUntilJump-- <= 0) {
                    this.ticksUntilJump = this.slime.getTicksUntilNextJump();
                    if (this.jumpOften) {
                        this.ticksUntilJump /= 3;
                    }

                    this.slime.getJumpControl().setActive();
                    if (this.slime.makesJumpSound()) {
                        this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), this.slime.getJumpSoundPitch());
                    }
                } else {
                    this.slime.sidewaysSpeed = 0.0F;
                    this.slime.forwardSpeed = 0.0F;
                    this.entity.setMovementSpeed(0.0F);
                }
            } else {
                this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
            }

        }
    }
}