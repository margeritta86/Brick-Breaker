package game.model.raquet;

import game.model.ball.Ball;

import java.util.ArrayList;
import java.util.List;

public class RaquetControlHandBall implements RaquetControlExecution {

    private Raquet raquet;
    private List<Ball> handBalls;

    public RaquetControlHandBall(Raquet raquet, List<Ball> handBalls) {
        this.raquet = raquet;
        this.handBalls = handBalls;
    }

    @Override
    public void horizontalAction() {
        raquet.setX(raquet.getX() + raquet.getSpeedX());
        if (raquet.isPositionToCorrect()) {
            return;
        }
        for (Ball stickedBall : findStickedBalls()) {
            stickedBall.setX(stickedBall.getX() + raquet.getSpeedX());
        }
    }

    @Override
    public void spaceAction() {
        for (Ball handBall : handBalls) {
            handBall.setStickedBall(false);
        }
    }

    private List<Ball> findStickedBalls() {
        List<Ball> stickedBalls = new ArrayList<>();
        for (Ball handBall : handBalls) {
            if (handBall.isFrozen()) {
                stickedBalls.add(handBall);
            }
        }
        return stickedBalls;
    }
}
