package game.model;

import game.engine.Mediator;

import java.awt.*;
import java.util.Random;

public abstract class MovingObject extends GameObject {

     protected double speedX;
     protected double speedY;

     public MovingObject(int x, int y, int width, int height,double speedX,double speedY, Color color,Type type, Mediator mediator ) {
        super(x, y, width, height,color,type,mediator);
        this.setSpeedX(speedX);
        this.setSpeedY(speedY);

    }

    public MovingObject(int x, int y, int width, int height, Color color,Type type, Mediator mediator) {
        super(x, y, width, height,color,type,mediator);
        speedY = randomSpeed();
        speedX = randomSpeed();

    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    private static double randomSpeed() {
        return new Random().nextDouble() * 5 + 3;
    }


}
