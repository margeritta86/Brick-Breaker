package model;

import engine.Display;
import engine.Mediator;

import java.awt.*;

public class Raquet extends MovingObject {

    private RaquetController controller;

    public static final int DEFAULT_HEIGHT = Display.getHeight() / 32,
            DEFAULT_WIDTH = Display.getWidth() / 4,
            DEFAULT_X = Display.getWidth() / 2 - DEFAULT_WIDTH / 2,
            DEFAULT_Y = Display.getHeight() - DEFAULT_HEIGHT * 2,
            DEFAULT_SPEED_X = 10;



    public Raquet(RaquetController controller, Mediator mediator) {
        super(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT,DEFAULT_SPEED_X,0, Color.BLUE,Type.RAQUET,mediator);
        this.controller = controller;


    }

    @Override
    public void tick() {
        move();
        correctPosition();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x,y,width,height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x,y,width,height);

    }

    @Override
    public void reactToHit(GameObject object) {

    }

    @Override
    public void accept(Special special) {

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
