package game.model.brick;

import game.engine.Mediator;
import game.model.GameObject;
import game.model.Type;

import java.awt.*;

public class BrickUnbreakable extends Brick {

    public BrickUnbreakable(int x, int y, Mediator mediator) {
        super(x, y,Type.BRICK_UNBREAKABLE, mediator);
        active = true;
    }

    @Override
    public void reactToHit(GameObject object) {
        active = true;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(getX(), y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getX(), y, width, height);
    }
}
