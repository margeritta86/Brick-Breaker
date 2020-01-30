package model;

import engine.Mediator;

import java.awt.*;

public abstract class MovingObject extends GameObject {

     double speedX;
     double speedY;

    public MovingObject(int x, int y, int width, int height,double speedX,double speedY, Color color,Type type, Mediator mediator) {
        super(x, y, width, height,color,type,mediator);
        this.speedX = speedX;
        this.speedY = speedY;

    }
}
