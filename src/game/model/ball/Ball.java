package game.model.ball;

import game.engine.SoundEffect;
import view.GameView;
import game.engine.Mediator;
import game.model.GameObject;
import game.model.MovingObject;
import game.model.special.Special;
import game.model.Type;

import java.awt.*;

public class Ball extends MovingObject {
    private static final int SIZE = 18;

    private BallRaquetCollision collision = new BallRaquetCollisionStandard(this);
    protected boolean stickedBall = false;


    public Ball(int x, int y, Color color, Mediator mediator) {
        super(x, y, SIZE, SIZE,  color, Type.BALL, mediator);
    }

    public Ball(int x, int y, double speedX, double speedY, Color color, Mediator mediator) {
        super(x, y, SIZE, SIZE, speedX,speedY, color, Type.BALL, mediator);
    }

    public void accept(Special special) {
        special.execute(this);
    }

    @Override
    public void tick() {
        correctSpeed();
        executeWallColision();
        mediator.executeBrickBallCollision(this);
        move();
    }

    public void correctSpeed() {
        if (getSpeedY() >= -0.5 && getSpeedY() <= 0.5) {
            setSpeedY(1);
        }

        if (getSpeedX() >= -0.5 && getSpeedX() <= 0.5) {
            setSpeedX(1);
        }
    }

    private void executeWallColision() {
        if (getX() + width >= GameView.WIDTH) {
            setSpeedX(-getSpeedX());
            setX(GameView.WIDTH - width);
        } else if (getX() <= 0) {
            setSpeedX(-getSpeedX());
            setX(0);
        } else if (y + width >= GameView.HEIGHT) {
            active = false;

        } else if (y <= 0) {
            setSpeedY(-getSpeedY());
            y = 0;
        }

    }

    private void move() {
        if (!stickedBall) {
            setX(getX() + Math.round(getSpeedX()));
            y += Math.round(getSpeedY());
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(getX(), y, width, height);
    }


    public boolean doBrickCollision(GameObject obstacle) {
        Rectangle bounds = obstacle.getBounds();
        boolean wasHit = true;

        boolean isInYRange = isInYRange(bounds);
        boolean isinXRange = isInXRange(bounds);

        if (obstacle.getType()==Type.RAQUET && isTopHit(bounds) && isinXRange) {
            SoundEffect.PLOOP.play();
            executeRaquetCollision(bounds);
            return true;
        }

        if (isInYRange && isLeftHit(bounds)) {//LEFT
            executeLeftCollision(bounds);
        } else if (isInYRange && isRightHit(bounds)) {//RIGHT
            executeRightCollision(bounds);
        } else if (isTopHit(bounds) && isinXRange) {//TOP
            executeSimpleTopCollision(bounds);
        } else if (isBottomHit(bounds) && isinXRange) {//BOTTOM
            executeBottomCollision(bounds);
        } else {
            return false;
        }
        return wasHit;
    }


   /* private void executeTopCollision(GameObject obstacle) {
        Rectangle bounds = obstacle.getBounds();
        if (obstacle.getType() == Type.RAQUET) {
            executeRaquetCollision(bounds);
        } else {
            executeSimpleTopCollision(bounds);
        }
    }*/

    private void executeSimpleTopCollision(Rectangle bounds) {
        setSpeedY(-getSpeedY());
        y = (int) (bounds.getY() - width);
    }

    private void executeBottomCollision(Rectangle bounds) {
        setSpeedY(-getSpeedY());
        y = (int) (bounds.getY() + bounds.getHeight());
    }

    private void executeRightCollision(Rectangle bounds) {
        setSpeedX(-getSpeedX());
        setX((int) (bounds.getX() + bounds.getWidth()));
    }

    private void executeLeftCollision(Rectangle bounds) {
        setSpeedX(-getSpeedX());
        setX((int) (bounds.getX() - width));
    }

    private boolean isInXRange(Rectangle bounds) {
        return getX() + width / 2 >= bounds.getX() && getX() + width / 2 <= bounds.getX() + bounds.getWidth();
    }

    private boolean isInYRange(Rectangle bounds) {
        return y + width / 2 >= bounds.getY() && y + width / 2 <= bounds.getY() + bounds.getHeight();
    }

    private boolean isRightHit(Rectangle bounds) {
        return getX() <= bounds.getX() + bounds.getWidth() && getX() > bounds.getX() + bounds.getWidth() / 2;
    }

    private boolean isLeftHit(Rectangle bounds) {
        return getX() + width >= bounds.getX() && getX() + width < bounds.getX() + bounds.getWidth() / 2;
    }

    private boolean isTopHit(Rectangle bounds) {
        return y + width >= bounds.getY() && y < bounds.getY() + bounds.getHeight() / 2;
    }

    private boolean isBottomHit(Rectangle bounds) {
        return y <= bounds.getY() + bounds.getHeight() && y > bounds.getY() + bounds.getHeight() / 2;
    }


    public void executeRaquetCollision(Rectangle bounds) {
        collision.executeRaquetCollision(bounds);
    }

    @Override
    public void reactToHit(GameObject obstacle) {
        

    }

    public void speedUp(int modificator) {

        if (getSpeedY() < 0) {
            setSpeedY(getSpeedY() - modificator);
        } else {
            setSpeedY(getSpeedY() + modificator);
        }
        if (getSpeedX() < 0) {
            setSpeedX(getSpeedX() - modificator);
        } else {
            setSpeedX(getSpeedX() + modificator);
        }
    }

    public void speedDown(int modificator) {
        if (getSpeedY() < 0) {
            setSpeedY(getSpeedY() + modificator);
        } else {
            setSpeedY(getSpeedY() - modificator);
        }
        if (getSpeedX() < 0) {
            setSpeedX(getSpeedX() + modificator);
        } else {
            setSpeedX(getSpeedX() - modificator);
        }

    }

    public void setStickedBall(boolean stickedBall) {
        this.stickedBall = stickedBall;
    }

    public void setCollision(BallRaquetCollision collision) {
        this.collision = collision;
    }

    @Override
    public boolean isFrozen() {
        return stickedBall;
    }

    public static int getSIZE() {
        return SIZE;
    }
}
