package hwFrame2;


import hwFrame2.BattleField.*;
import hwFrame2.Interfaces.Drawable;
import hwFrame2.Tanks.*;
import hwFrame2.Tanks.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActionField extends JPanel {

    private List<ActionList> actionList;

    private ExecutorService executorDefender;

    private JFrame frame;
    private JPanel sPanel;
    private JPanel goPanel;
    private boolean canStart = false;
    private int T34Killed = 0;

    private Write write;
    private boolean replay = false;

    private static final long serialVersionUID = 1L;

    private BattleField battleField;
    private Tank defender;
    private Tank aggressor;
    private List<Bullet> bullets;

    public ActionField() throws Exception {


        battleField = new BattleField();
        defender = new T34(battleField, 64, 448, Direction.UP);
        aggressor = new Bt7(battleField, 448, 64, Direction.DOWN);
        bullets = new ArrayList<>();
        bullets.add(new Bullet(-100, -100, Direction.UP, defender));

        actionList = new ArrayList<>();
        actionList.add(new ActionList(defender, Action.NONE, Direction.UP));

        executorDefender = Executors.newFixedThreadPool(2);

        write = new Write();


        frame = new JFrame("BATTLE FIELD, DAY 12");
        frame.setLocation(600, 100);
        frame.setMinimumSize(new Dimension(battleField.getBfWidth(), battleField.getBfHeight() + 20));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setFocusable(true);

        createStartMenu();
        frame.getContentPane().add(sPanel);


        frame.pack();
        frame.setVisible(true);

        new Thread(repaintActionField()).start();

        frame.addKeyListener(keyListener());

    }

    public void createStartMenu() throws Exception {
        sPanel = new JPanel();
        sPanel.setLayout(new GridBagLayout());

        JButton btnTiger = new JButton("Tiger");
        JButton btnBt7 = new JButton("Bt7");
        JButton btnRePlay = new JButton("RePlay");

        sPanel.add(btnTiger);
        sPanel.add(btnBt7);
//        sPanel.add(btnRePlay);

        btnTiger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggressor = new Tiger(battleField, 448, 64, Direction.DOWN);
                replay = false;
                createAll();
            }

        });

        btnBt7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggressor = new Bt7(battleField, 448, 64, Direction.DOWN);
                replay = false;
                createAll();

            }
        });

        btnRePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aggressor == null) {
                    throw new IllegalStateException("There is nothing to replay");
                } else {
                    replay = true;
                    createAll();
                }
            }
        });

    }

    public void createGameOverMenu() {
        goPanel = new JPanel();
        goPanel.setLayout(new GridBagLayout());

        JFormattedTextField total = new JFormattedTextField();
        total.setColumns(15);
        total.setValue("Game over: " + T34Killed);
        goPanel.add(total);

        JButton btnRestart = new JButton("Try Again");
        goPanel.add(btnRestart);

        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canStart = false;
                showStartPanel();

            }
        });


    }


    public void createAll() {

        frame.getContentPane().removeAll();
        frame.getContentPane().add(this);

        frame.pack();
        frame.repaint();
        canStart = true;

    }

    public void showStartPanel() {

        frame.getContentPane().removeAll();
        frame.getContentPane().add(sPanel);

        frame.pack();
        frame.repaint();

    }

    public void showGameOverPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(goPanel);

        frame.pack();
        frame.repaint();

    }

    private KeyAdapter keyListener() {

        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!defender.isDestroyed()) {
                    if ((e.getKeyCode() == KeyEvent.VK_LEFT)) {
                        if (defender.getX() % 64 == 0 && defender.getY() % 64 == 0) {
                            if (defender.getDirection() == Direction.LEFT) {
                                System.out.println("Left");
                                actionList.add(new ActionList(defender, Action.MOVE, Direction.LEFT));
                            } else {
                                defender.turn(Direction.LEFT);
                            }
                        }
                    } else if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
                        if (defender.getX() % 64 == 0 && defender.getY() % 64 == 0) {
                            if (defender.getDirection() == Direction.RIGHT) {
                                System.out.println("Right");
                                actionList.add(new ActionList(defender, Action.MOVE, Direction.RIGHT));
                            } else {
                                defender.turn(Direction.RIGHT);
                            }
                        }
                    } else if ((e.getKeyCode() == KeyEvent.VK_UP)) {
                        if (defender.getX() % 64 == 0 && defender.getY() % 64 == 0) {
                            if (defender.getDirection() == Direction.UP) {
                                System.out.println("UP");
                                actionList.add(new ActionList(defender, Action.MOVE, Direction.UP));
                            } else {
                                defender.turn(Direction.UP);
                            }
                        }
                    } else if ((e.getKeyCode() == KeyEvent.VK_DOWN)) {
                        if (defender.getX() % 64 == 0 && defender.getY() % 64 == 0) {
                            if (defender.getDirection() == Direction.DOWN) {
                                System.out.println("Down");
                                actionList.add(new ActionList(defender, Action.MOVE, Direction.DOWN));
                            } else {
                                defender.turn(Direction.DOWN);
                            }
                        }
                    } else if ((e.getKeyCode() == KeyEvent.VK_SPACE)) {
                        System.out.println("FIRE");
                        actionList.add(new ActionList(defender, Action.FIRE, Direction.NONE));

                    }
                }

            }


        };
    }

    private void aggressorAction() throws Exception {

        if (!aggressor.isDestroyed()) {
            if (aggressor.getX() % 64 == 0 && aggressor.getY() % 64 == 0) {
                actionList.add(new ActionList(aggressor, aggressor.setUp(), aggressor.getDirection()));
                Thread.sleep(25);

            }

        }

    }


    private Runnable repaintActionField() throws Exception {
        return new Runnable() {
            @Override
            public void run() {
                while (true) {
                    frame.repaint();
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        battleField.draw(g);
        defender.draw(g);
        aggressor.draw(g);
        battleField.drawWater(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }


    }


    public void runTheGame() throws Exception {

        while (!canStart) {
            System.out.println("Make your choice, I am waiting!!! ");
            Thread.sleep(2000);
        }

        while (canStart) {


            if (!defender.isDestroyed() && !aggressor.isDestroyed()) {
                aggressorAction();
                if (actionList.get(actionList.size() - 1).isActionIsDone() == false) {
                    processAction();
                }
                Thread.sleep(1);
            } else {
                T34Killed++;
                canStart = false;
            }
        }

        canStart = true;

        battleField = new BattleField();
        defender = new T34(battleField);
        if (aggressor instanceof Tiger) {
            aggressor = new Tiger(battleField, 448, 64, Direction.DOWN);
        } else {
            aggressor = new Bt7(battleField, 448, 64, Direction.DOWN);
        }

        createGameOverMenu();
        showGameOverPanel();


        while (canStart) {
            System.out.println("Game over, try again!");
            Thread.sleep(2000);
        }


        runTheGame();
    }

    private void processAction() throws Exception {

        ActionList tmpAL = actionList.get(actionList.size() - 1);

        if (tmpAL.getAction() == Action.MOVE) {
            tmpAL.getTank().turn(tmpAL.getDirection());
            executorDefender.execute(processMove(tmpAL.getTank()));
        } else if (tmpAL.getAction() == Action.FIRE) {
            new Thread(processFire(tmpAL.getTank(), tmpAL.getTank().fire())).start();

        }

        tmpAL.setActionIsDone(true);

        actionList.set(actionList.size() - 1, tmpAL);

    }


    boolean checkLimits(Tank tank, Direction direction) {

        if ((direction == Direction.UP && tank.getY() == 0) || (direction == Direction.DOWN && tank.getY() >= 512)
                || (direction == Direction.LEFT && tank.getX() == 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
            System.out.println("[illegal move] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            return true;
        }
        return false;
    }

    public Runnable processMove(Tank tank) throws Exception {
        return new Runnable() {
            @Override
            public synchronized void run() {
                Direction direction = tank.getDirection();
                int step = 1;


                for (int i = 0; i < tank.getMovePath(); i++) {


                    final int[] covered = {0};

                    String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
                    int v = Integer.parseInt(tankQuadrant.split("_")[0]);
                    int h = Integer.parseInt(tankQuadrant.split("_")[1]);

                    if (checkLimits(tank, direction)) {
                        return;
                    }

                    // check next quadrant before move
                    if (direction == Direction.UP) {
                        v--;
                    } else if (direction == Direction.DOWN) {
                        v++;
                    } else if (direction == Direction.RIGHT) {
                        h++;
                    } else if (direction == Direction.LEFT) {
                        h--;
                    }
                    ISimpleBFObject bfObject = battleField.scanQuadrant(v, h);
                    if (!(bfObject instanceof Blank) && !bfObject.isDestroyed() && !(bfObject instanceof Water)) {
                        System.out.println("[illegal move] direction: " + direction + " tankX: " + tank.getX() +
                                ", tankY: " + tank.getY());

                        return;
                    }


                    // process move

                    while (covered[0] < 64) {


                        if (direction == Direction.UP) {
                            tank.updateY(-step);
                        } else if (direction == Direction.DOWN) {
                            tank.updateY(step);
                        } else if (direction == Direction.LEFT) {
                            tank.updateX(-step);
                        } else if (direction == Direction.RIGHT) {
                            tank.updateX(step);
                        }

                        covered[0] += step;


                        try {
                            Thread.sleep(tank.getSpeed());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }


                    defender.shareTanksLocation(defender.getX(), defender.getY());


                }

            }


        };
    }


    public Runnable processFire(Tank tank, Bullet newBullet) throws Exception {

        bullets.add(newBullet);

        return new Runnable() {

            @Override
            public synchronized void run() {
                int step = 1;

                for (int i = bullets.size() - 1; i < bullets.size(); i++) {

                    final int finalI = i;

                    new Thread(new Runnable() {
                        @Override
                        public synchronized void run() {
                            while ((bullets.get(finalI).getX() > -14 && bullets.get(finalI).getX() < 590) &&
                                    (bullets.get(finalI).getY() > -14 && bullets.get(finalI).getY() < 590)) {
                                if (bullets.get(finalI).getDirection() == Direction.UP) {
                                    bullets.get(finalI).updateY(-step);
                                } else if (bullets.get(finalI).getDirection() == Direction.DOWN) {
                                    bullets.get(finalI).updateY(step);
                                } else if (bullets.get(finalI).getDirection() == Direction.LEFT) {
                                    bullets.get(finalI).updateX(-step);
                                } else {
                                    bullets.get(finalI).updateX(step);
                                }
                                if (processInterception(bullets.get(finalI), tank)) {
                                    bullets.get(finalI).destroy();
                                }


                                try {
                                    Thread.sleep(bullets.get(finalI).getSpeed());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                if (bullets.get(finalI).isDestroyed()) {
                                    break;

                                }


                            }
                        }
                    }).start();

                }

            }


        };


    }


    private boolean processInterception(Bullet bullet, Tank tank) {

        if (bullet.getX() > 576 || bullet.getX() < 0 || bullet.getY() > 576 || bullet.getY() < 0) {
            return true;
        }

        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet itBullet = it.next();

            if (itBullet.getY() == bullet.getY() && itBullet.getX() == bullet.getX()) {
                if (itBullet != bullet && !itBullet.isDestroyed()) {
                    itBullet.destroy();
                    return true;
                }
            }
        }


        String coordinates = getQuadrant(bullet.getX(), bullet.getY());

        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

        if (y >= 0 && y < 9 && x >= 0 && x < 9) {
            ISimpleBFObject bfObject = battleField.scanQuadrant(y, x);
            if (!bfObject.isDestroyed() && !(bfObject instanceof Blank) && !(bfObject instanceof Water)) {
                battleField.destroyObject(y, x, tank);
                return true;
            }


            // check aggressor
            if (!aggressor.isDestroyed() && checkInterception(getQuadrant(aggressor.getX(), aggressor.getY()), coordinates)
                    && !(bullet.getTank().getClass().getSimpleName().equals(aggressor.getClass().getSimpleName()))) {
                aggressor.destroy();
                return true;
            }

            // check defender
            if (!defender.isDestroyed() && checkInterception(getQuadrant(defender.getX(), defender.getY()), coordinates)
                    && !(bullet.getTank().getClass().getSimpleName().equals(defender.getClass().getSimpleName()))) {
                defender.destroy();
                return true;
            }


        }

        return false;
    }

    private boolean checkInterception(String object, String quadrant) {
        int oy = Integer.parseInt(object.split("_")[0]);
        int ox = Integer.parseInt(object.split("_")[1]);

        int qy = Integer.parseInt(quadrant.split("_")[0]);
        int qx = Integer.parseInt(quadrant.split("_")[1]);

        if (oy >= 0 && oy < 9 && ox >= 0 && ox < 9) {
            if (oy == qy && ox == qx) {
                return true;
            }
        }
        return false;
    }


    public String getQuadrant(int x, int y) {
        return y / 64 + "_" + x / 64;
    }


}