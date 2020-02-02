package model;

import engine.Display;
import engine.Mediator;

import java.awt.*;
import java.util.Random;

public class Ball extends MovingObject {

    private static Random random = new Random();


    //private double speedX = random.nextDouble()*5+3;
    //private double speedY =  random.nextDouble()*5+3;

    public Ball(int x, int y, int width, Color color,Mediator mediator) {
        super(x, y, width, width, randomSpeed(), randomSpeed(), color, Type.BALL,mediator);
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
        x += Math.round(speedX);
        y += Math.round(speedY);
    }


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

        int ballCenter = x + width / 2;
        int ballHit = ballCenter - bounds.x;

        double result = (double) ballHit / bounds.width;
        boolean right = false;
        if (result > 0.5) {
            result -= 0.5;
            right = true;
        }
        result *= 2;

        if (right) {

            speedX = Math.abs(speedX);
            double totalSpeed = speedX + speedY;
            speedX = result * totalSpeed;
            speedY = totalSpeed - speedX;
            speedX = Math.abs(speedX);

        } else {
            speedX = Math.abs(speedX);
            double totalSpeed = speedX + speedY;
            speedY = result * totalSpeed;
            speedX = totalSpeed - speedY;
            speedX = -speedX;


        }
        speedY = -speedY;
        y = (int) bounds.getY() - width;


    }

    public void speedUp() {
        speedY += 2;
        speedX += 2;
    }

    public void accept(Special special) {
        special.execute(this);
    }

    /*
     * Uniwersalny scheamt kolizji z prostokątem
     * // czy został udeżony -> + zwrócić info
     * // z której strony
     * // jak reagujemy na udeżenie z danej strony (specjalna reakcja na paletkę na hit z gory )
     * // zwrócenie informacji czy udeżony

     * */





}
