package model;

import engine.Mediator;

import java.awt.*;

import static model.SpecialFactory.DEFAULT_TIME;

public abstract class Special extends MovingObject {

    private static final int DEFAULT_SPEED_Y = 10, DEFAULT_SPEED_X = 0;
    private SpecialType type;
    private boolean used = false;
    private int secFromActivation = 0;

    public Special(int x, int y, SpecialType type, Mediator mediator) {
        super(x, y, 20, 20, DEFAULT_SPEED_X, DEFAULT_SPEED_Y, type.getColor(), Type.SPECIAL, mediator);
        this.type = type;
    }

    public void increase() {
        secFromActivation++;
    }

    public boolean isTimeToReverse() {
        return secFromActivation >= type.getDuration();
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

    public void accept(Special special) {
    }

    public abstract void reverseEffect(Ball ball);

    public abstract void reverseEffect(Brick brick);

    public abstract void reverseEffect(Raquet raquet);

    public  void execute(Ball ball){
        if (!active) {
            executeEffect(ball);

        } else {
            reverseEffect(ball);
        }
    }

    public  void execute(Brick brick){
        if (!active) {
            executeEffect(brick);

        } else {
            reverseEffect(brick);
        }
    }

    public  void execute(Raquet raquet){
        if (!active) {
            executeEffect(raquet);

        } else {
            reverseEffect(raquet);
        }
    }

    public abstract void executeEffect (Ball ball);

    public abstract void executeEffect(Brick brick);

    public abstract void executeEffect(Raquet raquet);


    public void activated() {
        active = false;
    }


}

class SpeedBallSpecial extends Special {
    private final static int SPEED_MODIFICATOR = 5;

    public SpeedBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.SPEED_BALL, mediator);
    }


    @Override
    public void executeEffect(Ball ball) {
        ball.speedUp(SPEED_MODIFICATOR);
    }

    @Override
    public void executeEffect(Brick brick) {

    }

    @Override
    public void executeEffect(Raquet raquet) {

    }

    @Override
    public void reverseEffect(Ball ball) {
        ball.speedDown(SPEED_MODIFICATOR);
    }

    @Override
    public void reverseEffect(Brick brick) {

    }

    @Override
    public void reverseEffect(Raquet raquet) {

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
    private int duration;

    SpecialType(Color color, int duration) {
        this.color = color;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public Color getColor() {
        return color;
    }
}
