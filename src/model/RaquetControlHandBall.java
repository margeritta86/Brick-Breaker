package model;

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
    public void leftAction() {
        raquet.x -= raquet.speedX;
        if (raquet.isPositionToCorrect()) {
            return;
        }
        for (Ball stickedBall : findStickedBalls()) {
            stickedBall.x -= raquet.speedX;
        }
    }

    @Override
    public void rightAction() {
        raquet.x += raquet.speedX;
        if (raquet.isPositionToCorrect()) {
            return;
        }
        for (Ball stickedBall : findStickedBalls()) {
            stickedBall.x += raquet.speedX;
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
