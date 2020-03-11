package model;

import engine.Display;
import engine.Mediator;

import java.awt.*;
import java.util.Random;

public class Ball extends MovingObject {

    private static Random random = new Random();
    private static final int SIZE = 30;
    private BallRaquetCollision collision = new BallRaquetCollisionStandard(this);
    protected boolean stickedBall = false;


    public Ball(int x, int y, Color color, Mediator mediator) {
        super(x, y, SIZE, SIZE, randomSpeed(), randomSpeed(), color, Type.BALL, mediator);
    }

    private static double randomSpeed() {
        return random.nextDouble() * 5 + 3;
    }

    @Override
    public void tick() {
        correctSpeed();
        checkWallColision();
        mediator.executeBallCollisions(this);
        move();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(x, y, width, width);
    }


    public void correctSpeed() {
        if (speedY >= -0.5 && speedY <= 0.5) {
            speedY = 1;
        }

        if (speedX >= -0.5 && speedX <= 0.5) {
            speedX = 1;
        }
    }

    private void checkWallColision() {
        if (x + width >= Display.getWidth()) {
            speedX = -speedX;
            x = Display.getWidth() - width;
        } else if (x <= 0) {
            speedX = -speedX;
            x = 0;
        } else if (y + width >= Display.getHeight()) {
            active = false;

        } else if (y <= 0) {
            speedY = -speedY;
            y = 0;
        }

    }

    private void move() {
        if (!stickedBall) {
            x += Math.round(speedX);
            y += Math.round(speedY);
        }
    }


    //brak ruchu
    public boolean doBrickCollision(GameObject obstacle) {
        Rectangle bounds = obstacle.getBounds();
        boolean wasHit = true;

        if (!obstacle.isInGame()) { // todo możliwe że nie wymagane
            return false;
        }
        boolean isInYRange = isInYRange(bounds);
        boolean isinXRange = isInXRange(bounds);

        if (isInYRange && isLeftHit(bounds)) {//LEWA
            executeLeftCollision(bounds);
        } else if (isInYRange && isRightHit(bounds)) {//PRAWA
            executeRightCollision(bounds);
        } else if (isTopHit(bounds) && isinXRange) {//GÓRA
            executeTopCollision(obstacle);
        } else if (isBottomHit(bounds) && isinXRange) {//DÓŁ
            executeBottomCollision(bounds);
        } else {
            return false;
        }
        return wasHit;
    }


    @Override
    public void reactToHit(GameObject obstacle) {

    }

    //zmiana na przyklejanie
    private void executeTopCollision(GameObject obstacle) {
        Rectangle bounds = obstacle.getBounds();
        if (obstacle.getType() == Type.RAQUET) {
            executeRaquetCollision(bounds);
        } else {
            executeSimpleTopCollision(bounds);
        }
    }

    private void executeSimpleTopCollision(Rectangle bounds) {
        speedY = -speedY;
        y = (int) (bounds.getY() - width);
    }

    private void executeBottomCollision(Rectangle bounds) {
        speedY = -speedY;
        y = (int) (bounds.getY() + bounds.getHeight());
    }

    private void executeRightCollision(Rectangle bounds) {
        speedX = -speedX;
        x = (int) (bounds.getX() + bounds.getWidth());
    }

    private void executeLeftCollision(Rectangle bounds) {
        speedX = -speedX;
        x = (int) (bounds.getX() - width);
    }

    private boolean isInXRange(Rectangle bounds) {
        return x + width / 2 >= bounds.getX() && x + width / 2 <= bounds.getX() + bounds.getWidth();
    }

    private boolean isInYRange(Rectangle bounds) {
        return y + width / 2 >= bounds.getY() && y + width / 2 <= bounds.getY() + bounds.getHeight();
    }

    private boolean isRightHit(Rectangle bounds) {
        return x <= bounds.getX() + bounds.getWidth() && x > bounds.getX() + bounds.getWidth() / 2;
    }

    private boolean isLeftHit(Rectangle bounds) {
        return x + width >= bounds.getX() && x + width < bounds.getX() + bounds.getWidth() / 2;
    }

    private boolean isTopHit(Rectangle bounds) {
        return y + width >= bounds.getY() && y < bounds.getY() + bounds.getHeight() / 2;
    }

    private boolean isBottomHit(Rectangle bounds) {
        return y <= bounds.getY() + bounds.getHeight() && y > bounds.getY() + bounds.getHeight() / 2;
    }


    public void executeRaquetCollision(Rectangle bounds) {
        collision.executeRaquetCollision(bounds);
    }

    public void speedUp(int modificator) {

        if (speedY < 0) {
            speedY -= modificator;
        } else {
            speedY += modificator;
        }
        if (speedX < 0) {
            speedX -= modificator;
        } else {
            speedX += modificator;
        }
    }

    public void accept(Special special) {
        special.execute(this);
    }

    public void slowDown(int modificator) {

        if (speedY < 0) {
            speedY += modificator;
        } else {
            speedY -= modificator;
        }
        if (speedX < 0) {
            speedX += modificator;
        } else {
            speedX -= modificator;
        }
    }


    public void speedDown(int modificator) {
        if (speedY < 0) {
            speedY += modificator;
        } else {
            speedY -= modificator;
        }
        if (speedX < 0) {
            speedX += modificator;
        } else {
            speedX -= modificator;
        }

    }

    public void setStickedBall(boolean stickedBall) {
        this.stickedBall = stickedBall;
    }

    public void setCollision(BallRaquetCollision collision) {
        this.collision = collision;
    }

    @Override
    public boolean isFrozen() {
        return stickedBall;
    }
}
