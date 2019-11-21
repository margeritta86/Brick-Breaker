package model;

import engine.Display;

import java.awt.*;

public class Brick extends Rect {



    public static final int DEFAULT_HEIGHT = Display.getHeight()/16,
                            DEFAULT_WIDTH = Display.getWidth()/10;

    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color,CollisionType.STANDARD);
    }


    @Override
    void reactToCollision() {
        active = false;

    }

    /*private List deleteBrick(List bricks) {
        Brick cegla;
        for (Object brick : bricks) {
            if(!this.isAcitve()){
                cegla = this;
            }
        }
        bricks.remove(cegla);
        return bricks;

    }*/


    @Override
    public void tick() {


    }

}
