package hwFrame2;

import hwFrame2.Tanks.Action;
import hwFrame2.Tanks.Tank;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Write {

    private List<String> actionTank;


    public Write(){
        actionTank = new ArrayList<>();
    }

    public void rememberActionTank(Action a, Tank t) {
        String tmp = String.valueOf(a) + ", " + t.getClass().getSimpleName() + ", " + t.getDirection();
        System.out.println("Writing to file: " + tmp);
        actionTank.add(tmp);
    }

    public void toFile() throws IOException {
        Path pathToFile = Paths.get("actionTank.txt");
        Files.write(pathToFile, actionTank, StandardCharsets.UTF_8);
        actionTank = new ArrayList<>();
    }
}
