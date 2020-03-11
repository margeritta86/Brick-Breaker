package model;

import engine.Display;
import engine.Mediator;

import java.awt.*;

public class Brick extends GameObject {


    public static final int DEFAULT_HEIGHT = Display.getHeight() / 16,
            DEFAULT_WIDTH = Display.getWidth() / 10;

    public Brick(int x, int y, Color color, Mediator mediator) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, color, Type.BRICK,mediator);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);

    }

    @Override
    public void reactToHit(GameObject object) {

        active = false;
        mediator.spawnSpecial(this);
    }

    @Override
    public void accept(Special special) {

    }

    @Override
    public boolean isFrozen() {
        return true;
    }
}
