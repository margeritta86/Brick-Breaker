package game.model.brick;

import game.engine.Mediator;
import game.engine.SoundEffect;
import game.model.GameObject;

import java.awt.*;

public class BrickStandard extends Brick {

    public BrickStandard(int x, int y, Color color, Mediator mediator) {
        super(x, y, color, mediator);
    }

    @Override
    public void reactToHit(GameObject object) {
        SoundEffect.BRICK_BREAKER.play();
        brickDestroyed();
    }


}
