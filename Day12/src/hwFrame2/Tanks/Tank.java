package hwFrame2.Tanks;

import hwFrame2.Direction;
import hwFrame2.Interfaces.Destroyable;
import hwFrame2.Interfaces.Drawable;

public interface Tank extends Drawable, Destroyable {

    public Action setUp();
    public void move();
    public Bullet fire();
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public Direction getDirection();
    public void updateX(int x);
    public void updateY(int y);
    public int getSpeed();
    public int getMovePath();
    public void shareTanksLocation(int x, int y);
    public void turn(Direction direction);


    public Action moveToQuadrant(int v, int h);
    public void setActions(Object actions);
}
