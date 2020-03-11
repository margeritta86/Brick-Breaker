package model;

import engine.Mediator;

import java.util.Optional;
import java.util.Random;

public class SpecialFactory {

    final static int DEFAULT_TIME = 10;
    private final static double SPAWN_CHANCE = 0.20;
    private Random random = new Random();
    private Mediator mediator;

    public SpecialFactory(Mediator mediator) {
        this.mediator = mediator;
    }

    public Optional<Special> produceSpecial(Brick brick) {

        double result = random.nextDouble();

        if (result > SPAWN_CHANCE) {
            return Optional.empty();
        }
        SpecialType type = SpecialType.values()[random.nextInt(SpecialType.values().length)];

        double resultOfChance = random.nextDouble();
        if (type.getChance() >= resultOfChance) {

            int x = brick.getX() + brick.getWidth() / 2;
            int y = brick.getY() + brick.getHeight() / 2;
            return Optional.of(drawSpecial(type, x, y));

        } else return Optional.empty();
    }

    private Special drawSpecial(SpecialType type, int x, int y) {
        type = SpecialType.HAND_BALL;

        switch (type) {
            case WIDER_RAQUET:
                return new WiderRaquetSpecial(x, y, mediator);
            case SPEED_BALL:
                return new SpeedBallSpecial(x, y, mediator);
            case NARROWER_RAQUET:
                return new NarrowerRaquetSpecial(x, y, mediator);
            case DOUBLE_BALL:
                return new DoubleBallSpecial(x, y, mediator);
            case LEVELUP:
                return new LevelUpSpecial(x, y, mediator);
            case SLOWDOWN_BALL:
                return new SlowdownBallSpecial(x, y, mediator);
            case HAND_BALL:
                return new HandBallSpecial(x, y, mediator);
            default:

                return new DoubleBallSpecial(x, y, mediator);
        }
    }

}
