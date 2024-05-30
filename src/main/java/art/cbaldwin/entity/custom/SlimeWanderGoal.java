package art.cbaldwin.entity.custom;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.SlimeEntity;

import java.util.EnumSet;

public class SlimeWanderGoal extends Goal {
    private final BlueSlimeEntity slime;
    private float targetYaw;
    private int timer;

    public SlimeWanderGoal(BlueSlimeEntity slime) {
        this.slime = slime;
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE, Goal.Control.LOOK));

    }

    @Override
    public boolean canStart() {
        return !this.slime.hasVehicle();
    }

    public void tick() {
        if (--this.timer <= 0) {
            this.timer = this.getTickCount(40 + this.slime.getRandom().nextInt(60));
            this.targetYaw = targetYaw + (-90 + (float)this.slime.getRandom().nextInt(180));
        }

        SimpleSlimeMoveControl moveControl = this.slime.getSlimeMoveControl();
        if (moveControl instanceof SimpleSlimeMoveControl) {
            moveControl.look(this.targetYaw, false);
            moveControl.move(1.0);
        }

    }
}
