package game.model.ball;

import java.awt.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class BallRaquetCollisionHandBall implements BallRaquetCollision {

    private LocalTime counter= null;
    private Ball ball;
    private BallRaquetCollisionBounce bounceCollision;

    public BallRaquetCollisionHandBall(Ball ball) {
        this.ball = ball;
        bounceCollision = new BallRaquetCollisionBounce(ball);
    }

    @Override
    public void executeRaquetCollision(Rectangle bounds) {
        // jeśli ponizej sekundy(gdy nie jest nulem) lub jest juz przyklejona
        if ( ball.isFrozen()) {
            return;
        }
        bounceCollision.bounce(bounds);
        ball.stickedBall = true;
        counter = LocalTime.now();
    }

    private boolean isNotTimeToBounce() {
        return counter != null && counter.until(LocalTime.now(), ChronoUnit.SECONDS) < 1;
    }
}
