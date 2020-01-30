package model;

import engine.Mediator;

import java.awt.*;

public class Special extends MovingObject {

    private static final int DEFAULT_SPEED_Y = 10, DEFAULT_SPEED_X = 0;


    public Special(int x, int y, int width, int height, Color color, Mediator mediator) {
        super(x, y, width, height, DEFAULT_SPEED_X, DEFAULT_SPEED_Y, color, Type.SPECIAL, mediator);
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fill3DRect(x, y, width, width,true);
    }


    @Override
    public void reactToHit(GameObject object) {

    }
}

enum SpecialType {

    SPEED_BALL(Color.BLUE),
    WIDER_RAQUET(Color.RED),
    THREE_BALLS(Color.YELLOW),
    HAND_BALL(Color.PINK),
    LEVELUP(Color.GREEN),
    SLOWDOWN_BALL(Color.CYAN);

    SpecialType(Color color) {

    }
}
