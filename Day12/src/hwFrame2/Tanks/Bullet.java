package hwFrame2.Tanks;

import hwFrame2.Direction;
import hwFrame2.Interfaces.Destroyable;
import hwFrame2.Interfaces.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Bullet implements Drawable,Destroyable {

	private int speed = 4;
	private int x;
	private int y;
	private Direction direction;
	private Tank tank;

    private boolean destroyed;

	protected Image image;

	public Bullet(int x, int y, Direction direction, Tank tank) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	    this.destroyed = false;
		this.tank = tank;

		try {
			image = ImageIO.read(new File("yadro.png").getAbsoluteFile());
		} catch (IOException e) {
			System.out.println("There is no Yadro image.");
		}
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

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public void destroy() {

		try {
			image = ImageIO.read(new File("Explosion.png").getAbsoluteFile());
		} catch (IOException e) {
			System.out.println("There is no Yadro image.");
		}

		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		destroyed = true;
		setX(-100);
		setY(-100);


	}

	@Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
	public void draw(Graphics g) {
        if (!destroyed){
			if (image != null){
				g.drawImage(image, this.x, this.y, new ImageObserver() {
					@Override
					public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
						return false;
					}
				});
			}
        }
	}
}
