package model;

import engine.Mediator;

import java.awt.*;

public abstract class GameObject {

    int x;
    int y;
    int width,height;
    Color color;
    boolean active;
    Type type;
    Mediator mediator;

    public GameObject(int x, int y, int width, int height,Color color, Type type, Mediator mediator) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.type = type;
        this.mediator = mediator;
        active = true;
    }

    public boolean isInGame() {//todo refactorować do should be deleted(odwrotny warunek)
        return active;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public Type getType() {
        return type;
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public abstract void reactToHit(GameObject object);

    public boolean isIntersecting(GameObject other) { //TODO wywalić jesli niepotrzebny przy znajdźkach
        return getBounds().intersects(other.getBounds());
    }
}
