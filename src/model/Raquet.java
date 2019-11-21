package model;

import engine.Display;

import java.awt.*;

public class Raquet extends Rect {

    private RaquetController controller;
    private int speedX = DEFAULT_SPEED_X;

    public static final int DEFAULT_HEIGHT = Display.getHeight() / 32,
            DEFAULT_WIDTH = Display.getWidth() / 4,
            DEFAULT_X = Display.getWidth() / 2 - DEFAULT_WIDTH / 2,
            DEFAULT_Y = Display.getHeight() - DEFAULT_HEIGHT * 2,
            DEFAULT_SPEED_X = 10;



    @Override
    void reactToCollision() {

    }

    public Raquet(Color color, RaquetController controller) {
        super(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, color,CollisionType.RAQUET);
        this.controller = controller;


    }

    @Override
    public void tick() {
        move();
        correctPosition();
    }

    private void move() {

        if (controller.isRIghtPressed()) {
            x += speedX;
        }
        if (controller.isLeftPressed()) {
            x -= speedX;
        }
    }

    private void correctPosition() {
        if (x + width > Display.getWidth()) {
            x = Display.getWidth() - width;
        }
        if (x < 0) {
            x = 0;
        }


    }
}
