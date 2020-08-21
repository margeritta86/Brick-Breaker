package game.model.special;

import game.engine.Mediator;
import game.model.brick.Brick;

import java.util.Optional;
import java.util.Random;

public class SpecialFactory {

    public final static int DEFAULT_TIME = 5;
    private final static double SPAWN_CHANCE = 0.50;
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

    public Special createHandBallOnStartSpecial() {
        Special startSpecial = new HandBallSpecial(-100,-100,mediator);
        startSpecial.setDuration(3);
        return  startSpecial;
    }
}
