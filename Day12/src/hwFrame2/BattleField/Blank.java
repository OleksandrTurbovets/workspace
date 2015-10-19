package hwFrame2.BattleField;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Blank extends SimpleBFObject {

    public Blank(int x, int y){
        super(x, y);
        try{
            image = ImageIO.read(new File("BlankBIG.jpg").getAbsoluteFile());
        } catch (IOException e){
            System.out.println("There is no Blank image.");
        }

    }
}

