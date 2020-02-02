package model;

import engine.Mediator;

import java.awt.*;

import static model.SpecialFactory.DEFAULT_TIME;

public abstract class Special extends MovingObject {

    private static final int DEFAULT_SPEED_Y = 10, DEFAULT_SPEED_X = 0;
    private SpecialType type;
    private boolean used = false;

    public Special(int x, int y, SpecialType type, Mediator mediator) {
        super(x, y, 20, 20, DEFAULT_SPEED_X, DEFAULT_SPEED_Y, type.getColor(), Type.SPECIAL, mediator);
        this.type = type;
    }


    @Override
    public void tick() {
        move();
        mediator.executeRaquetCollision(this);

    }

    private void move() {
        x += Math.round(speedX);
        y += Math.round(speedY);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fill3DRect(x, y, width, width, false);
    }


    @Override
    public void reactToHit(GameObject object) {
    }

    public void accept(Special special){
    }

    public abstract void execute(Ball ball);
    public abstract void execute(Brick brick);
    public abstract void execute(Raquet raquet);

    public  void activated(){
        active = false;
    }
}

class SpeedBallSpecial extends Special{

    public SpeedBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.SPEED_BALL, mediator);
    }
    @Override
    public void execute(Brick brick) {

    }

    @Override
    public void execute(Raquet raquet) {

    }

    @Override
    public void execute(Ball ball) {
        activated();
        ball.speedUp();
        System.out.println("SUKCES!");
    }

}









enum SpecialType {

    SPEED_BALL(Color.BLUE, DEFAULT_TIME),
    WIDER_RAQUET(Color.RED, DEFAULT_TIME),
    NARROWER_RAQUET(Color.ORANGE, DEFAULT_TIME),
    THREE_BALLS(Color.YELLOW, DEFAULT_TIME),
    HAND_BALL(Color.PINK, DEFAULT_TIME),
    LEVELUP(Color.GREEN, DEFAULT_TIME),
    SLOWDOWN_BALL(Color.CYAN, DEFAULT_TIME);

    private Color color;
    private int time;

    SpecialType(Color color, int time) {
        this.color = color;
        this.time = time;
    }

    public Color getColor() {
        return color;
    }
}
