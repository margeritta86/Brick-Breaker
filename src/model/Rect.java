package model;

import engine.Gameplay;

import java.awt.*;


enum CollisionType {
    STANDARD, RAQUET
}

// obiekt z którym mogą być kolizje
public abstract class Rect implements GameObject {

    int x, y;
    int width;
    int height;
    private Color color;
    boolean active = true;
    private CollisionType collisionType;

    public Rect(int x, int y, int width, int height, Color color, CollisionType collisionType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.collisionType = collisionType;
    }

    public void render(Graphics graphics) {
        if (!active) {
            return;
        }

        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);


    }

    abstract void reactToCollision();


    public boolean isAcitve() {
        return active;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return y;
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }
}
