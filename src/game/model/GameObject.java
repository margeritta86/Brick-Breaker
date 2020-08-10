package game.model;

import game.engine.Mediator;
import game.model.special.Special;

import java.awt.*;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color color;
    protected boolean active;
    private Type type;
    protected Mediator mediator;


   public GameObject(int x, int y,int width, int height, Type type, Mediator mediator) {
       this.setX(x);
       this.y = y;
       this.mediator = mediator;
       this.type = type;
       this.height = height;
       this.width = width;
       active = true;
   }

    public GameObject(int x, int y, int width, int height, Color color, Type type, Mediator mediator) {
        this.setX(x);
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.type = type;
        this.mediator = mediator;
        active = true;
    }

    public GameObject(int x, int y, Type type, Mediator mediator) {
        this.setX(x);
        this.y = y;
        this.type = type;
        this.mediator = mediator;
        active = true;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public abstract void reactToHit(GameObject object);

    public abstract void accept(Special special);

    public Rectangle getBounds() {
        return new Rectangle(getX(), y, width, height);
    }

    public boolean isIntersecting(GameObject other) {
        return getBounds().intersects(other.getBounds());
    }

    public boolean isNotInGame() {
        return !active;
    }

    public void setY(Number y) {
        this.y = y.intValue();
    }

    public void setX(Number x) {
        this.x = x.intValue();
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract boolean isFrozen();

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
