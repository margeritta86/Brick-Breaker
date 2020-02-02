package engine;

import model.*;

import java.util.Optional;

public class Mediator {

    private Gameplay gameplay;

    public Mediator(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public void executeBallCollisions(Ball ball) {
        for (GameObject gameObject : gameplay.getObjects()) {
            if (gameObject.getType() == Type.BALL) {
                continue;
            }
            boolean wasHit = ball.doBrickCollision(gameObject);
            if (wasHit) {

                gameObject.reactToHit(ball);
            }
        }
    }


    public void spawnSpecial(Brick brick) {

        SpecialFactory specialFactory = new SpecialFactory();
        Optional<Special> special = specialFactory.produceSpecial(brick, this);
        if (special.isPresent()) {
            gameplay.addObject(special.get());
        }
    }

    public void executeRaquetCollision(Special special) {
        for (GameObject gameObject : gameplay.getObjects()) {
            if (gameObject.getType() == Type.SPECIAL) {
                continue;
            }
           special.accept(special);
        }
    }
}
