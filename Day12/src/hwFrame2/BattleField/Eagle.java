package hwFrame2.BattleField;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Eagle extends SimpleBFObject {

    public Eagle(int x, int y){
        super(x, y);
        try{
            image = ImageIO.read(new File("Eagle.jpg").getAbsoluteFile());
        } catch (IOException e){
            System.out.println("There is no Eagle image.");
        }
    }
}

