package game.model.special;

import game.engine.Mediator;
import game.engine.SoundEffect;
import game.model.GameObject;
import game.model.ImageLoader;
import game.model.MovingObject;
import game.model.Type;
import game.model.ball.Ball;
import game.model.ball.BallRaquetCollisionHandBall;
import game.model.ball.BallRaquetCollisionStandard;
import game.model.brick.Brick;
import game.model.raquet.Raquet;
import game.model.raquet.RaquetControlHandBall;
import game.model.raquet.RaquetControlStandard;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Special extends MovingObject {
    
    
     SpecialType type;
    private boolean used = false;
    private int secFromActivation = 0;
    int duration;
    private BufferedImage image;
    private static final int DEFAULT_SPEED_Y = 5, 
                             DEFAULT_SPEED_X = 0;
    
    public Special(int x, int y, SpecialType type, Mediator mediator, String imagePath) {
        super(x, y, 40, 40, DEFAULT_SPEED_X, DEFAULT_SPEED_Y, type.getColor(), Type.SPECIAL, mediator);
        this.type = type;
        duration = type.getDuration();
        image = ImageLoader.loadImage(imagePath);//nowe
    }

    public void increase() {
        secFromActivation++;
    }

    public void doubleTime() {
        duration += type.getDuration();
    }

    public boolean isTimeToReverse() {
        return secFromActivation >= duration;
    }

    @Override
    public void tick() {
        move();
        mediator.executeRaquetCollision(this);

    }

    private void move() {
        setX(getX() + Math.round(getSpeedX()));
        y += Math.round(getSpeedY());
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.drawImage(image,getX(),y,width,height,null);
        
    }


    @Override
    public void reactToHit(GameObject object) {
    }

    public void accept(Special special) {
    }

    public void reverseEffect() {
    }

    public void reverseEffect(Ball ball) {
    }

    public void reverseEffect(Brick brick) {
    }

    public void reverseEffect(Raquet raquet) {
    }

    public void executeEffect() {
    }

    public void execute() {
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
        } else {
            reverseEffect(raquet);
        }
    }

    public void executeEffect(Ball ball) {
    }

    public void executeEffect(Brick brick) {
    }

    public void executeEffect(Raquet raquet) {
    }


    public void activated() {
        active = false;
    }

    @Override
    public boolean isFrozen() {
        return false;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return type == special.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Special{" +
                "type=" + type +
                ", used=" + used +
                ", secFromActivation=" + secFromActivation +
                ", duration=" + duration +
                '}';
    }
}

class SpeedBallSpecial extends Special {
    private final static int SPEED_MODIFICATOR = 5;

    public SpeedBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.SPEED_BALL, mediator, "/game/resources/special_icons/speedUp.png");
    }


    @Override
    public void executeEffect(Ball ball) {
        ball.speedUp(SPEED_MODIFICATOR);
        ball.setColor(Color.RED);
    }


    @Override
    public void reverseEffect(Ball ball) {
        ball.speedDown(SPEED_MODIFICATOR/2);//TODO posprawdzać 
        ball.setColor(Color.YELLOW);
    }

}

class SlowdownBallSpecial extends Special {
    private final static int SPEED_MODIFICATOR = 5;

    public SlowdownBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.SLOWDOWN_BALL, mediator, "/game/resources/special_icons/slowDown.png");
    }


    @Override
    public void executeEffect(Ball ball) {
        ball.speedDown(SPEED_MODIFICATOR);
        ball.setColor(Color.CYAN);
    }


    @Override
    public void reverseEffect(Ball ball) {
        ball.speedUp(SPEED_MODIFICATOR);
        ball.setColor(Color.YELLOW);
    }

}

class WiderRaquetSpecial extends Special {

    private final static int SIZE_MODIFICATOR = 60;

    public WiderRaquetSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.WIDER_RAQUET, mediator, "/game/resources/special_icons/widerRaquet.png");
    }

    @Override
    public void reverseEffect(Raquet raquet) {
        raquet.sizeDown(SIZE_MODIFICATOR);
        raquet.setColor(Raquet.DEFAULT_COLOR);
    }

    @Override
    public void executeEffect(Raquet raquet) {
        raquet.sizeUpRaquet(SIZE_MODIFICATOR);
        raquet.setColor(Color.RED);
    }
}

class NarrowerRaquetSpecial extends Special {

    private final static int SIZE_MODIFICATOR = 60;

    public NarrowerRaquetSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.NARROWER_RAQUET, mediator, "/game/resources/special_icons/narrowerRaquet.png");
    }

    @Override
    public void reverseEffect(Raquet raquet) {
        raquet.sizeUpRaquet(SIZE_MODIFICATOR);
        raquet.setColor(Raquet.DEFAULT_COLOR);
    }

    @Override
    public void executeEffect(Raquet raquet) {
        raquet.sizeDown(SIZE_MODIFICATOR);
        raquet.setColor(Color.MAGENTA);
    }
}

class DoubleBallSpecial extends Special {


    public DoubleBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.DOUBLE_BALL, mediator, "/game/resources/special_icons/3balls.png");
    }

    @Override
    public void executeEffect(Ball ball) {
        mediator.doubleBall(ball);
    }

    @Override
    public void reverseEffect(Ball ball) {
        super.reverseEffect(ball);
    }
}

class HandBallSpecial extends Special {

    private List<Ball> handBalls = new ArrayList<>();

    public HandBallSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.HAND_BALL, mediator, "/game/resources/special_icons/handBall.png");

    }

    @Override
    public void executeEffect(Ball ball) {
        ball.setCollision(new BallRaquetCollisionHandBall(ball));
        handBalls.add(ball);
    }

    @Override
    public void reverseEffect(Ball ball) {
        ball.setStickedBall(false);
        ball.setCollision(new BallRaquetCollisionStandard(ball));
        handBalls.clear();
    }

    @Override
    public void reverseEffect(Raquet raquet) {
        raquet.setRaquetControlExecution(new RaquetControlStandard(raquet));
        raquet.setColor(Raquet.DEFAULT_COLOR);
    }

    @Override
    public void executeEffect(Raquet raquet) {
        raquet.setRaquetControlExecution(new RaquetControlHandBall(raquet, handBalls));
        raquet.setColor(Color.GREEN);
    }



}

class LevelUpSpecial extends Special {

    public LevelUpSpecial(int x, int y, Mediator mediator) {
        super(x, y, SpecialType.LEVELUP, mediator, "/game/resources/special_icons/levelUp.png");
    }

    @Override
    public void executeEffect() {
        SoundEffect.LEVEL_WON.play();
        mediator.wonLevel();
    }
}



