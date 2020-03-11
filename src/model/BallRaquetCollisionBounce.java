package model;

import java.awt.*;

public class BallRaquetCollisionBounce {

    private Ball ball;

    public BallRaquetCollisionBounce(Ball ball) {
        this.ball = ball;
    }

    public void bounce(Rectangle bounds) {
            int ballCenter = ball.x + ball.width / 2;
            int ballHit = ballCenter - bounds.x;

            double result = (double) ballHit / bounds.width;
            boolean right = false;
            if (result > 0.5) {
                result -= 0.5;
                right = true;
            }
            result *= 2;

            if (right) {

                ball.speedX = Math.abs(ball.speedX);
                double totalSpeed = ball.speedX + ball.speedY;
                ball.speedX = result * totalSpeed;
                ball.speedY = totalSpeed - ball.speedX;
                ball.speedX = Math.abs(ball.speedX);

            } else {
                ball.speedX = Math.abs(ball.speedX);
                double totalSpeed = ball.speedX + ball.speedY;
                ball.speedY = result * totalSpeed;
                ball.speedX = totalSpeed - ball.speedY;
                ball.speedX = -ball.speedX;


            }
            ball.speedY = -ball.speedY;
            ball.y = (int) bounds.getY() - ball.width;
    }
}
