package hwFrame2.BattleField;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class SimpleBFObject implements ISimpleBFObject {

    // current position on BF
    private int x;
    private int y;

    protected Image image;

    private boolean isDestroyed = false;

    public SimpleBFObject(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed){
            if (image != null){
                int imageSize = image.getHeight(new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
                if (imageSize > 64){
                    g.drawImage(image, getX(), getY(), getX() + 64, getY() + 64, getX(), getY(), getX() + 64, getY() + 64, new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });
                } else {
                    g.drawImage(image, getX(), getY(), new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });
                }

            }
        }
    }


    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
