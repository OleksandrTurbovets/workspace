package hwFrame2.BattleField;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Water extends ConstantBFObject {

    public Water(int x, int y){
        super(x, y);
        try{
        image = ImageIO.read(new File("WaterBIG.jpg").getAbsoluteFile());
        } catch (IOException e){
            System.out.println("There is no Water image.");
        }
    }
}

