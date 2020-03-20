package game.model.brick;

import game.engine.Mediator;
import game.model.GameObject;

import java.awt.*;

public class BrickSolid extends Brick {

    private int counter;


    public BrickSolid(int x, int y, Mediator mediator) {
        super(x, y,Color.GRAY, mediator);
        counter = 0;
    }



    @Override
    public void reactToHit(GameObject object) {
        if (counter >= 1) {
            active = false;
            mediator.spawnSpecial(this);
        }
        this.setColor(Color.BLUE);
        counter++;

    }

}
