package model;

import engine.Mediator;

import java.util.Optional;
import java.util.Random;

public class SpecialFactory {

    final static int DEFAULT_TIME = 5;
    private final static double SPAWN_CHANCE = 0.20;
    private Random random = new Random();

    public Optional<Special> produceSpecial(Brick brick, Mediator mediator) {
        double result = random.nextDouble();

        if(result>SPAWN_CHANCE){
            return Optional.empty();
        }
        return Optional.of(new SpeedBallSpecial(brick.getX()+brick.getWidth()/2,
                brick.getY()+brick.getHeight()/2,
                mediator));
    }
}
