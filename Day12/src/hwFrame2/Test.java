package hwFrame2;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Test {

    static Set<Integer> keysPressed;



    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        keysPressed = Collections.synchronizedSet(new HashSet<>());
        KeyListener kl = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                final Integer keyCode = e.getKeyCode();

                keysPressed.add(keyCode);


                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (keysPressed.contains(keyCode)) {
                            System.out.println("pressed: " + keyCode + " size: " + keysPressed.size() + " hashCode: " + keyCode.hashCode());
                        }
                    }
                }).start();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.remove(e.getKeyCode());

            }

        };
        f.addKeyListener(kl);


    }

}
