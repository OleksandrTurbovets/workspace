package hwFrame2.BattleField;

import hwFrame2.Interfaces.*;
import hwFrame2.Tanks.Bt7;
import hwFrame2.Tanks.Tank;

import java.awt.*;

public class BattleField implements Drawable {

    public static final String BRICK = "B";
    public static final String EAGLE = "E";
    public static final String ROCK = "R";
    public static final String WATER = "W";
    public static final String BLANK = " ";


	private int bfWidth = 576;
	private int bfHeight = 576;

	private String[][] battleFieldTemplate = {
		{"B","B","B","B","B","B","B","B","B"},
		{"B"," "," "," "," "," "," "," ","B"},
		{"B"," ","B","B","B","B","B"," ","B"},
		{"B"," ","B","W","W","W","B"," ","B"},
		{"B"," ","B","W","W","W","B"," ","B"},
		{"B"," ","B","W","W","W","B"," ","B"},
		{"B"," ","B"," "," "," ","B"," ","B"},
		{"B"," "," ","R","R","R"," "," ","B"},
		{"B","B","B","R","E","R","B","B","B"}
	};

    private int DxTemp;
    private int DyTemp;

    private ISimpleBFObject[][] simpleBattleField = new SimpleBFObject[9][9];
    private IConstantBFObject[][] constantBattleField = new ConstantBFObject[9][9];

    public BattleField(){
        drawBattleField();
    }

    public BattleField(String[][] battleField){
        battleFieldTemplate = battleField;
        drawBattleField();
    }

    public void setTanksLocation(int x, int y, String tank){
            battleFieldTemplate[DxTemp][DyTemp] = " ";
            battleFieldTemplate[x][y] = tank;
            DxTemp = x;
            DyTemp = y;

    }

    public String getTanksLocation(int x, int y){
        return battleFieldTemplate[y][x];
    }


    public String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    private void drawBattleField(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                String coordinates = getQuadrantXY(i + 1, j + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates.substring(0, separator));
                int x = Integer.parseInt(coordinates.substring(separator + 1));

                String obj = battleFieldTemplate[i][j];
                ISimpleBFObject sbfObject = null;
                if (obj.equals(BRICK)){
                    sbfObject = new Brick(x, y);
                } else if (obj.equals(ROCK)){
                    sbfObject = new Rock(x, y);
                } else if (obj.equals(EAGLE)){
                    sbfObject = new Eagle(x, y);
                }else {
                    sbfObject = new Blank(x, y);
                }
                simpleBattleField[i][j] = sbfObject;

                IConstantBFObject cbfObject = null;
                if (obj.equals(WATER)){
                    cbfObject = new Water(x, y);
                }
                constantBattleField[i][j] = cbfObject;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < simpleBattleField.length; j++) {
            for (int k = 0; k < simpleBattleField.length; k++) {
                simpleBattleField[j][k].draw(g);
            }
        }


    }

    public void drawWater(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        for (int j = 0; j < constantBattleField.length; j++) {
            for (int k = 0; k < constantBattleField.length; k++) {
                if (constantBattleField[j][k] instanceof Water){
                    constantBattleField[j][k].draw(g);
                }
            }
        }
    }




    public void destroyObject(int v, int h, Tank tank) {

        if (!(simpleBattleField[v][h] instanceof Rock)){
            simpleBattleField[v][h].destroy();
            simpleBattleField[v][h] = new Blank(h*64, v*64);
        } else if (simpleBattleField[v][h] instanceof Rock && tank instanceof Bt7){
            simpleBattleField[v][h].destroy();
            simpleBattleField[v][h] = new Blank(h*64, v*64);
        }

    }

    public ISimpleBFObject scanQuadrant(int v, int h){
        return simpleBattleField[v][h];
    }

    public int getBfWidth(){
        return bfWidth;
    }

    public int getBfHeight(){
        return bfHeight;
    }
}
