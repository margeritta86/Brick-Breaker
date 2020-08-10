package game.model.brick;

import game.engine.Mediator;
import game.engine.SoundEffect;
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
            SoundEffect.BRICK_BREAKER.play();
            brickDestroyed();
        }
        setColor(Color.BLUE);
        counter++;
    }
}
