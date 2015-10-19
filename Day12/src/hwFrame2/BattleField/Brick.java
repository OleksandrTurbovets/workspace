package hwFrame2.BattleField;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Brick extends SimpleBFObject {

    public Brick(int x, int y){
        super(x, y);
        try{
            image = ImageIO.read(new File("BrickBIG.jpg").getAbsoluteFile());
        } catch (IOException e){
            System.out.println("There is no Brick image.");
        }
    }
}
