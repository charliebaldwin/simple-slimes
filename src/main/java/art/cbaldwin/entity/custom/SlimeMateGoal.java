package art.cbaldwin.entity.custom;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.AnimalEntity;

public class SlimeMateGoal extends AnimalMateGoal {
    private final BlueSlimeEntity slime;
    private float targetYaw;
    private int timer;
    private float currentYaw;

    public SlimeMateGoal(AnimalEntity animal, double speed, BlueSlimeEntity slime) {
        super(animal, speed);
        this.slime = slime;
        currentYaw = 0f;
    }

    @Override
    public void tick() {
        /*this.animal.getLookControl().lookAt(this.mate, 10.0F, (float)this.animal.getMaxLookPitchChange());
        this.animal.getNavigation().startMovingTo(this.mate, this.speed);*/

        this.slime.lookAtEntity(this.mate, 10.0F, 10.0F);
        SimpleSlimeMoveControl moveControl = this.slime.getSlimeMoveControl();
        if (moveControl instanceof SimpleSlimeMoveControl ) {
            moveControl.look(this.slime.getYaw(), true);
        }

        ++this.timer;
        if (this.timer >= this.getTickCount(60) && this.animal.squaredDistanceTo(this.mate) < 9.0) {
            this.breed();
        }

    }


}
