package model;

import java.awt.*;

public class BallRaquetCollisionStandard implements BallRaquetCollision {

    private Ball ball;
    private BallRaquetCollisionBounce bounceCollision;


    public BallRaquetCollisionStandard(Ball ball) {
        this.ball = ball;
        bounceCollision = new BallRaquetCollisionBounce(ball);
    }

    @Override
    public void executeRaquetCollision(Rectangle bounds) {
        bounceCollision.bounce(bounds);
    }
}
