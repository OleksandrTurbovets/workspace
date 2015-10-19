package hwFrame2.Tanks;


import hwFrame2.BattleField.Brick;
import hwFrame2.BattleField.ISimpleBFObject;
import hwFrame2.BattleField.BattleField;
import hwFrame2.BattleField.Eagle;
import hwFrame2.Direction;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class AbstractTank implements Tank {

    protected Image[] images;

    private int speed = 8;
    protected int movePath = 1;

    protected Direction direction;

    protected int x;
    protected int y;

    private boolean destroyed;

    protected BattleField bf;

    public AbstractTank(BattleField bf) {
        this(bf, 64, 448, Direction.RIGHT);
    }

    public AbstractTank(BattleField bf, int x, int y, Direction direction) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public void move() {

    }


    public void turn(Direction direction) {
        this.direction = direction;
    }

    public Bullet fire() {
        int bulletX = -100;
        int bulletY = -100;
        if (direction == Direction.UP) {
            bulletX = x + 25;
            bulletY = y - 20;
        } else if (direction == Direction.DOWN) {
            bulletX = x + 25;
            bulletY = y + 70;
        } else if (direction == Direction.LEFT) {
            bulletX = x - 20;
            bulletY = y + 25;
        } else if (direction == Direction.RIGHT) {
            bulletX = x + 70;
            bulletY = y + 25;
        }

        return new Bullet(bulletX, bulletY, direction, this);
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.drawImage(images[getDirection().getId()], getX(), getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });


        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    public Action moveToQuadrant(int v, int h) {
        String coordinates = bf.getQuadrantXY(v, h);
        int separator = coordinates.indexOf("_");
        int y = Integer.parseInt(coordinates.substring(0, separator));
        int x = Integer.parseInt(coordinates.substring(separator + 1));

        if (getY() < y) {
            while (getY() != y) {
                direction = Direction.DOWN;
                return Action.MOVE;
            }
        } else {
            while (getY() != y) {
                direction = Direction.UP;
                return Action.MOVE;
            }
        }
        if (getX() < x) {
            while (getX() != x) {
                direction = Direction.RIGHT;
                return Action.MOVE;
            }
        } else {
            while (getX() != x) {
                direction = Direction.LEFT;
                return Action.MOVE;
            }
        }
        return Action.NONE;
    }


    public Action moveRandom() {
        while (true) {

            if (bfCleaning()) {
                bulletIsBusy = true;
                return Action.FIRE;

            }

            String timeS = String.valueOf(System.currentTimeMillis());
            String timeL = timeS.substring(timeS.length() - 1);
            int random = Integer.parseInt(timeL);

            if (random == 1) {
                direction = Direction.UP;
                return Action.MOVE;
            } else if (random == 2) {
                direction = Direction.DOWN;
                return Action.MOVE;
            } else if (random == 3) {
                direction = Direction.LEFT;
                return Action.MOVE;
            } else if (random == 4) {
                direction = Direction.RIGHT;
                return Action.MOVE;
            } else if (random == 5) {
                direction = Direction.UP;
                return Action.MOVE;
            } else if (random == 6) {
                direction = Direction.DOWN;
                return Action.MOVE;
            } else if (random == 7) {
                direction = Direction.LEFT;
                return Action.MOVE;
            } else if (random == 8) {
                direction = Direction.RIGHT;
                return Action.MOVE;
            } else if (random == 9) {
                direction = Direction.LEFT;
                return Action.MOVE;
            } else if (random == 0) {
                direction = Direction.RIGHT;
                return Action.MOVE;
            }
            return Action.NONE;
        }
    }

    private boolean bulletIsBusy = false;

    public Action eagleKiller() {
        Action action = Action.NONE;
        if (bfCleaning()) {
            bulletIsBusy = true;
            return Action.FIRE;

        }
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                ISimpleBFObject bfObject = bf.scanQuadrant(i, k);
                if (bfObject instanceof Eagle) {
                    action = moveToQuadrant(i + 1, k + 1);
                }
            }
        }
        return action;
    }

    private boolean bfCleaning() {
        if (!bulletIsBusy){
            if (direction == Direction.DOWN) {
                for (int i = getY() / 64; i < 9; i++) {
                    if (bf.scanQuadrant(i, getX() / 64) instanceof Brick || bf.scanQuadrant(i, getX() / 64) instanceof Eagle) {
                        return true;
                    }
                }
            } else if (direction == Direction.RIGHT){
                for (int i = getX() / 64; i < 9; i++){
                    if (bf.scanQuadrant(getY() / 64, i) instanceof Brick || bf.scanQuadrant(getY() / 64, i) instanceof Eagle){
                        return true;
                    }
                }
            } else if (direction == Direction.UP){
                for (int i = getY() / 64; i >= 0; i--){
                    if (bf.scanQuadrant(i, getX() / 64) instanceof Brick || bf.scanQuadrant(i, getX() / 64) instanceof Eagle) {
                        return true;
                    }
                }
            } else if (direction == Direction.LEFT){
                for (int i = getX() / 64; i >= 0; i--){
                    if (bf.scanQuadrant(getY() / 64, i) instanceof Brick || bf.scanQuadrant(getY() / 64, i) instanceof Eagle){
                        return true;
                    }
                }
            }



        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bulletIsBusy = false;
            }
        }).start();

        return false;
    }


    public Action defenderKiller() {
        int y = findDefender()[0];
        int x = findDefender()[1];
        Action action = moveToQuadrant(y, x);
        return action;
    }

    public int[] findDefender() {
        int[] location = new int[2];
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                String tank = bf.getTanksLocation(i, k);
                if (tank == "T34") {
                    location[0] = i + 1;
                    location[1] = k + 1;
                }
            }
        }
        return location;
    }


    public void updateSpeed(int speed) {
        this.speed += speed;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getMovePath() {
        return movePath;
    }

    @Override
    public void setActions(Object actions) {

    }
}
