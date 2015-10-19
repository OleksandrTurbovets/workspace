package hwFrame2;

import hwFrame2.Tanks.Action;
import hwFrame2.Tanks.Tank;

import java.util.List;

public class ActionList{

    private Tank tank;
    private Action action;
    private Direction direction;
    private boolean actionIsDone;

    public ActionList() {

    }

    public ActionList(Tank tank, Action action, Direction direction) {
        this.tank = tank;
        this.action = action;
        this.direction = direction;
        actionIsDone = false;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isActionIsDone() {
        return actionIsDone;
    }

    public void setActionIsDone(boolean actionIsDone) {
        this.actionIsDone = actionIsDone;

    }



}
