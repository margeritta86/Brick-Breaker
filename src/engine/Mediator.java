package engine;

import model.Ball;
import model.GameObject;
import model.Type;

public class Mediator {

    private Gameplay gameplay;

    public Mediator(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public void executeBallCollisions(Ball ball) {
        for (GameObject gameObject : gameplay.getObjects()) {
            if ( gameObject.getType() == Type.BALL) {
                continue;
            }
            boolean wasHit = ball.doBrickCollision(gameObject);
            if (wasHit) {

                gameObject.reactToHit(ball);
            }
        }
    }
}
