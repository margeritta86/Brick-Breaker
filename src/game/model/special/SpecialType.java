package game.model.special;

import java.awt.*;

import static game.model.special.SpecialFactory.DEFAULT_TIME;

public enum SpecialType {

    SPEED_BALL(Color.BLUE, DEFAULT_TIME,0.90),
    WIDER_RAQUET(Color.RED, DEFAULT_TIME,0.90),
    NARROWER_RAQUET(Color.ORANGE, DEFAULT_TIME,0.90),
    DOUBLE_BALL(Color.MAGENTA, 0,0.90),
    HAND_BALL(Color.PINK, DEFAULT_TIME,0.50),
    LEVELUP(Color.GREEN, 0,0.10),
    SLOWDOWN_BALL(Color.CYAN, DEFAULT_TIME,0.50);


    private Color color;
    private int duration;
    private double chance;

    SpecialType() {
    }

    SpecialType(Color color, int duration, double chance) {
        this.color = color;
        this.duration = duration;
        this.chance = chance;
    }

    public int getDuration() {
        return duration;
    }

    public Color getColor() {
        return color;
    }


    public double getChance() {
        return chance;
    }
}
