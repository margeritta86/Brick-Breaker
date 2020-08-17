package game.model.raquet;

import view.GameView;
import game.engine.Mediator;
import game.model.*;
import game.model.special.Special;

import java.awt.*;

public class Raquet extends MovingObject {

    private ControllerStatus keyBoardController;
    private ControllerStatus mouseController;
    private RaquetControlExecution raquetControlExecution = new RaquetControlStandard(this);
    public static final Color DEFAULT_COLOR = Color.CYAN;
    public static final int DEFAULT_WIDTH = GameView.WIDTH / 8,
            DEFAULT_HEIGHT = GameView.HEIGHT / 48,
            DEFAULT_Y = GameView.HEIGHT - DEFAULT_HEIGHT * 2;
    public static final int SPEED_X = 10,
            DEFAULT_X = GameView.WIDTH / 2 - DEFAULT_WIDTH / 2;


    public Raquet(ControllerStatus keyBoardController, ControllerStatus mouseController, Mediator mediator) {
        super(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, SPEED_X, 0, DEFAULT_COLOR, Type.RAQUET, mediator);
        this.keyBoardController = keyBoardController;
        this.mouseController = mouseController;
    }

    @Override
    public void tick() {
        executeInput();
        correctPosition();
    }

    private void executeInput() {
        int keyBoardSpeed = Math.abs(keyBoardController.getHorizontalMovement());
        int mouseSpeed = Math.abs(mouseController.getHorizontalMovement());
        chooseKeyboardOrMouse(keyBoardSpeed, mouseSpeed);

        if (speedX != 0) {
            raquetControlExecution.horizontalAction();
        }
        if (keyBoardController.isActionPressed()) {
            raquetControlExecution.spaceAction();
        }
    }

    private void chooseKeyboardOrMouse(int keyBoardSpeed, int mouseSpeed) {
        if (keyBoardSpeed > mouseSpeed) {
            speedX = keyBoardController.getHorizontalMovement();
        } else if (mouseSpeed > keyBoardSpeed) {
            speedX = -mouseController.getHorizontalMovement();
        }else {
            speedX = 0;
        }
    }

    private void correctPosition() {
        if (isRightWallCollision()) {
            setX(GameView.WIDTH - width);
        }
        if (isLeftWallCollision()) {
            setX(0);
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getX(), y, width, height);
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


    public boolean isPositionToCorrect() {
        return isRightWallCollision() || isLeftWallCollision();
    }


    private boolean isLeftWallCollision() {
        return getX() < 0;
    }

    private boolean isRightWallCollision() {
        return getX() + width > GameView.WIDTH;
    }


    @Override
    public boolean isFrozen() {
        throw new UnsupportedOperationException();
    }


    public void setRaquetControlExecution(RaquetControlExecution raquetControlExecution) {
        this.raquetControlExecution = raquetControlExecution;
    }

}
