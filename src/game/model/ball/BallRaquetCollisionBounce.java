package game.model.ball;

import java.awt.*;

public class BallRaquetCollisionBounce {

    private Ball ball;

    public BallRaquetCollisionBounce(Ball ball) {
        this.ball = ball;
    }

    public void bounce(Rectangle bounds) {
            int ballCenter = ball.getX() + ball.getWidth() / 2;
            int ballHit = ballCenter - bounds.x;

            double result = (double) ballHit / bounds.width;
            boolean right = false;
            if (result > 0.5) {
                result -= 0.5;
                right = true;
            }
            result *= 2;

            if (right) {

                ball.setSpeedX(Math.abs(ball.getSpeedX()));
                double totalSpeed = ball.getSpeedX() + ball.getSpeedY();
                ball.setSpeedX(result * totalSpeed);
                ball.setSpeedY(totalSpeed - ball.getSpeedX());
                ball.setSpeedX(Math.abs(ball.getSpeedX()));

            } else {
                ball.setSpeedX(Math.abs(ball.getSpeedX()));
                double totalSpeed = ball.getSpeedX() + ball.getSpeedY();
                ball.setSpeedY(result * totalSpeed);
                ball.setSpeedX(totalSpeed - ball.getSpeedY());
                ball.setSpeedX(-ball.getSpeedX());


            }
            ball.setSpeedY(-ball.getSpeedY());
            ball.setY( bounds.getY() - ball.getWidth() -1);
    }
}
