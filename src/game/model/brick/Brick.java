package game.model.brick;

import view.GameView;
import game.engine.Mediator;
import game.model.GameObject;
import game.model.special.Special;
import game.model.Type;

import java.awt.*;

public abstract class Brick extends GameObject {

    public static final int DEFAULT_HEIGHT = GameView.HEIGHT / 32,
                            DEFAULT_WIDTH = GameView.WIDTH / 20;


    public Brick(int x, int y, Color color, Mediator mediator) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, color, Type.BRICK, mediator);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getX(), y, width, height);
    }

    @Override
    public abstract void reactToHit(GameObject object);

    @Override
    public void accept(Special special) {
        special.execute(this);
    }

    @Override
    public boolean isFrozen() {
        return true;
    }

    void brickDestroyed() {
        active = false;
        mediator.spawnSpecial(this);
        mediator.incrementPlayerScore();
    }
}
