package model;

import engine.Mediator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.SpecialFactory.DEFAULT_TIME;

public abstract class Special extends MovingObject {

    private static final int DEFAULT_SPEED_Y = 5, DEFAULT_SPEED_X = 0;
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
        return secFromActivation >= type.getDuration() ;
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
    public void reactToHit(GameObject object) { }

    public void accept(Special special) { }

    public void reverseEffect(){ }

    public void reverseEffect(Ball ball) { }

    public void reverseEffect(Brick brick) { }

    public void reverseEffect(Raquet raquet) { }

    public void executeEffect(){ }

    public void execute(){
        if (active) {
            executeEffect();
        } else {
            reverseEffect();
        }
    }

    public void execute(Ball ball) {
        if (active) {
            executeEffect(ball);

        } else {
            reverseEffect(ball);
        }
    }

    public void execute(Brick brick) {
        if (active) {
            executeEffect(brick);

        } else {
            reverseEffect(brick);
        }
    }

    public void execute(Raquet raquet) {
        if (active) {
            executeEffect(raquet);
            System.out.println("Rakieta");
        } else {
            reverseEffect(raquet);
        }
    }

    public void executeEffect(Ball ball) { }

    public void executeEffect(Brick brick) { }

    public void executeEffect(Raquet raquet) { }


    public void activated() {
        active = false;
    }

    @Override
    public boolean isFrozen() {
        return false;
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
    public void reverseEffect(Ball ball) {
        ball.speedDown(SPEED_MODIFICATOR);
    }

}

class SlowdownBallSpecial extends Special {
    private final static int SPEED_MODIFICATOR = 5;

    public SlowdownBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.SLOWDOWN_BALL, mediator);
    }


    @Override
    public void executeEffect(Ball ball) {
        ball.speedDown(SPEED_MODIFICATOR);
    }


    @Override
    public void reverseEffect(Ball ball) {
        ball.speedUp(SPEED_MODIFICATOR);
    }

}

class WiderRaquetSpecial extends Special {

    private final static int SIZE_MODIFICATOR = 40;

    public WiderRaquetSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.WIDER_RAQUET, mediator);
    }

    @Override
    public void reverseEffect(Raquet raquet) {
        raquet.sizeDown(SIZE_MODIFICATOR);
    }

    @Override
    public void executeEffect(Raquet raquet) {
        raquet.sizeUpRaquet(SIZE_MODIFICATOR);
    }
}

class NarrowerRaquetSpecial extends Special {

    private final static int SIZE_MODIFICATOR = 40;

    public NarrowerRaquetSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.NARROWER_RAQUET, mediator); }

    @Override
    public void reverseEffect(Raquet raquet) {
        raquet.sizeUpRaquet(SIZE_MODIFICATOR);
    }

    @Override
    public void executeEffect(Raquet raquet) {
        raquet.sizeDown(SIZE_MODIFICATOR);
    }
}

class DoubleBallSpecial extends Special {


    public DoubleBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.DOUBLE_BALL, mediator);
    }

    @Override
    public void executeEffect(Ball ball) {
        mediator.doubleBall(ball);
    }

}

class HandBallSpecial extends Special{

    private List<Ball> handBalls = new ArrayList<>();
    public HandBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.HAND_BALL, mediator);
    }

    @Override
    public void executeEffect(Ball ball) {
        ball.setCollision(new BallRaquetCollisionHandBall(ball));
        handBalls.add(ball);
    }

    //0 start sp 1
    //3 start sp 2
    //przyklaja sie piłka -> jest frozen i ma collision handball
    //5 stop sp 1

    //8 stp sp 2
    //odkleja się


    @Override
    public void reverseEffect(Ball ball) {
        ball.setStickedBall(false);
        ball.setCollision(new BallRaquetCollisionStandard(ball));
        handBalls.clear();
    }
    @Override
    public void reverseEffect(Raquet raquet) {
        raquet.setRaquetControlExecution(new RaquetControlStandard(raquet));
    }

    @Override
    public void executeEffect(Raquet raquet) {
        raquet.setRaquetControlExecution(new RaquetControlHandBall(raquet,handBalls));
    }

}

class LevelUpSpecial extends Special{

    public LevelUpSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.LEVELUP, mediator);
    }

    @Override
    public void executeEffect() {
        mediator.wonLevel();
    }
}


enum SpecialType {

    SPEED_BALL(Color.BLUE, DEFAULT_TIME,0.90),
    WIDER_RAQUET(Color.RED, DEFAULT_TIME,0.90),
    NARROWER_RAQUET(Color.ORANGE, DEFAULT_TIME,0.90),
    DOUBLE_BALL(Color.MAGENTA, DEFAULT_TIME,0.90),
    HAND_BALL(Color.PINK, DEFAULT_TIME,0.90),
    LEVELUP(Color.GREEN, DEFAULT_TIME,0.10),
    SLOWDOWN_BALL(Color.CYAN, DEFAULT_TIME,0.90);


    private Color color;
    private int duration;
    private double chance;

    SpecialType() {
    }

    SpecialType(Color color, int duration, double chance) {
        this.color = color;
        this.duration = duration;
        this.chance = chance;
    }

    public int getDuration() {
        return duration;
    }

    public Color getColor() {
        return color;
    }


    public double getChance() {
        return chance;
    }
}
