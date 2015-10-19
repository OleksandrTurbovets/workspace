package hwFrame2.Tanks;

import hwFrame2.BattleField.BattleField;
import hwFrame2.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class T34 extends AbstractTank {

    public T34(BattleField bf) {
        super(bf);
        setImages();
        shareTanksLocation(super.getX(), super.getY());
    }

    public T34(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        setImages();
        shareTanksLocation(x, y);
    }

    public void shareTanksLocation(int x, int y) {
        bf.setTanksLocation(x / 64, y / 64, "T34");
    }

    private void setImages() {
        images = new Image[4];
        try {
            images[0] = ImageIO.read(new File("T34_UP.png").getAbsoluteFile());
            images[1] = ImageIO.read(new File("T34_DOWN.png").getAbsoluteFile());
            images[2] = ImageIO.read(new File("T34_LEFT.png").getAbsoluteFile());
            images[3] = ImageIO.read(new File("T34_RIGHT.png").getAbsoluteFile());
        } catch (IOException e) {
            throw new IllegalStateException("Can't find tank images.");
        }
    }

    @Override
    public Action setUp() {
        return null;
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
}
