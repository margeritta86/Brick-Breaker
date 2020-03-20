package game.engine;

import game.model.*;
import game.model.ball.Ball;
import game.model.brick.Brick;
import game.model.special.Special;

//komunikuje obiekty majace interkacje

public class Mediator {

    private Gameplay gameplay;
    private SpecialManager specialManager;

    public Mediator(Gameplay gameplay) {
        this.gameplay = gameplay;
        specialManager = new SpecialManager(gameplay, this);
    }

    public void executeBrickBallCollision(Ball ball) {
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

    public void doubleBall(Ball ball) {
        ObjectFactory objectFactory = new ObjectFactory(this);
        gameplay.addObjects(objectFactory.produceBalls(2, ball));
    }

    public void wonLevel(){
        gameplay.getLevelService().setState(State.WIN);
    }

    public void executeRaquetCollision(Special special) {
        specialManager.executeRaquetCollision(special);
    }

    public void spawnSpecial(Brick brick) {
        specialManager.spawnSpecial(brick);
    }


}
