package hwFrame2.BattleField;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rock extends SimpleBFObject {

    public Rock(int x, int y){
        super(x, y);
        try{
        image = ImageIO.read(new File("RockBIG.jpg").getAbsoluteFile());
    } catch (IOException e){
        System.out.println("There is no Rock image.");
    }
    }
}

