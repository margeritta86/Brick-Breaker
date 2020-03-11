package model;

import engine.Display;
import engine.Mediator;

import java.awt.*;

public class Raquet extends MovingObject {

    private ControllerStatus controller;
    public static final int DEFAULT_WIDTH = Display.getWidth() / 4,
            DEFAULT_HEIGHT = Display.getHeight() / 32;


    public static final int
            DEFAULT_X = Display.getWidth() / 2 - DEFAULT_WIDTH / 2,
            DEFAULT_Y = Display.getHeight() - DEFAULT_HEIGHT * 2,
            DEFAULT_SPEED_X = 10;

    private RaquetControlExecution raquetControlExecution = new RaquetControlStandard(this);


    public Raquet(ControllerStatus controller, Mediator mediator) {
        super(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SPEED_X, 0, Color.BLUE, Type.RAQUET, mediator);
        this.controller = controller;
    }

    @Override
    public void tick() {
        executeInput();
        correctPosition();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);

    }

    public void sizeUpRaquet(int modificator) {
        width = width + modificator;
    }

    public void sizeDown(int modificator) {
        width = width - modificator;
    }

    @Override
    public void reactToHit(GameObject object) {

    }

    @Override
    public void accept(Special special) {
        special.execute(this);
    }


    private void executeInput() {
        if (controller.isRIghtPressed()) {
            raquetControlExecution.rightAction();
        }
        if (controller.isLeftPressed()) {
            raquetControlExecution.leftAction();
        }
        if (controller.isSpacePressed()) {
            raquetControlExecution.spaceAction();
        }

    }

    public boolean isPositionToCorrect() {
        return isRightWallCollision() || isLeftWallCollision();
    }


    private void correctPosition() {
        if (isRightWallCollision()) {
            x = Display.getWidth() - width;
        }
        if (isLeftWallCollision()) {
            x = 0;
        }
    }

    private boolean isLeftWallCollision() {
        return x < 0;
    }

    private boolean isRightWallCollision() {
        return x + width > Display.getWidth();
    }


    @Override
    public boolean isFrozen() {
        throw new UnsupportedOperationException();
    }


    public void setRaquetControlExecution(RaquetControlExecution raquetControlExecution) {
        this.raquetControlExecution = raquetControlExecution;
    }
}
