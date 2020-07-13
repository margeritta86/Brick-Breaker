package game.engine;

import game.model.*;
import game.model.ball.Ball;
import game.model.brick.Brick;
import game.model.player.Player;
import game.model.special.Special;

//komunikuje obiekty majace interkacje

public class Mediator {

    private Gameplay gameplay;
    private SpecialManager specialManager;
    private Player player;

    public Mediator(Gameplay gameplay, Player player) {
        this.gameplay = gameplay;
        specialManager = new SpecialManager(gameplay, this);
        this.player = player;
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


    public void incrementPlayerScore() {
        player.incrementScore();
    }
}
