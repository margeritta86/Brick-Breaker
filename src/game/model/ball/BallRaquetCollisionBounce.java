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
            double newSpeedX = Math.abs(ball.getSpeedX()); //watość bezwzgledna
            double totalSpeed =newSpeedX + ball.getSpeedY(); //łączna prędkość
            newSpeedX = result * totalSpeed; //pozwala ustalić nową proporcie X do Y
            newSpeedX =Math.abs(newSpeedX);
            ball.setSpeedY(totalSpeed - newSpeedX);// wcześniej było - ball.getSpeedX
            ball.setSpeedX(newSpeedX);
        } else {
            ball.setSpeedX(Math.abs(ball.getSpeedX()));
            double totalSpeed = ball.getSpeedX() + ball.getSpeedY();
            ball.setSpeedY(result * totalSpeed);
            ball.setSpeedX(totalSpeed - ball.getSpeedY());
            ball.setSpeedX(-ball.getSpeedX());
        }
        
        ball.setY(bounds.getY() - ball.getWidth() - 1);
        ball.setSpeedY(-ball.getSpeedY());

    }
}
